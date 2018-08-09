package com.mylearn.zookeeper.locks;

import java.util.List;

import com.mylearn.zookeeper.test.ZkApi;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 18/3/19
 * Time: ÏÂÎç2:27
 * CopyRight: ying
 * Descrption:
 */

public class NodeWatcher implements Watcher {

    public static void main(String[] args) {
        try {
            String serverPath = "127.0.0.1:2181";
            //String serverPath = "11.239.178.187:2181";
            ZkApi zkApi = new ZkApi(serverPath);
            ZooKeeper zk = zkApi.getZk();
            zkApi.createNod("/testWatch", "lockRootData", ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT);
            List<ACL> aclList = ZooDefs.Ids.OPEN_ACL_UNSAFE;
            String nodePath = "/testWatch/testphan";
            String node = zkApi.createNod(nodePath, "testphan", aclList,
                CreateMode.EPHEMERAL_SEQUENTIAL);

            Stat sts = zk.exists(node, true);
            System.out.println("stats exists=" + sts);

            zk.delete(node, sts.getVersion());

            System.out.println("stats delete=" + sts);
        } catch (Exception e) {

        }

    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("---watch process-----");
    }
}
