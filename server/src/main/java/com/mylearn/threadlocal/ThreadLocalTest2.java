package com.mylearn.threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/13
 * Time: 上午11:30
 * CopyRight: taobao
 * Descrption:   threadlocal正确使用例子
 */

public class ThreadLocalTest2 {
    public static final ThreadLocal<Integer> local = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread threads[] =new Thread[5];
        createThreads(threads);

        for(Thread thread: threads) {
            thread.start();
        }
    }

    private static void createThreads(Thread[] threads) {
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    int num = local.get();
                    for (int j = 0; j <5; j++) {
                              num++;
                    }
                    local.set(num);
                    System.out.println(Thread.currentThread().getName() +":"+local.get());
                }
            },"Thread-"+i)  ;
        }
    }

}
