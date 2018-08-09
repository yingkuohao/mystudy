package com.mylearn.thread.base;

import com.mylearn.threadlocal.demo1.TheadLocalTest;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/5/3
 * Time: 上午10:46
 * CopyRight: taobao
 * Descrption:Thread.interrupt方法实例
 * java的中断其实是一种协作机制,也就是说调用线程对象的interrupt方法并不一定就中断了正在运行的线程,他只是要求线程自己在合适的时机中断自己.
 * 每个线程都有一个boolean的中断状态(这个状态不在Thread的属性上),intterrupt方法仅仅只是将其状态设为true.
 * 比如对正常运行的线程调用interrupt方法并不能终止它,只是改变了interrupt标识符.
 *
 */

public class InterruptTest extends Thread {


    public static void main(String[] args) throws InterruptedException {
        InterruptTest interruptTest = new InterruptTest();
//        interruptTest.test1();
        interruptTest.test2();

    }

    public void test1() {      //采用变量的机制来协作
        try {
            Thread1 interruptTest = new Thread1();
            System.out.println("Starting thread.....");
            interruptTest.start();

            Thread.sleep(3000);

            System.out.println("Asking thread to stop...");
            interruptTest.stop = true;
            Thread.sleep(3000);
            System.out.println("Stopping application...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Thread1 extends Thread {
        volatile boolean stop = false;//线程中断信号量

        public void run() {
            while (!stop) {
                System.out.println("Thead is running.........");
                long time = System.currentTimeMillis();
                while (!(System.currentTimeMillis() - time < 1000)) {
                          //模拟sleep方法
                }
            }
            System.out.println("Thread existing under request...");
        }
    }


    public void test2() {    //采用interrupt来协作
        try {
            Thread2 interruptTest = new Thread2();
            System.out.println("Starting thread.....");
            interruptTest.start();

            Thread.sleep(3000);

            System.out.println("Asking thread to stop...");
            interruptTest.interrupt();      //发出协作新信号
            Thread.sleep(3000);
            System.out.println("Stopping application...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Thread2 extends Thread {

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thead is running.........");

                try {
                    //如果线程阻塞,将不会去检测中断信号量stop变量,所以thread.interrupt()会使阻塞线程
                    //从阻塞的地方抛出异常,让阻塞线程从阻塞状态逃离出来,井进行异常块里相应的处理
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thead is interrupted...");
                    System.out.println(this.isInterrupted()); //false
                    Thread.currentThread().interrupt();       //中不中断由自己决定,如果需要真中断线程,则需要重新设置中断位,如果不需要,则不用调用
                }
            }
            System.out.println("Thread existing under request...");
        }
    }
}
