package com.mylearn.threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/13
 * Time: 上午11:30
 * CopyRight: taobao
 * Descrption:threadlocal无效实例
 */

public class ThreadLocalTest3 {

    private static Index num = new Index();
    //创建一个Index类型的本地变量
    private static ThreadLocal<Index> local = new ThreadLocal<Index>() {
        @Override
        protected Index initialValue() {
            return num;       //注意这里是引用,其实各个线程还是访问的同一个对象,所以达不到隔离变量的效果
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
