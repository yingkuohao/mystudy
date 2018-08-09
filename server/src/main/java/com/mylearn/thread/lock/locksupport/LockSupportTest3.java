package com.mylearn.thread.lock.locksupport;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/27
 * Time: 下午3:12
 * CopyRight: taobao
 * Descrption:
 * 本test主要测试上下文切换(context switch)带来的消耗
 * 通过定义两个线程,开始时，第一个线程挂起自己; 第二个线程唤醒第一个线程，再挂起自己;
 * 第一个线程醒来之后唤醒第二个线程, 再挂起自己. 就这样一来一往,互相唤醒对方, 挂起自己.
 *
 * 通过实现发现 :
 * 这么简单的for循环, 线性执行会非常快,不需要1秒, 而执行这段程序需要几十秒的耗时. 每个上下文切换需要耗去十几us的时间,这对于程序吞吐量的影响很大.
 */

public class LockSupportTest3 {
    static final int RUNS = 3;
    static final int ITERATES = 1000000;
    static AtomicReference turn = new AtomicReference();

    static final class WorkerThread extends Thread {
        volatile Thread other;
        volatile int nparks;

        public void run() {
            final AtomicReference t = turn;
            final Thread other = this.other;
            if (turn == null || other == null)
                throw new NullPointerException();
            int p = 0;
            for (int i = 0; i < ITERATES; i++) {
                while (!t.compareAndSet(other, this)) {     //比较t.value是否是other,如果是,则把自身设为t.
                    LockSupport.park();        //如果发现CAS失败,则当前线程阻塞
                    ++p;
                }
                LockSupport.unpark(other);    //如果CAS成功,则唤醒other线程
            }
            LockSupport.unpark(other);
            nparks = p;
            System.out.println("parks:" + p);
        }
    }

    static void test() throws InterruptedException {
        WorkerThread a = new WorkerThread();
        WorkerThread b = new WorkerThread();
        a.other = b;
        b.other = a;       //CAS比较时用到
        turn.set(a);       //默认把turn指向a,则两个线程中的t都是a,则a进行CAS时就会返回false,b就会成功
        long startTime = System.nanoTime();
        a.start();
        b.start();
        a.join();    //通过join阻塞主线程
        b.join();
        long endTime = System.nanoTime();
        int parkNum = a.nparks + b.nparks;
        System.out.println("Average time: " + ((endTime - startTime) / parkNum)
                + "ns");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < RUNS; i++) {
            test();
        }
    }
}
