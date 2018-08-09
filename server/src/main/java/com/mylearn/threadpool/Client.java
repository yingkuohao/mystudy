package com.mylearn.threadpool;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-29
 * Time: ????2:30
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public static void main(String args[]) throws InterruptedException {
        ThreadPoolService threadPoolService = new ThreadPoolService();
        for (int i = 0; i < 10; i++) {
            threadPoolService.runTask(new SimpleTest(i + ""));
        }
        threadPoolService.start();

        Thread.sleep(1000); //?????????????????????

        threadPoolService.stop();
    }
}

