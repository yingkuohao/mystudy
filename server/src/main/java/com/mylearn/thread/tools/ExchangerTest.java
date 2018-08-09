package com.mylearn.thread.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-11
 * Time: 下午5:18
 * CopyRight:360buy
 * Descrption:
 * Exchanger是一个双方栅栏，各方在栅栏位置上交换数据，当两方执行不对称的操作时，Exchanger会非常有用，例如当一个线程向
 * 缓冲区写入数据，另一个线程从缓冲区读取数据。这些线程可以使用Exchanger来汇合并把满的缓冲区与空的缓冲区交换。当两个
 * 线程通过Exchanger交换对象时，这种交换就把这两个对象安全地发布给另一方。
 *   数据交换的时机取决于应用程序的响应需求。最简单的方案是，当缓冲区被填满时，由填充任务进行交换，当缓冲区为空时，
 *   由清空任务进行交换。这样会把需要交换的次数降至最低，但如果新数据的到达率不可预测，那么一些数据的处理过程就会延迟。
 *   另一个方法是，不仅当缓冲区将被填满时交换，并且当缓冲区被填充到一定程度并保持一段时间后，也进行交换。
 * To change this template use File | Settings | File Templates.
 */
public class ExchangerTest {
    public static void main(String args[]) {
        LinkedBlockingQueue emptyQueue = new LinkedBlockingQueue(10);  //生产者队列
        LinkedBlockingQueue fullQueue = new LinkedBlockingQueue(10);    //消费者队列
        Exchanger<LinkedBlockingQueue> exchanger = new Exchanger<LinkedBlockingQueue>();

        try {
            //初始化一些值到fullQueue
            fullQueue.put("inital1");
            fullQueue.put("inital2");
            fullQueue.put("inital3");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Thread product = new Thread(new Product(emptyQueue, exchanger));     //生产者线程负责生产
        Thread consumer = new Thread(new Consumer(fullQueue, exchanger));     //消费者线程负责消费
        product.start();
        consumer.start();
//        当生产者队列满，消费者队列为空的时候，两者交换，生产者变为空，消费者队列变为满，相当于一个批量操作。
    }

    static class Product implements Runnable {
        LinkedBlockingQueue emptyQueue;
        Exchanger<LinkedBlockingQueue> exchanger;

        Product(LinkedBlockingQueue emptyQueue, Exchanger<LinkedBlockingQueue> exchanger) {
            this.emptyQueue = emptyQueue;
            this.exchanger = exchanger;
        }

        public void run() {
            while (emptyQueue != null) {
                try {
                    emptyQueue.put("product1");
                    System.out.println(Thread.currentThread().getName() + "成功放入一个商品");
                    if (emptyQueue.size() == 10) {
                        System.out.println(Thread.currentThread().getName() + "begin change: emptyQueue.size=" + emptyQueue.size());
                        Thread.sleep(1000);
                        emptyQueue = exchanger.exchange(emptyQueue);
                        System.out.println(Thread.currentThread().getName() + "after change: emptyQueue.size=" + emptyQueue.size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        }
    }

    static class Consumer implements Runnable {
        LinkedBlockingQueue fullQueue;
        Exchanger<LinkedBlockingQueue> exchanger;

        Consumer(LinkedBlockingQueue fullQueue, Exchanger<LinkedBlockingQueue> exchanger) {
            this.fullQueue = fullQueue;
            this.exchanger = exchanger;
        }

        public void run() {
            while (fullQueue != null) {
                try {
                    Object object = fullQueue.take();
                    System.out.println(Thread.currentThread().getName() + "成功取到一个商品" + object);
                    if (fullQueue.isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + "begin change: fullQueue.size=" + fullQueue.size());
                        Thread.sleep(1000);
                        fullQueue = exchanger.exchange(fullQueue);
                        System.out.println(Thread.currentThread().getName() + "after change: fullQueue.size=" + fullQueue.size());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }

            }
        }
    }
}
