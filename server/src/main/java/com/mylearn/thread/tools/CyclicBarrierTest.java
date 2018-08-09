package com.mylearn.thread.tools;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-7-15
 * Time: ����3:54
 * CopyRight:360buy
 * Descrption:
 * CyclicBarrier��һ��ͬ�������࣬������һ���̻߳���ȴ���ֱ������ĳ���������ϵ㣨common barrier point����
 * ���漰һ��̶���С���̵߳ĳ����У���Щ�̱߳��벻ʱ�ػ���ȴ�����ʱCyclicBarrier�����á���Ϊ��barrier��
 * �ͷŵȴ��̺߳�������ã����Գ���Ϊѭ����barrier��
 * <p/>
 * CyclicBarrier֧��һ����ѡ��Runnable�����һ���߳��е����һ���̵߳���֮�󣨵����ͷ������߳�֮ǰ����
 * ���������ÿ�����ϵ�����һ�Ρ����ڼ������в����߳�֮ǰ���¹���״̬�������ϲ��������á�
 * To change this template use File | Settings | File Templates.
 */
public class CyclicBarrierTest {

    public static void main(String args[]) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("������һ�����");  //�����߳�ִ��

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

            System.out.println(Thread.currentThread().getName() + "���緹");   //ִ��������Ȼ��wait
            try {
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "���緹");   //ִ��������Ȼ��wait
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "����");   //ִ��������Ȼ��wait
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
