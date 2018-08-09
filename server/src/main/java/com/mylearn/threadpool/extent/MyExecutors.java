package com.mylearn.threadpool.extent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-9
 * Time: ????11:49
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class MyExecutors {

    public static MyThreadPoolExecutor newFixedThreadPool(int nThreads) {
        //new???????????threadPoolExecutor
         return new MyThreadPoolExecutor(nThreads, nThreads,
                                       0L, TimeUnit.MILLISECONDS,
                                       new LinkedBlockingQueue<Runnable>());
     }


}
