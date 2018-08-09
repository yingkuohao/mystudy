package com.mylearn.thread.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????10:41
 * CopyRight:360buy
 * Descrption:  ????AtomicInteger
 * To change this template use File | Settings | File Templates.
 */
public class AtomicIntegerCompareTest {
    private int value;

    public AtomicIntegerCompareTest(int value) {
        this.value = value;
    }

    public synchronized int increase() {
        return value++;
    }


    public static void main(String args[]) {

        final AtomicIntegerCompareTest test = new AtomicIntegerCompareTest(0);
        final CountDownLatch latch = new CountDownLatch(3);   //??????????????????????
        final CountDownLatch latchMain = new CountDownLatch(1);

        //?????????????value????synchronized????????????
        for (int i = 0; i < 3; i++) {
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                        latchMain.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 1000 * 1000; i++) {
                        test.increase();
                    }
                    latch.countDown();
                }
            });
            t1.start();
        }
        latchMain.countDown();    //???????????
        long start = System.nanoTime();
        try {
            latch.await();   //?????countDownLatch
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        long end = System.nanoTime();


        System.out.println("synchronized time elapse:" + (end - start));
        System.out.println("AtomicIntegerCompareTest.value=" + test.getValue());
        final CountDownLatch latch1 = new CountDownLatch(3);
        final CountDownLatch latch2Start = new CountDownLatch(1);

        //????????????AtomicInteger4????????CAS
        final AtomicInteger atomic = new AtomicInteger(0);
        for (int i = 0; i < 3; i++) {
            Thread t4 = new Thread() {
                @Override
                public void run() {
                    try {
                        latch2Start.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int i = 0; i < 1000 * 1000; i++) {
                        atomic.incrementAndGet();
                    }
                    latch1.countDown();
                }
            };
            t4.start();
        }
        latch2Start.countDown();
        long start1 = System.nanoTime();
        try {
            latch1.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();//
        }
        long end1 = System.nanoTime();
        System.out.println("AtomicInteger time elapse:" + (end1 - start1));
        System.out.println("AtomicIntegerCompareTest.value=" + test.getValue());
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
