package com.mylearn.thread.base;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/29
 * Time: 下午3:43
 * CopyRight: taobao
 * Descrption:
 * yield告诉当前真在执行的线程把运行机会交给线程池中拥有相同优先级的线程.
 * yield不能保证使得当前正在运行的线程迅速转换到可运行的状态
 * 它仅能使一个线程从运行状态转到可运行状态,而不是等待或阻塞状态.
 */

public class YieldTest {
    public static void main(String[] args) {
        Runnable producer = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Producer : Produced Item " + i);
                Thread.yield();
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Consumer : Produced Item " + i);
                Thread.yield();
            }
        };

        Thread p = new Thread(producer);
        p.setPriority(Thread.MIN_PRIORITY);
        p.start();
        Thread c = new Thread(consumer);
        c.setPriority(Thread.MAX_PRIORITY);
        c.start();
    }

    class Producer extends Thread {
        public void run() {

        }

    }
}
