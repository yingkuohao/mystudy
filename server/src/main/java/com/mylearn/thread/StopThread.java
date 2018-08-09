package com.mylearn.thread;

import java.util.concurrent.TimeUnit;

/**
 * Package:com.mylearn.thread
 * User: yingkuohao
 * Date: 12-5-3
 * Time: ????11:12
 * CopyRight:360buy
 * Descrption:
 */
public class StopThread {
    private static boolean stopRequested;

    public static void main(String args[]) throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("child thead");
                int i = 0;
                while (!stopRequested) {
                    i++;
                    System.out.println("`1111");
                }
            }
        });
        backgroundThread.start();
        System.out.println("main thead1");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("main thead2");
        stopRequested = true;

    }
}
