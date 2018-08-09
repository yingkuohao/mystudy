package com.mylearn.threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/13
 * Time: ����11:30
 * CopyRight: taobao
 * Descrption:threadlocal��Чʵ��
 */

public class ThreadLocalTest3 {

    private static Index num = new Index();
    //����һ��Index���͵ı��ر���
    private static ThreadLocal<Index> local = new ThreadLocal<Index>() {
        @Override
        protected Index initialValue() {
            return num;       //ע������������,��ʵ�����̻߳��Ƿ��ʵ�ͬһ������,���Դﲻ�����������Ч��
        }
    };

    public static void main(String[] args) {
        Thread threads[] = new Thread[5];
        createThreads(threads);

        for (Thread thread : threads) {
            thread.start();
        }
    }

    private static void createThreads(Thread[] threads) {
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    Index index = local.get();
                    for (int j = 0; j < 5; j++) {
                        index.increase();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + index.getNum());
                }
            }, "Thread-" + i);
        }
    }

    static class Index {
        int num;

        public void increase() {
            num++;
        }

        public int getNum() {
            return num;
        }
    }

}
