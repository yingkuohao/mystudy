package com.mylearn.thread.atomic;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-8
 * Time: 下午12:06
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class CASTest {
    private int value = 0;

    public synchronized int get() {
        return value;
    }

    /**
     * 模拟硬件compareAndSwap，通过 synchronized保证
     * @param expectValue
     * @param newValue
     * @return
     */
    public synchronized int compareAndSwap(int expectValue, int newValue) {
        System.out.println("cureentThread:" +Thread.currentThread().getName()+"value="+value);
        int oldValue = value;
        if (oldValue == expectValue) { //对比期望值，如果相等就用新值赋值
            value = newValue;
        }
        return oldValue; //无论成功失败都返回旧值
    }

    /**
     * 判断CAS是否成功，通过 synchronized保证
     * @param expectValue
     * @param newValue
     * @return
     */
    public synchronized boolean compareAdnSet(int expectValue, int newValue) {
        return expectValue == compareAndSwap(expectValue, newValue);
    }

    public static void main(String args[]) {
        final CASTest casTest = new CASTest();
        final CountDownLatch countDownLatch = new CountDownLatch(3);
        Thread thread = new Thread(new Runnable() {
            public void run() {
                System.out.println("thead0开始执行");
                int i = 0;
                for (; ; ) {
                    i++;
                    System.out.println("thread0第" + i + "次执行，value="+casTest.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    if (casTest.compareAdnSet(0, 1)) {
                        System.out.println("thread0 CAS 成功，cureValue="+casTest.get());
                        countDownLatch.countDown();
                        return;
                    }

                }

            }
        });

        Thread thread1 = new Thread(new Runnable() {
                public void run() {
                    System.out.println("thead1开始执行");
                    int i = 0;
                    for (; ; ) {
                        i++;
                        System.out.println("thread1第" + i + "次执行，value="+casTest.get());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if (casTest.compareAdnSet(0, 1)) {
                            System.out.println("thread1 CAS 成功，cureValue="+casTest.get());
                            countDownLatch.countDown();
                            return;
                        }

                    }

                }
            });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("thead2开始执行");
                int i = 0;
                for (; ; ) {
                    i++;
                    System.out.println("thread2第" + i + "次执行，value="+casTest.get());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    if (casTest.compareAdnSet(1, 2)) {   //准备修改成2
                        System.out.println("thread2 CAS 成功，curValue=" + casTest.get());
                        countDownLatch.countDown();
                        return;
                    }

                }
            }
        });
        thread.start();
        thread1.start();
        thread2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("fianle:" + casTest.get());
    }
}

