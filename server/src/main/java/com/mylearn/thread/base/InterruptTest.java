package com.mylearn.thread.base;

import com.mylearn.threadlocal.demo1.TheadLocalTest;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/5/3
 * Time: ����10:46
 * CopyRight: taobao
 * Descrption:Thread.interrupt����ʵ��
 * java���ж���ʵ��һ��Э������,Ҳ����˵�����̶߳����interrupt��������һ�����ж����������е��߳�,��ֻ��Ҫ���߳��Լ��ں��ʵ�ʱ���ж��Լ�.
 * ÿ���̶߳���һ��boolean���ж�״̬(���״̬����Thread��������),intterrupt��������ֻ�ǽ���״̬��Ϊtrue.
 * ������������е��̵߳���interrupt������������ֹ��,ֻ�Ǹı���interrupt��ʶ��.
 *
 */

public class InterruptTest extends Thread {


    public static void main(String[] args) throws InterruptedException {
        InterruptTest interruptTest = new InterruptTest();
//        interruptTest.test1();
        interruptTest.test2();

    }

    public void test1() {      //���ñ����Ļ�����Э��
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
        volatile boolean stop = false;//�߳��ж��ź���

        public void run() {
            while (!stop) {
                System.out.println("Thead is running.........");
                long time = System.currentTimeMillis();
                while (!(System.currentTimeMillis() - time < 1000)) {
                          //ģ��sleep����
                }
            }
            System.out.println("Thread existing under request...");
        }
    }


    public void test2() {    //����interrupt��Э��
        try {
            Thread2 interruptTest = new Thread2();
            System.out.println("Starting thread.....");
            interruptTest.start();

            Thread.sleep(3000);

            System.out.println("Asking thread to stop...");
            interruptTest.interrupt();      //����Э�����ź�
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
                    //����߳�����,������ȥ����ж��ź���stop����,����thread.interrupt()��ʹ�����߳�
                    //�������ĵط��׳��쳣,�������̴߳�����״̬�������,�������쳣������Ӧ�Ĵ���
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Thead is interrupted...");
                    System.out.println(this.isInterrupted()); //false
                    Thread.currentThread().interrupt();       //�в��ж����Լ�����,�����Ҫ���ж��߳�,����Ҫ���������ж�λ,�������Ҫ,���õ���
                }
            }
            System.out.println("Thread existing under request...");
        }
    }
}
