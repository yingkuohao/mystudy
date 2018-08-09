package com.mylearn.thread.tools;

import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-11
 * Time: ����5:18
 * CopyRight:360buy
 * Descrption:
 * Exchanger��һ��˫��դ����������դ��λ���Ͻ������ݣ�������ִ�в��ԳƵĲ���ʱ��Exchanger��ǳ����ã����統һ���߳���
 * ������д�����ݣ���һ���̴߳ӻ�������ȡ���ݡ���Щ�߳̿���ʹ��Exchanger����ϲ������Ļ�������յĻ�����������������
 * �߳�ͨ��Exchanger��������ʱ�����ֽ����Ͱ�����������ȫ�ط�������һ����
 *   ���ݽ�����ʱ��ȡ����Ӧ�ó������Ӧ������򵥵ķ����ǣ���������������ʱ�������������н�������������Ϊ��ʱ��
 *   �����������н��������������Ҫ�����Ĵ���������ͣ�����������ݵĵ����ʲ���Ԥ�⣬��ôһЩ���ݵĴ�����̾ͻ��ӳ١�
 *   ��һ�������ǣ���������������������ʱ���������ҵ�����������䵽һ���̶Ȳ�����һ��ʱ���Ҳ���н�����
 * To change this template use File | Settings | File Templates.
 */
public class ExchangerTest {
    public static void main(String args[]) {
        LinkedBlockingQueue emptyQueue = new LinkedBlockingQueue(10);  //�����߶���
        LinkedBlockingQueue fullQueue = new LinkedBlockingQueue(10);    //�����߶���
        Exchanger<LinkedBlockingQueue> exchanger = new Exchanger<LinkedBlockingQueue>();

        try {
            //��ʼ��һЩֵ��fullQueue
            fullQueue.put("inital1");
            fullQueue.put("inital2");
            fullQueue.put("inital3");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        Thread product = new Thread(new Product(emptyQueue, exchanger));     //�������̸߳�������
        Thread consumer = new Thread(new Consumer(fullQueue, exchanger));     //�������̸߳�������
        product.start();
        consumer.start();
//        �������߶������������߶���Ϊ�յ�ʱ�����߽����������߱�Ϊ�գ������߶��б�Ϊ�����൱��һ������������
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
                    System.out.println(Thread.currentThread().getName() + "�ɹ�����һ����Ʒ");
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
                    System.out.println(Thread.currentThread().getName() + "�ɹ�ȡ��һ����Ʒ" + object);
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
