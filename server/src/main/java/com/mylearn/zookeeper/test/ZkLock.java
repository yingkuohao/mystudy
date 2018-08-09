package com.mylearn.zookeeper.test;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-26
 * Time: ????10:54
 * CopyRight:360buy
 * Descrption:
 * ??????  ??
 * 1. ??????????
      * 2. ?????????? ???????��???????
      * 3. ?????jvm????????��??????��???????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class ZkLock {

    private static final int SESSION_TIMEOUT = 10000;
    static CountDownLatch countDownLatch = new CountDownLatch(1);
    CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        public void run() {
            System.out.println("????????????????????");  //??????????
        }
    });
    private static String parentLockPth = "/lockRoot";
    volatile static boolean flag = false;

    public static void main(String args[]) {
        final Map<String, String> map = new HashMap<String, String>();
        map.put("node0", "11.239.178.187:2181");
        map.put("node1", "192.168.229.79:2181");
        map.put("node2", "192.168.229.80:2181");
        final ZkLock zkLock = new ZkLock();
        zkLock.creatRootNode(map.get("node0")); //????lock?????

        checkParentIsOk(map);   //��?�??????ok
        //??????????
        for (int i = 0; i < 3; i++) {
            final int finalI = i;
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        countDownLatch.await();  //??????????
                        String key = "node" + finalI;
                        zkLock.createEphemeralNode(map.get(key), finalI);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            });
            thread.start();
        }
        countDownLatch.countDown();//?????????

    }

    /**
     * ????????��?????????????
     *
     * @param map
     */
    private static void checkParentIsOk(Map<String, String> map) {
        ZkApi zkApi = new ZkApi(map.get("node1"));
        ZooKeeper zooKeeper = zkApi.getZk();
        try {
            String parentData = new String(zooKeeper.getData(parentLockPth, false, null));
            System.out.println(Thread.currentThread().getName() + "parentData=" + parentData);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ??????????:lockRoot
     *
     * @param serverPath
     */
    public void creatRootNode(String serverPath) {
        ZkApi zkApi = new ZkApi(serverPath);
        ZooKeeper zooKeeper = zkApi.getZk();
        String parentLockPth = "/lockRoot";
        zkApi.createNod(parentLockPth, "lockRootData", ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT); //???????????
        try {
            String parentData = new String(zooKeeper.getData(parentLockPth, false, null));
            System.out.println(Thread.currentThread().getName() + "parentData=" + parentData);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ???????????????????????��
     * /lockRoot/lock0
     * /lockRoot/lock1
     * /lockRoot/lock2
     *
     * @param serverPath
     * @param i
     */
    public void createEphemeralNode(String serverPath, int i) {
        ZkApi zkApi = new ZkApi(serverPath);
        String currentPath = "/lock" + i;
        String wholePath = parentLockPth + currentPath;        //???��?? ,,????/lockRoot/lock1
        String testData = "lockdata";       //  ??????????
        List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE; //???????

//      zkApi.createNod(path, testData, aclList, CreateMode.EPHEMERAL_SEQUENTIAL); //?????????????????????????????????lock1000000018, lock1000000019
        zkApi.createNod(wholePath, testData, aclList, CreateMode.EPHEMERAL); //???????????
        try {
            cyclicBarrier.await();  //??????????????????????
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        getLock(zkApi.getZk(), parentLockPth, currentPath); //????
    }

    /**
     * ???????????
     * 1. ??????????
     * 2. ?????????? ???????��???????
     * 3. ?????jvm????????��??????��???????????????????????????????
     *
     * @param zooKeeper
     * @param parentLockPth
     * @param currentPath
     */
    private void getLock(ZooKeeper zooKeeper, String parentLockPth, String currentPath) {
        try {
            flag = false;
            List<String> childNode = zooKeeper.getChildren(parentLockPth, false);    //????????��?
            Collections.sort(childNode); //??????��????? ???��lock0,lock1,lock2
            System.out.println(Thread.currentThread().getName() + "beginGetLock" + StringUtils.join(childNode.toArray(), ","));
            String firtNode = childNode.get(0); //?????��???????????????????????lock0
            System.out.println(Thread.currentThread().getName() + "firNode=" + firtNode + "cureentNode=" + currentPath);
            firtNode = "/" + firtNode;
            if (currentPath.equals(firtNode)) {
                //?????????????????????????????????
                System.out.println(Thread.currentThread().getName() + "[get Lock ok]" + zooKeeper.getSaslClient().getConfigStatus());
                doAction();
                zooKeeper.delete(parentLockPth + firtNode, -1);//??????????????node
                Stat stat = zooKeeper.exists(parentLockPth + firtNode, false);
                if (stat == null) {
                    System.out.println(Thread.currentThread().getName() + "?????????");
                }
            } else {
                //????????????????��???????????
                waitLock(zooKeeper, parentLockPth, currentPath);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ?????????????4???????????????????????server???????????
     * ?????????server???????????
     * @param zooKeeper
     * @param parentLockPth
     * @param currentPath
     */
    private void waitLock(ZooKeeper zooKeeper, String parentLockPth, String currentPath) {
        try {
            Stat stat = zooKeeper.exists(parentLockPth + currentPath, false);
            while (flag = false) {
                this.wait();  //flag????false?????????????????????????????flag?????????
            }
            if (stat != null) {
                //??????????????????????????????????????
                System.out.println(Thread.currentThread().getName() + "???????????????????");
                flag = false;
                Thread.sleep(2000);
            }
            System.out.println(Thread.currentThread().getName() + "watiLock.????,node[0]???????");
            //????????????????????????????????????????????????????????????????????????
            getLock(zooKeeper, parentLockPth, currentPath);

        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (KeeperException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void getLock() {
        doAction();
        flag = true;
    }

    private void doAction() {
        System.out.println(Thread.currentThread().getName() + "doAction");
        try {
            Thread.currentThread().sleep(2000);
            System.out.println(Thread.currentThread().getName() + "??????");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
