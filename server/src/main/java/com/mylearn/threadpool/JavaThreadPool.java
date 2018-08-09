package com.mylearn.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-19
 * Time: ????3:57
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class JavaThreadPool {
    public static void main(String args[]) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
//        ExecutorService pool = Executors.newSingleThreadExecutor(2);
        Thread t1 = new Thread(new MyThread("Thread 1"));
        Thread t2 = new Thread(new MyThread("Thread 2"));
        Thread t3 = new Thread(new MyThread("Thread 3"));
        Thread t4 = new Thread(new MyThread("Thread 4"));
        Thread t5 = new Thread(new MyThread("Thread 5"));

        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.shutdown();
    }
}

class MyThread implements Runnable{

     private String name;

    MyThread(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + "??§³?????");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
