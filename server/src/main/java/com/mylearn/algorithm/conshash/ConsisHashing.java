package com.mylearn.algorithm.conshash;

import com.alibaba.fastjson.JSONObject;
import com.mylearn.util.LocalHostUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/11/11
 * Time: 下午2:01
 * CopyRight: taobao
 * Descrption:
 */

public class ConsisHashing<T extends Comparable> {
    private final static Logger logger = LoggerFactory.getLogger(ConsisHash.class);
    private TreeMap<Long, ServerNode> nodes = new TreeMap<Long, ServerNode>();
    //真实服务器节点信息
    private List<ServerNode> shards = new ArrayList();
    private Map<ServerNode, List<T>> nodeMap = new HashMap<ServerNode, List<T>>();        //key:serverNod, value:终端编号list
    private static int MAX_SIZE = 0;


    public boolean curServerIsAdmin(String diamondCfg) {
        String serverName = getNodeNameByIp(diamondCfg);
        return serverName != null;
    }


    /**
     * 获取当前节点
     *
     * @return
     */
    public List<T> getCurServerTerminal(String diamondCfg) {
        String serverName = getNodeNameByIp(diamondCfg);
        ServerNode serverNode = getSerNode(serverName);
        if (serverNode != null) {
            List<T> terminalList = nodeMap.get(serverNode);
            logger.info("getCurServerTerminal:{}", terminalList);
            return terminalList;
        }
        logger.error("can't find serverNode");
        return Collections.EMPTY_LIST;
    }

    public Map<ServerNode, List<T>> hasing(String serverConfig, List<T> nodeList) {
        initServer(serverConfig);
        initNode(nodeList);
        return nodeMap;
    }

    private void initNode(List<T> nodeList) {
        logger.info("initTerminal==,terminalDOList size" + nodeList.size());
        if (CollectionUtils.isEmpty(nodeList)) {
            logger.error("----terminal not initialed-----");
            //CR trhow runtimeException    fixed
            throw new RuntimeException("-terminal not initialed");
        }
        MAX_SIZE = nodeList.size();//获得终端最大数量
        //        CR: 保证终端有序  ,注意保证id有序.      fixed
        Collections.sort(nodeList);
        nodeList.forEach(n ->
                {
                    byte[] digest = computeMd5(String.valueOf(n));
                    long hash1 = hash(digest, 1);
                    ServerNode serverNode = getShardInfo(hash1);
                    logger.info("getShardInfo,serverNode={}", serverNode);
                    List<T> keyList = new ArrayList<T>();
                    if (nodeMap.containsKey(serverNode)) {
                        keyList = nodeMap.get(serverNode);
                    }
                    keyList.add(n);
                    nodeMap.put(serverNode, keyList);
                }
        );
        logger.info("nodeMap=" + nodeMap.toString());
        logger.info("after add slot nodes info=" + nodes.toString());
    }

    private void initServer(String serverConfig) {
        //如果新的配置非空,则情况hash环
        //如果当前服务器没配置,初始化一个默认的节点
        try {
            Map<String, String> serverNodeMap = new HashMap<>();
            if (StringUtils.isEmpty(serverConfig)) {
                logger.error("online Ips empty,warning!");
                throw new RuntimeException("-online Ips empty-");
            } else {
                serverNodeMap = JSONObject.parseObject(serverConfig, Map.class);
                long keyCnt = serverNodeMap.keySet().size();
                long valueCnt = serverNodeMap.values().stream().distinct().count();
                logger.info("node keycnt={},valueCnt={}", keyCnt, valueCnt);
                if (keyCnt != valueCnt) {
                    throw new RuntimeException("diamond config error, 有重复的value");
                }
            }
            if (CollectionUtils.isEmpty(serverNodeMap)) {
                logger.error("serverConfig format error!");
                throw new RuntimeException("-serverConfig format error-");
            }
            //初始化shards
            for (Map.Entry<String, String> entry : serverNodeMap.entrySet()) {
                ServerNode serverNode = new ServerNode(entry.getKey(), entry.getValue());
                shards.add(serverNode);
                int VIRTUAL_NUM = 4;
                for (int j = 0; j < VIRTUAL_NUM; j++) {
                    //这里一定要注意,用node.name来做hash,保持节点hash的值不变
                    nodes.put(hash(computeMd5("SHARD-" + serverNode.getName() + "-NODE-" + j), j), serverNode);
                }
            }
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw e;
            }
            logger.error("init server error: ", e);
        }
        //
        logger.info("nodes info=" + nodes.toString());
        logger.info("shards info=" + shards.toString());
    }


    /**
     * 根据2^32把节点分布到圆环上面。
     *
     * @param digest
     * @param nTime
     * @return
     */
    public long hash(byte[] digest, int nTime) {
        long rv = ((long) (digest[3 + nTime * 4] & 0xFF) << 24)
                | ((long) (digest[2 + nTime * 4] & 0xFF) << 16)
                | ((long) (digest[1 + nTime * 4] & 0xFF) << 8)
                | (digest[0 + nTime * 4] & 0xFF);

        return rv & 0xffffffffL; /* Truncate to 32-bits */
    }

    /**
     * Get the md5 of the given key.
     * 计算MD5值
     */
    public byte[] computeMd5(String k) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not supported", e);
        }
        md5.reset();
        byte[] keyBytes = null;
        try {
            keyBytes = k.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unknown string :" + k, e);
        }

        md5.update(keyBytes);
        return md5.digest();
    }


    /**
     * 根据key的hash值取得服务器节点信息
     *
     * @param hash
     * @return
     */
    public ServerNode getShardInfo(long hash) {
        int average = MAX_SIZE / shards.size() + (MAX_SIZE % shards.size() == 0 ? 0 : 1); //平均值
        Long key = hash;
        //返回键值大于等于key的map,取第一个虚拟节点,
        SortedMap<Long, ServerNode> tailMap = nodes.tailMap(key);
        if (tailMap.isEmpty()) {
            //                如果落到最后一个环   ,直接构造一个当前hash的slot
            ServerNode minSizeServer = getMinSizeServer();
            if (minSizeServer == null) {
                minSizeServer = nodes.firstEntry().getValue();
            }
            nodes.put(hash, minSizeServer);
            return minSizeServer;
        } else {
            key = tailMap.firstKey();
        }
        logger.info("-- nodekey:{} ,average={}", key, average);
//        nodes.get(key);

        ServerNode serverNode = nodes.get(key);
        List<T> objectList = nodeMap.get(serverNode);
        if (!CollectionUtils.isEmpty(objectList) && objectList.size() >= average) {
//            logger.info("servernode={}, ojbectsize={},key={},hash={}", serverNode.getName(), objectList.size(), key, hash);
            //如果某个服务器节点已经满了 ,增加虚拟节点  ,对应数量最少的那个服务器
            //   rehash,在hash和下一个节点前加入slot节点,
            ServerNode minSizeServer = getMinSizeServer();
            Long slotKey = (key + hash) / 2;
            while (key.equals(slotKey)) {              //当key和hash相等时,取平均肯定也是一样的,就会造成死循环,所以这里就取下一个节点的hash
                key++;
                SortedMap<Long, ServerNode> tailMapNext = nodes.tailMap(key);
                logger.info("tailMapNext.firstkey:{},size={}", tailMapNext.firstKey(), tailMapNext.size());
                if (tailMapNext.isEmpty()) {
                    //如果下一个刚好为空,则取
                    slotKey = (key + Long.MAX_VALUE) / 2;
                } else {
                    //如果下一个 不空,则取下一个节点和当前key的均值
                    slotKey = (key + tailMapNext.firstKey()) / 2;
                }
            }
//            logger.info("key={},hash={},slotKey={},minSizeServer={}", key, hash, slotKey, minSizeServer.getName());
            nodes.put(slotKey, minSizeServer);
            serverNode = getShardInfo(hash);
        }
        return serverNode;
    }

    /**
     * 获取最小负载的服务器节点
     *
     * @return
     */
    public ServerNode getMinSizeServer() {
        int size = 0;
        ServerNode key = null;

        for (Map.Entry<ServerNode, List<T>> entry : nodeMap.entrySet()) {
            int curSize = entry.getValue().size();
            if (size == 0) {
                size = curSize;
                key = entry.getKey();
            }
            if (size > curSize) {
                size = curSize;
                key = entry.getKey();
            }
        }
        logger.debug("min server:  key=" + key + ",size=" + size);
        return key;
    }


    public String getNodeNameByIp(String diamondCfg) {
        String ip = LocalHostUtil.getHostAddress();
        if (!StringUtils.isEmpty(diamondCfg)) {
            Map<String, String> serverNodeMap = JSONObject.parseObject(diamondCfg, Map.class);
            return serverNodeMap.get(ip);
        }
        return null;
    }

    /**
     * 根据serverName获取节点
     *
     * @param serverName
     * @return
     */
    private ServerNode getSerNode(String serverName) {
        logger.info("getSerNode serverName=" + serverName);
        if (serverName != null) {
            for (ServerNode serverNode : shards) {
                //读取diamond,找到当前ip对应的node-name,然后根据name找节点
                if (serverName.equals(serverNode.getName().toString())) {
                    logger.info("--find the serverNode,serverName={}", serverName);
                    return serverNode;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {


        Map<String, String> serverMapB = new HashMap<String, String>();
        serverMapB.put("192.168.1.4", "serverA");
        serverMapB.put("192.168.1.5", "serverB");
        serverMapB.put("192.168.1.6", "serverC");
        serverMapB.put("192.168.1.7", "serverD");
        Set<String> s1 = new HashSet<>(serverMapB.values());
//        String serverCfg = JSONObject.toJSONString(serverMapB);
        ConsisHashing<Long> consisHashing = new ConsisHashing<>();
        String allServerConfig = "{\"10.218.132.7\":\"serverA\",\"10.218.132.8\":\"serverB\",\"10.218.132.9\":\"serverC\"}";
        JSONObject jsonObject1 = JSONObject.parseObject(allServerConfig);
        jsonObject1.put(LocalHostUtil.getHostAddress(), "serverB");
        String serverCfg = jsonObject1.toJSONString();
        List<Long> nodeList = new ArrayList<Long>();
        for (int i = 0; i < 50; i++) {
            nodeList.add(138000001001L + i);
        }
        Map<ServerNode, List<Long>> nodeMap = consisHashing.hasing(serverCfg, nodeList);
        System.out.println("after hasing=" + nodeMap);
        nodeMap.forEach((id, val) -> System.out.print("nodeMap,ke=" + id.getName() + ",size=" + val.size()));

    }

}
