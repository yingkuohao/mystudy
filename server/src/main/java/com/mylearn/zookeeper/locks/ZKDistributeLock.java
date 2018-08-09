package com.mylearn.zookeeper.locks;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.mylearn.zookeeper.test.ZkApi;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 18/3/19
 * Time: 上午10:59
 * CopyRight: ying
 * Descrption:
 */

public class ZKDistributeLock {
    private static String parentLockPth = "/lockRoot";
    private static String lockName = "/testLock";
    protected static ZooKeeper zk = null;
    protected static ZkApi zkApi = null;
    protected static Integer mutex;
    protected volatile Boolean flag = false;

    private String myNode = "";

    public static void main(String[] args) {
        //singleTest();
        batchTest();
    }

    private void singleTest() {
        ZKDistributeLock zkDistributeLock = new ZKDistributeLock();
        //初始化连接
        String serverPath = "127.0.0.1:2181";
        //String serverPath = "11.239.178.187:2181";
        zkApi = new ZkApi(serverPath);
        initConnection();
        try {
            boolean isGetLock = zkDistributeLock.lock(myNode);

            if (isGetLock) {
                System.out.println("getLock ok,do some thing");
            }
        } catch (Exception e) {
            System.out.println("getLock error,e=" + e);
            e.printStackTrace();
        } finally {
            zkDistributeLock.unlock();
        }
    }

    public static void batchTest() {

        //初始化连接
        String serverPath = "127.0.0.1:2181";
        //String serverPath = "11.239.178.187:2181";
        zkApi = new ZkApi(serverPath);
        initConnection();
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(() -> {
                ZKDistributeLock zkDistributeLock = new ZKDistributeLock();
                try {
                    boolean isGetLock = zkDistributeLock.lock(null);
                    if (isGetLock) {
                        System.out.println(Thread.currentThread().getName() + "----getLock ok,do some thing-----");
                    }
                } catch (Exception e) {
                    System.out.println("getLock error,e=" + e);
                    e.printStackTrace();
                } finally {
                    zkDistributeLock.unlock();
                }
                countDownLatch.countDown();
            });
            t.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean lock(String nodePath) {
        //创建虚节点,路径非空时不创建.
        try {
            String lockpath = parentLockPth + lockName;
            if (nodePath != null) {
                myNode = nodePath;
            } else {
                Stat nodeStat = zk.exists(lockpath, false);
                if (nodeStat == null) {
                    myNode = createEphemeralNode(lockpath);
                } else {
                    myNode = nodePath;
                }
            }
            //核心逻辑:校验最小节点
            List<String> list = null;
            list = zk.getChildren(parentLockPth, false);
            String[] nodes = list.toArray(new String[list.size()]);
            Arrays.sort(nodes);
            String lowestNode = parentLockPth + "/" + nodes[0];
            System.out.println("lowerst node=" + lowestNode + ",but myNode=" + myNode);
            if (myNode.equals(lowestNode)) {
                //doAction();
                System.out.println(Thread.currentThread().getName() + ",get Lock ok");
                return true;
            } else {
                waitForLock(nodes[0]);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    void waitForLock(String lower) throws InterruptedException, KeeperException {
        Stat stat = zk.exists(parentLockPth + "/" + lower, true);        //watch住比当前序号小的节点
        //防止重入
        System.out.println("flag=" + flag);

        //上锁 ,等待watch结束后唤醒
        MutexObj.lock.lock();
        MutexObj.condition.await();
        System.out.println("after wait");
        MutexObj.lock.unlock();

        //唤醒后重新抢锁
        System.out.println(Thread.currentThread().getName() + ", waiting for Lock try getLock");
        if (stat != null) {
            flag = false;
            Thread.sleep(2000);
        }

        lock(myNode);
    }

    public void unlock() {
        try {
            if (myNode != null) {

                Stat stat = zk.exists(myNode.toString(), false);
                if (stat != null) {
                    zk.delete(myNode.toString(), 0);
                    System.out.println("----realease Lock ok" + Thread.currentThread().getName() + "-myNode=" + myNode);
                }
            }
            //flag = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    private static void initConnection() {

        zk = zkApi.getZk();
        zkApi.createNod(parentLockPth, "lockRootData", ZooDefs.Ids.OPEN_ACL_UNSAFE,
            CreateMode.PERSISTENT); //???????????
        try {
            String parentData = new String(zk.getData(parentLockPth, false, null));
            System.out.println(Thread.currentThread().getName() + "parentData=" + parentData);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String createEphemeralNode(String lockPath) {
        try {
            String testData = "lockdata";
            List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE;

            String node = zkApi.createNod(lockPath, testData, aclList,
                CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("crete node ok" + node);

            byte[] bytes = zk.getData(node, false, null);
            //String s = new String(bytes);
            //System.out.println("get ephenemral date,s=" + s);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
