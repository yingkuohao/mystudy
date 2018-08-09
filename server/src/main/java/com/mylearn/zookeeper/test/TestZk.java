package com.mylearn.zookeeper.test;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-25
 * Time: 下午12:29
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestZk implements Watcher {
    private static final int SESSION_TIMEOUT = 10000;
    private static final String CONNECTION_STRING = "11.239.178.187:2181";
    private static final String ZK_PATH = "/nileader";
    private ZooKeeper zk = null;

    private CountDownLatch connectedSemaphore = new CountDownLatch( 1 );

    public static void main(String args[]) {

        TestZk sample = new TestZk();
        sample.createConnection( CONNECTION_STRING, SESSION_TIMEOUT );
        if ( sample.createPath( ZK_PATH, "我是节点初始内容" ) ) {
            System.out.println();
            System.out.println( "数据内容: " + sample.readData( ZK_PATH ) + "\n" );
            sample.writeData( ZK_PATH, "更新后的数据" );
            System.out.println( "数据内容: " + sample.readData( ZK_PATH ) + "\n" );
            sample.deleteNode( ZK_PATH );
        }

        sample.releaseConnection();
    }

    public void createConnection(String connectString,int sessionTimeout) {
             try{
                 zk = new ZooKeeper(connectString,sessionTimeout,this);
                 connectedSemaphore.await();
             } catch (IOException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             } catch (InterruptedException e) {
                 e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
             }
    }


    /**
     * 关闭ZK连接
     */
    public void releaseConnection() {
        if ( this.zk!=null ) {
            try {
                this.zk.close();
            } catch ( InterruptedException e ) {
                // ignore
                e.printStackTrace();
            }
        }
    }

    public boolean  createPath(String path,String data)  {
        try {
            System.out.println( "节点创建成功, Path: "
                     + this.zk.create( path, //
                                               data.getBytes(), //
                                               ZooDefs.Ids.OPEN_ACL_UNSAFE, //
                                               CreateMode.EPHEMERAL )
                     + ", content: " + data );
        } catch (KeeperException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return true;
    }

    public String readData(String path) {
        try {
            System.out.println( "获取数据成功，path：" + path );
             return new String( this.zk.getData( path, false, null ) );
        } catch (KeeperException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public boolean writeData(String path,String data) {
        try {
            System.out.println( "更新数据成功，path：" + path + ", stat: " +
                                                         this.zk.setData( path, data.getBytes(), -1 ) );
        } catch (KeeperException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return  false;
    }

    /**
     * 删除指定节点
     * @param path 节点path
     */
    public void deleteNode( String path ) {
        try {
            this.zk.delete( path, -1 );
            System.out.println( "删除节点成功，path：" + path );
        } catch ( KeeperException e ) {
            System.out.println( "删除节点失败，发生KeeperException，path: " + path  );
            e.printStackTrace();
        } catch ( InterruptedException e ) {
            System.out.println( "删除节点失败，发生 InterruptedException，path: " + path  );
            e.printStackTrace();
        }
    }



    public void process(WatchedEvent watchedEvent) {
        System.out.println( "收到事件通知：" + watchedEvent.getState() +"\n"  );
        if ( Event.KeeperState.SyncConnected == watchedEvent.getState() ) {
            connectedSemaphore.countDown();
        }
    }
}
