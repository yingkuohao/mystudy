package com.mylearn.thread.tools;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-7-15
 * Time: 下午3:54
 * CopyRight:360buy
 * Descrption:
 * CyclicBarrier是一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点（common barrier point）。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时CyclicBarrier很有用。因为该barrier在
 * 释放等待线程后可以重用，所以称它为循环的barrier。
 * <p/>
 * CyclicBarrier支持一个可选的Runnable命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），
 * 该命令可在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作很有用。
 * To change this template use File | Settings | File Templates.
 */
public class CyclicBarrierTest {

    public static void main(String args[]) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("饭后来一颗益达");  //后续线程执行

            }
        });

        Future future = executorService.submit(new Task(cyclicBarrier));
        executorService.submit(new Task(cyclicBarrier));
        executorService.shutdown();
        System.out.println("mainfangfa ");
    }


    private static class Task implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public CyclicBarrier getCyclicBarrier() {
            return cyclicBarrier;
        }

        public void setCyclicBarrier(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {

            System.out.println(Thread.currentThread().getName() + "吃早饭");   //执行子任务，然后wait
            try {
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "吃午饭");   //执行子任务，然后wait
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "吃晚饭");   //执行子任务，然后wait
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
