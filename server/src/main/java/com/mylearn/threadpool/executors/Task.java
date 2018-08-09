package com.mylearn.threadpool.executors;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-28
 * Time: ????3:16
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Task implements Runnable {

    int k;

    public Task(int k) {
        this.k = k;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("?????§µ?" + k +"----"+Thread.currentThread().getName());
        }
    }
}
