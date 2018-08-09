package com.mylearn.thread.base;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/5/3
 * Time: ����11:45
 * CopyRight: taobao
 * Descrption:
 */

public class InterruptTest2 {

    public static void main(String[] args) {
        InterruptTest2 interruptTest2 = new InterruptTest2();
//        interruptTest2.methodA();
        interruptTest2.methodB();
    }

    //�˷���,���ǵ���interrupt������,������������,�����ǿ�ƽ���,����һֱ������ȥ

    public void methodA() {
        Thread t = new Thread(new ATask());
        t.start();
        //����һ��ʱ���ж��߳�
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
                //���̵߳����������л����������̵��ź�
                Thread.yield();
            }
        }
    }


    //�˲��Կ���ͨ��Thread.interrupted������Ƿ����ж�,
    public void methodB() {
            Thread t = new Thread(new BTask());
            t.start();
            //����һ��ʱ���ж��߳�
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
                    //���̵߳����������л����������̵��ź�
                    Thread.yield();
                }
            }
        }


}
