package com.mylearn.thread.lock.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/27
 * Time: 下午1:47
 * CopyRight: taobao
 * Descrption:
 *
 * http://www.tuicool.com/articles/EF3UNb
 */

public class LockSupportTest1 {
    private static Thread mainThread;


    public static void main(String[] args) {
        mainThread  = Thread.currentThread();

        ThreadA childThread = new ThreadA("childThread");
        System.out.println("---1. start child thread!---");
        childThread.start();

        System.out.println("----2. main thread block----");
        LockSupport.park(mainThread);      //阻塞主线程
        System.out.println("-----4.main thread continue---");
    }

    static  class ThreadA extends  Thread {
        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"3. wake up other thread");
            LockSupport.unpark(mainThread);     //唤醒主线程
        }
    }
}
