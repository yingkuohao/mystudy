package com.mylearn.algorithm.conshash;


import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/10/19
 * Time: 下午5:16
 * CopyRight: taobao
 * Descrption:
 */

public class ConsisHash {


    private static TreeMap<Long, Object> nodes = null;
    //真实服务器节点信息
    private List<Object> shards = new ArrayList();
    private static Map<Object, List<Long>> nodeMap = new HashMap<Object, List<Long>>(); //每个节点保存了多少个值
    //设置虚拟节点数目
    private int VIRTUAL_NUM = 4;
    private static int MAX_SIZE = 100;

    /**
     * 初始化一致环
     */
    public void init() {
//        shards.add("192.168.0.0-服务器0");
        shards.add("192.168.0.1-服务器1");
        shards.add("192.168.0.2-服务器2");
        shards.add("192.168.0.3-服务器3");
        shards.add("192.168.0.4-服务器4");
//        shards.add("192.168.0.5-服务器5");

        nodes = new TreeMap<Long, Object>();
        for (int i = 0; i < shards.size(); i++) {
            Object shardInfo = shards.get(i);
            for (int j = 0; j < VIRTUAL_NUM; j++) {
                nodes.put(hash(computeMd5("SHARD-" + i + "-NODE-" + j), j), shardInfo);
            }
        }
    }

    /**
     * 根据key的hash值取得服务器节点信息
     *
     * @param hash
     * @return
     */
    public Object getShardInfo(long hash) {
        int average = MAX_SIZE / shards.size(); //平均值
        Long key = hash;
        //返回键值大于等于key的map,取第一个虚拟节点,
        SortedMap<Long, Object> tailMap = nodes.tailMap(key);
        Object minSizeServer = getMinSizeServer();
        if (tailMap.isEmpty()) {
//                如果落到最后一个环   ,直接构造一个当前hahs的slot
            key = nodes.firstKey();
            nodes.put(hash, minSizeServer);
            return minSizeServer;
        } else {
            key = tailMap.firstKey();
        }


        System.out.print(" nodekey: " + key);
        Object serverNode = nodes.get(key);
        List<Long> objectList = nodeMap.get(serverNode);
        if (!CollectionUtils.isEmpty(objectList) && objectList.size() >= average) {
            //如果某个服务器节点已经满了 ,增加虚拟节点  ,对应数量最少的那个服务器
            //   rehash,在hash和下一个节点前加入slot节点,
            nodes.put((key + hash) / 2, minSizeServer);
            serverNode=  getShardInfo(hash);
        }

        return serverNode;
    }

    public Object getMinSizeServer() {
        int size = 0;
        Object key = null;
        for (Map.Entry<Object, List<Long>> entry : nodeMap.entrySet()) {
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
        System.out.println("min server:  key=" + key + ",size=" + size);
        return key;
    }

    /**
     * 打印圆环节点数据
     */
    public void printMap() {
        System.out.println(nodes);
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

        long hashValue = rv & 0xffffffffL;//  Truncate to 32-bits

//        hashValue = hashValue % 256;
        return hashValue;
//        return hashCode(new String(digest).toCharArray());
    }

    public int hashCode(char[] value) {
        int h = 0;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 8 * h + val[i];
            }
        }
//        h = h % 128;
//        h = Math.abs(h);
        System.out.println("h=" + h);
        return h;
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

    public static void main(String[] args) {


        Random ran = new Random();
        ConsisHash hash = new ConsisHash();
        String s = "hahaha";
        int m = hash.hashCode(s.toCharArray());


        hash.init();
        hash.printMap();
        //循环50次，是为了取50个数来测试效果，当然也可以用其他任何的数据来测试
        for (int i = 0; i < MAX_SIZE; i++) {
            byte[] digest = hash.computeMd5(String.valueOf(i));
//            System.out.println("--i="+i+ " :  "+ hash.getShardInfo(hash.hash(digest, ran.nextInt(hash.VIRTUAL_NUM))));
            long hash1 = hash.hash(digest, 1);
//            long hash1 = hash.hash(digest, ran.nextInt(hash.VIRTUAL_NUM));
            Object shardInfo = hash.getShardInfo(hash1);
            List<Long> keyList = new ArrayList<Long>();
            if (nodeMap.containsKey(shardInfo)) {
                keyList = nodeMap.get(shardInfo);
            }
            keyList.add(Long.valueOf(i));
            nodeMap.put(shardInfo, keyList);
//            System.out.println("--i=" + i + " :  datahash:" + hash1 + " : " + shardInfo);
        }
        System.out.println("nodeMap=" + nodeMap.toString());
        System.out.println("nodes=" + nodes.toString());
    }


}
