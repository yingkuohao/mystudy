package com.mylearn.threadpool.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/3/17
 * Time: ÏÂÎç7:29
 * CopyRight: taobao
 * Descrption:
 */

public class TestLock {
    private static Lock lock = new ReentrantLock();

    public void test() {
        try {
            lock.lock();
            System.out.println("---ThreadName=" + Thread.currentThread().getName() + ",MediaCommand get Lock ok");

            System.out.println("---ThreadName= do thing!");

            System.out.println("---ThreadName=" + Thread.currentThread().getName() + ",release Lock ok");
            lock.unlock();
        } catch (Exception e) {
            e.printStackTrace();
            lock.unlock();
            System.out.println("lock error" + e);
        } finally {
            System.out.println("---ThreadName=" + Thread.currentThread().getName() + "fianlly  ,release Lock ok");
        }
    }

    public void testSync(Long id) {
        synchronized (id) {
            System.out.println("---ThreadName=" + Thread.currentThread().getName() + ",synchonize get Lock ok");

            System.out.println("---ThreadName= do thing!");
            System.out.println("---ThreadName=" + Thread.currentThread().getName() + ",release Lock ok");
        }
        System.out.println("---ThreadName=" + Thread.currentThread().getName() + ",out synchonice Lock ok");
    }

}
