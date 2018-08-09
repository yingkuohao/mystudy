package com.mylearn.zookeeper.test;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-26
 * Time: 上午8:57
 * CopyRight:360buy
 * Descrption:
 * zk java api测试
 * To change this template use File | Settings | File Templates.
 */
public class ZkApi {

//    private static String server = "192.168.229.80:";
    private static String server = "11.239.178.187:";
    private static String port = "2181";
    private static final int SESSION_TIMEOUT = 10000;
    private  ZooKeeper zk = null;
    private static  ZooKeeper zkClient = null;

    public static void main(String args[]) {

        ZkApi testzkApi = new ZkApi(server + port);
        zkClient = testzkApi.getZk();
        String parentPath = "/testParentPath";        //节点路径 ,the path for the node
        String testParentData = "testParentData";       //  节点初始化数据，the initial data for the node
        List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE; //节点的权限 the acl for the node
//        CreateMode createMode = CreateMode.EPHEMERAL_SEQUENTIAL;  //临时节点，一旦创建这个节点的客户端与服务端端口session超时，这种节点会被自动删除。
        CreateMode createMode = CreateMode.PERSISTENT;  //持久节点，这个目录节点存储的数据不会丢失。
        try {
            testzkApi. createNod(parentPath, testParentData, aclList, createMode);
            String parentData = new String(zkClient.getData(parentPath, false, null));//读取付父节点内容，getData方法
            System.out.println("parentData=" + parentData);

            String childPath1 = parentPath + "/testChildPath1";
            String childData1 = "testChildData1";

            testzkApi. createNod(childPath1, childData1, aclList, createMode);//创建子目录 1
            String childDataPop = new String(zkClient.getData(childPath1, false, null));//读取子节点内容
            System.out.println("childData1=" + childDataPop);


            String childPath2 = parentPath + "/testChildPath2";
            String childData2 = "testChildData2";

            testzkApi.createNod(childPath2, childData2, aclList, createMode);//创建子目录2
            //创建另一个父节点
            String parentPath2 = "/testParentPath2";
            String testParentData2 = "testParentData2";
            testzkApi. createNod(parentPath2, testParentData2, aclList, createMode);

            listNode("/");
            listNode(parentPath);

            Stat stat = zkClient.exists(parentPath, false);
            List<ACL> rootAcl = zkClient.getACL(parentPath, stat);   //获取某个目录节点的访问权限列表。
            System.out.println("节点权限：" + StringUtils.join(rootAcl.toArray(), ",") + "节点版本：" + stat.getVersion());
            zkClient.setACL(parentPath, ZooDefs.Ids.READ_ACL_UNSAFE, stat.getVersion());
            System.out.println("节点权限：" + StringUtils.join(zkClient.getACL(parentPath, stat).toArray(), ","));

            deleteNode("/testChildPath");
            deleteNode(parentPath); //父目录下有子节点，不能直接删除，会报错： KeeperErrorCode = Directory not empty for /testParentPath
            listNode("/");
        } catch (KeeperException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    /**
     * 删除节点
     *
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void deleteNode(String path) throws KeeperException, InterruptedException {
        Stat stat = zkClient.exists(path, true);
        if (stat != null) {
            zkClient.delete(path, -1);  //-1可以匹配任何版本，也就删除了这个目录节点所有数据,
            System.out.println("删除" + path + "节点成功");
            listNode(path);
        } else {
            System.out.println(path + "节点不存在");
        }
    }

    /**
     * 打印节点信息
     *
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public static void listNode(String path) throws KeeperException, InterruptedException {
        List<String> nodeList = zkClient.getChildren(path, false); //获取孩子节点
        System.out.println(path + "路径下的节点" + StringUtils.join(nodeList.toArray(), ","));
    }

    /**
     * 创建节点
     *
     * @param nodePath   节点路径
     * @param nodeData   节点初始化数据
     * @param aclList    权限列表
     * @param createMode 节点类型
     * @throws KeeperException
     * @throws InterruptedException
     */
    public  String createNod(String nodePath, String nodeData, List<ACL> aclList, CreateMode createMode) {
        String node=null;
        try {
            Stat stat = this.getZk().exists(nodePath, true);
            if (stat != null) {
                //如两次执行 EPHEMERAL节点，就不会执行这段代码，因为每次都会清除；而 PERSISTENT节点，创建一次后就会一直存在，除非手动删除
                System.out.println("节点" + nodePath + "已存在！");
                node=  stat.toString();
            } else {
                node= this.zk.create(nodePath, nodeData.getBytes(), aclList, createMode);//创建节点 ,create方法
                System.out.println("创建新节点成功："+nodePath);

            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return node;
    }


    public ZkApi(String nodePath) {
        init(nodePath);
    }

    /**
     * 初始化zk
     *
     *
     * @param path
     * @return
     */
    public ZooKeeper init(String path) {
        if (zk == null) {
            try {
                Watcher watcher = new MyZk();
                System.out.println("path="+path);
                zk = new ZooKeeper(path, SESSION_TIMEOUT, watcher);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        return zk;
    }

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }
}
