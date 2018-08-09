package com.mylearn.zookeeper.test;

import com.mylearn.zookeeper.locks.MutexObj;
import com.mylearn.zookeeper.locks.ZKDistributeLock;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-26
 * Time: 上午8:59
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class MyZk implements Watcher {

    public void process(WatchedEvent watchedEvent) {
        if (EventType.NodeDeleted.equals(watchedEvent.getType())) {
        System.out.println("触发了" + watchedEvent.getType() + "事件！");
            ZKDistributeLock zkDistributeLock = new ZKDistributeLock();
            zkDistributeLock.setFlag(true);
            //唤醒锁
            MutexObj.lock.lock();
            MutexObj.condition.signalAll();
            MutexObj.lock.unlock();
            System.out.println("signallAll");
        }
    }
}
