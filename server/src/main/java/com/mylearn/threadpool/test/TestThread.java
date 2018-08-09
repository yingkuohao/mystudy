package com.mylearn.threadpool.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/3/17
 * Time: ÏÂÎç7:31
 * CopyRight: taobao
 * Descrption:
 */

public class TestThread {
    public static void main(String[] args) {
        final TestLock testLock=new TestLock();
        final CountDownLatch countDownLatch=new CountDownLatch(1);
//        testLock(testLock, countDownLatch);
        testSync(testLock, countDownLatch);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count donnw");
        countDownLatch.countDown();
    }


    private static void testSync(final TestLock testLock, final CountDownLatch countDownLatch) {
        for(int i=0;i<5;i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        System.out.println("await");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    testLock.testSync(1l);
                }
            })  ;

            thread.start();
        }
    }

    private static void testLock(final TestLock testLock, final CountDownLatch countDownLatch) {
        for(int i=0;i<5;i++) {
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        System.out.println("await");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    testLock.test();
                }
            })  ;

            thread.start();
        }
    }
}
