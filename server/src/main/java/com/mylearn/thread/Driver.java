package com.mylearn.thread;

import com.mylearn.util.DateUtil;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-20
 * Time: ????2:49
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Driver {


    public static void main(String args[]) {
        CountDownLatch startSignal = new CountDownLatch(1);  //?????????????????driver????????wokre???????????????????§Ö?worker??????§³?
        CountDownLatch doneSignal = new CountDownLatch(10);  //?????????????????????????driver?????????worker???????
        IntegerTest integerTest = new IntegerTest(0);
//        IntegerTest integerTest = new IntegerTest(new AtomicInteger(0));
        for (int i = 0; i < 10; i++) {
            new Thread(new Worker(startSignal, doneSignal, integerTest)).start();
        }

        System.out.println("begin----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
        startSignal.countDown(); //????????1?????0???????awati??????§µ?????????
        System.out.println("ing----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
        try {
            doneSignal.await();//????????????????
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("done----------" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
    }

    static class Worker implements Runnable {

        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        private IntegerTest integerTest;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal, IntegerTest integerTest) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.integerTest = integerTest;
        }

        public void run() {

            try {
                startSignal.await();  //????????????????????????????????????§Ø??

                doWork();
//                Automic();
                doneSignal.countDown(); //?????????????????????????????§Ö??????
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            for (int i = 0; i < 100000; i++) {
                integerTest.increament(); //??????????????????100000 * 10
//                integerTest.increamentCommon(); //???????????????????????? 100000 * 10
            }
            System.out.println("i = " + integerTest.getI());
        }


        /**
         * ??AtomicInteger??????CAS??????§¹???synchronize??
         */
        private void Automic() {

            final AtomicInteger atomic = new AtomicInteger(0);
            for (int i = 0; i < 100000; i++) {
                integerTest.getAtomicInteger().incrementAndGet();
            }
            System.out.println("AtomicInteger = " + integerTest.getAtomicInteger());
        }

    }

    static class IntegerTest {
        private int i = 0;
        private AtomicInteger atomicInteger;

        IntegerTest(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }

        IntegerTest(int i) {
            this.i = i;
        }

        public synchronized int increament() {
            return i++;
        }

        public int increamentCommon() {
            return i++;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public AtomicInteger getAtomicInteger() {
            return atomicInteger;
        }

        public void setAtomicInteger(AtomicInteger atomicInteger) {
            this.atomicInteger = atomicInteger;
        }
    }

}
