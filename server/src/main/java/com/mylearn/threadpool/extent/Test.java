package com.mylearn.threadpool.extent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-9
 * Time: ????12:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String args[]) {
        final AtomicInteger atomicInteger = new AtomicInteger(0);
        MyThreadPoolExecutor executorService =  MyExecutors.newFixedThreadPool(5);



          for (int i = 0; i < 15;i++) {
              Thread t = new Thread(new Runnable() {
                        public void run() {
                            try {
                                System.out.println(Thread.currentThread().getName() + "????????1??");
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                            }
                            atomicInteger.incrementAndGet();
                        }
                    } );
             executorService.execute(t);
          }


        executorService.pause();
        System.out.println("????????1??");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("????????1??????????");
        executorService.resume();
    }



}
