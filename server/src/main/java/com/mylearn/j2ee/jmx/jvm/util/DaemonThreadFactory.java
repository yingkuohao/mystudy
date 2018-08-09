package com.mylearn.j2ee.jmx.jvm.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:50
 * CopyRight: taobao
 * Descrption:
 */

public class DaemonThreadFactory implements ThreadFactory {

    final ThreadGroup   group;
    final AtomicInteger threadNumber = new AtomicInteger(1);
    final String        namePrefix;
    final String        nameSuffix   = "]";

    public DaemonThreadFactory(String poolName){
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = poolName + " Pool [Thread-";
    }

    public DaemonThreadFactory(String poolName, ThreadGroup threadGroup){
        group = threadGroup;
        namePrefix = poolName + " Pool [Thread-";
    }

    public ThreadGroup getThreadGroup() {
        return group;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement() + nameSuffix, 0);
        t.setDaemon(true);
        if (t.getPriority() != Thread.NORM_PRIORITY) t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
