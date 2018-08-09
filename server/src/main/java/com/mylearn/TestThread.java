package com.mylearn;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-18
 * Time: ????4:11
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestThread {

    public static void main(String args[]) throws InterruptedException {
        long i = Instant.now().minusSeconds(9 * 60).getEpochSecond();
        System.out.println("i=" + i);
/*        for(int i = 0 ;i < 5; i++) {
                 new Thread(new Product()).start();
         }
         Thread.sleep(1000);

        for(int i = 0; i  < 5 ; i++) {
             new Thread(new  Consumer()).start();
            new Thread(new Product()).start();
        }*/
//        Thread thread = new Test();
//        thread.start();
        TestThread testThread = new TestThread();
        testThread.testThreadPool();

        Thread.sleep(1000 * 100);
    }


    public void testThreadPool() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 10, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 100; i++) {
           final int j=i;
            threadPool.execute(() -> savaAwardRecord(j));
        }
    }

    private void savaAwardRecord(Integer i) {
        try {
            System.out.println("Thread--" + Thread.currentThread().getName() + "--,execut ---[" + i + "]-----");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Test extends Thread {
    public void run() {
        System.out.println("11");
    }
}

class Global {
    public final static Lock lock = new ReentrantLock();
    public final static Condition notEmpty = lock.newCondition();
    public final static Condition notFull = lock.newCondition();
    public final static List<String> array = new ArrayList<String>(3);  //?????
}

class Consumer implements Runnable {


    public void run() {
        Global.lock.lock();
        while (Global.array.size() < 0) {

            try {
                Global.notEmpty.await();
                System.out.println("??????????????????????");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        Global.notFull.signalAll();
        System.out.println("????????????????" + Global.array.get(0));   //??????
        Global.lock.unlock();
        //To change body of implemented methods use File | Settings | File Templates.
    }
}


class Product implements Runnable {


    public void run() {
        Global.lock.lock();
        while (Global.array.size() > 3) {
            try {
                Global.notFull.await();
                System.out.println("???????????????????????");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
        Global.array.add("a!delicious food!");  //???????
        Global.notEmpty.signalAll();
        System.out.println("?????????????????????");
        Global.lock.unlock();


    }
}




