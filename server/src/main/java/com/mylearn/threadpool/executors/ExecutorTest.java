package com.mylearn.threadpool.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-28
 * Time: ????3:16
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class ExecutorTest {
    public static void main(String args[]) {

        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            Task task = new Task(i);
            executorService.execute(task);
        }
    }
}
