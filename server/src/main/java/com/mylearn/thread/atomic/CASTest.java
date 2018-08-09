package com.mylearn.thread.atomic;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-8
 * Time: ����12:06
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
     * ģ��Ӳ��compareAndSwap��ͨ�� synchronized��֤
     * @param expectValue
     * @param newValue
     * @return
     */
    public synchronized int compareAndSwap(int expectValue, int newValue) {
        System.out.println("cureentThread:" +Thread.currentThread().getName()+"value="+value);
        int oldValue = value;
        if (oldValue == expectValue) { //�Ա�����ֵ�������Ⱦ�����ֵ��ֵ
            value = newValue;
        }
        return oldValue; //���۳ɹ�ʧ�ܶ����ؾ�ֵ
    }

    /**
     * �ж�CAS�Ƿ�ɹ���ͨ�� synchronized��֤
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
                System.out.println("thead0��ʼִ��");
                int i = 0;
                for (; ; ) {
                    i++;
                    System.out.println("thread0��" + i + "��ִ�У�value="+casTest.get());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    if (casTest.compareAdnSet(0, 1)) {
                        System.out.println("thread0 CAS �ɹ���cureValue="+casTest.get());
                        countDownLatch.countDown();
                        return;
                    }

                }

            }
        });

        Thread thread1 = new Thread(new Runnable() {
                public void run() {
                    System.out.println("thead1��ʼִ��");
                    int i = 0;
                    for (; ; ) {
                        i++;
                        System.out.println("thread1��" + i + "��ִ�У�value="+casTest.get());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        }
                        if (casTest.compareAdnSet(0, 1)) {
                            System.out.println("thread1 CAS �ɹ���cureValue="+casTest.get());
                            countDownLatch.countDown();
                            return;
                        }

                    }

                }
            });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.out.println("thead2��ʼִ��");
                int i = 0;
                for (; ; ) {
                    i++;
                    System.out.println("thread2��" + i + "��ִ�У�value="+casTest.get());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    if (casTest.compareAdnSet(1, 2)) {   //׼���޸ĳ�2
                        System.out.println("thread2 CAS �ɹ���curValue=" + casTest.get());
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

