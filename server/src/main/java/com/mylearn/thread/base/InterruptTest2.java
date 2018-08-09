package com.mylearn.thread.base;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/5/3
 * Time: 上午11:45
 * CopyRight: taobao
 * Descrption:
 */

public class InterruptTest2 {

    public static void main(String[] args) {
        InterruptTest2 interruptTest2 = new InterruptTest2();
//        interruptTest2.methodA();
        interruptTest2.methodB();
    }

    //此方法,我们调用interrupt方法后,程序仍在运行,如果不强制结束,程序将一直运行下去

    public void methodA() {
        Thread t = new Thread(new ATask());
        t.start();
        //运行一断时间中断线程
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("****************************");
        System.out.println("Interrupted Thread!");
        System.out.println("****************************");
        t.interrupt();
    }
    class ATask implements Runnable {
        private double d = 0.0;

        @Override
        public void run() {

            while (true) {
                System.out.println("I am running!");
                for (int i = 0; i < 900000; i++) {
                    d = d + (Math.PI + Math.E) / d;
                }
                //给线程调度器可以切换到其它进程的信号
                Thread.yield();
            }
        }
    }


    //此测试可以通过Thread.interrupted来检查是否发生中断,
    public void methodB() {
            Thread t = new Thread(new BTask());
            t.start();
            //运行一断时间中断线程
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("****************************");
            System.out.println("Interrupted Thread!");
            System.out.println("****************************");
            t.interrupt();
        }
        class BTask implements Runnable {
            private double d = 0.0;

            @Override
            public void run() {

                while (!Thread.interrupted()) {
                    System.out.println("I am running!");
                    for (int i = 0; i < 900000; i++) {
                        d = d + (Math.PI + Math.E) / d;
                    }
                    //给线程调度器可以切换到其它进程的信号
                    Thread.yield();
                }
            }
        }


}
