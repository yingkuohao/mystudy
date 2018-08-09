package com.mylearn.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-24
 * Time: ????2:14
 * CopyRight:360buy
 * Descrption:  ??????????
 * To change this template use File | Settings | File Templates.
 */
public class ConditionTest2 {

    public static void main(String args[]) throws InterruptedException {

        for(int i = 0 ;i < 5; i++) {
                new Thread(new Product()).start();
        }
        Thread.sleep(1000);

        for(int i = 0 ;i < 15; i++) {
            new Thread(new Consumer()).start();
            new Thread(new Product()).start();
            Thread.sleep(1000);
        }

    }
}

class GlobalRule {
    public final static Lock lock = new ReentrantLock();
    public final static Condition notEmpty = lock.newCondition();
    public final static Condition notFull = lock.newCondition();
    public final static List<String> array = new ArrayList<String>(3);  //?????
}

class Consumer implements Runnable {

    public void run() {

        GlobalRule.lock.lock();
        try {
            while (GlobalRule.array.size() <= 0) {
                try {
                    GlobalRule.notEmpty.await();
                    System.out.println("??????????????????????");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            System.out.println("????????????????" + GlobalRule.array.get(0));   //??????
            GlobalRule.notFull.signalAll();    //?????????????????????????????????????

        } finally {
            GlobalRule.lock.unlock();
        }

    }
}

class Product implements Runnable{

    public void run() {
        GlobalRule.lock.lock();
        try{

            while(GlobalRule.array.size() >=3) {
                try {
                    GlobalRule.notFull.await();
                    System.out.println("???????????????????????");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

            GlobalRule.array.add("a!delicious food!");  //???????
            System.out.println("?????????????????????");
            GlobalRule.notEmpty.signalAll();  //??????????????????????????????????
        } finally {
            GlobalRule.lock.unlock();
        }

    }
}
