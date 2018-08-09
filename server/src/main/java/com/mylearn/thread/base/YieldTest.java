package com.mylearn.thread.base;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/29
 * Time: ����3:43
 * CopyRight: taobao
 * Descrption:
 * yield���ߵ�ǰ����ִ�е��̰߳����л��ύ���̳߳���ӵ����ͬ���ȼ����߳�.
 * yield���ܱ�֤ʹ�õ�ǰ�������е��߳�Ѹ��ת���������е�״̬
 * ������ʹһ���̴߳�����״̬ת��������״̬,�����ǵȴ�������״̬.
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
