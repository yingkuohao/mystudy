package com.mylearn.thread.lock;

import com.mylearn.thread.IntegerTest;
import com.mylearn.util.DateUtil;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-21
 * Time: ????1:35
 * CopyRight:360buy
 * Descrption:  ??????
 * To change this template use File | Settings | File Templates.
 */
public class LockTest {


    public static void main(String args[]) {
        Lock lock = new ReentrantLock();
        IntegerTest integerTest = new IntegerTest();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        System.out.println("begin----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(integerTest, lock, countDownLatch)).start();
//            System.out.println("??" + i + "?¦²?i lock = " + integerTest.getI());
        }
        System.out.println("done----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("i1 = " + integerTest.getI());

        /*      IntegerTest integerTest2 = new IntegerTest();
    System.out.println("begin----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
    for (int i = 0; i < 10; i++) {
        new Thread(new Worker2(integerTest2)).start();
        System.out.println("??" + i +  "?¦²?i synchronize = " + integerTest2.getI());
    }
    System.out.println("done----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
    System.out.println("i2 = " + integerTest2.getI());*/
    }


    static class Worker implements Runnable {

        private IntegerTest integerTest;

        private Lock lock;
        private CountDownLatch countDownLatch;

        Worker(IntegerTest integerTest, Lock lock, CountDownLatch countDownLatch) {
            this.integerTest = integerTest;
            this.lock = lock;
            this.countDownLatch = countDownLatch;
        }

        public IntegerTest getIntegerTest() {
            return integerTest;
        }

        public void setIntegerTest(IntegerTest integerTest) {
            this.integerTest = integerTest;
        }

        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10000000; i++) {
                    integerTest.increamentCommon(); //????
                }
                countDownLatch.countDown();//????????????????
            } finally {
                lock.unlock(); //finally???????????
            }

            System.out.println("i = " + integerTest.getI());
        }
    }


    static class Worker2 implements Runnable {
        private IntegerTest integerTest;

        Worker2(IntegerTest integerTest) {
            this.integerTest = integerTest;
        }

        public IntegerTest getIntegerTest() {
            return integerTest;
        }

        public void setIntegerTest(IntegerTest integerTest) {
            this.integerTest = integerTest;
        }

        public void run() {
            synchronized (integerTest) {
                for (int i = 0; i < 10000000; i++) {
                    integerTest.increamentCommon();
                }
            }
//            System.out.println("i = " + integerTest.getI());
        }
    }

}
