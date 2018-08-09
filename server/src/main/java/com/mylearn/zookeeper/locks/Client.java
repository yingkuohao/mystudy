package com.mylearn.zookeeper.locks;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 18/3/19
 * Time: ÏÂÎç3:07
 * CopyRight: ying
 * Descrption:
 */

public class Client {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            MutexObj.lock.lock();
            try {
                System.out.println("before wait");
                MutexObj.condition.await();
                System.out.println("afeter wait");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                MutexObj.lock.unlock();
            }
        });

        thread.start();

        Thread thread2 = new Thread(() -> {
            MutexObj.lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("before signal");
                MutexObj.condition.signal();
                System.out.println("after signal");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                MutexObj.lock.unlock();
            }
        });
        thread2.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
