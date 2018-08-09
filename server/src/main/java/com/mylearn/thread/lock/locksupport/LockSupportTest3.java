package com.mylearn.thread.lock.locksupport;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/27
 * Time: ����3:12
 * CopyRight: taobao
 * Descrption:
 * ��test��Ҫ�����������л�(context switch)����������
 * ͨ�����������߳�,��ʼʱ����һ���̹߳����Լ�; �ڶ����̻߳��ѵ�һ���̣߳��ٹ����Լ�;
 * ��һ���߳�����֮���ѵڶ����߳�, �ٹ����Լ�. ������һ��һ��,���໽�ѶԷ�, �����Լ�.
 *
 * ͨ��ʵ�ַ��� :
 * ��ô�򵥵�forѭ��, ����ִ�л�ǳ���,����Ҫ1��, ��ִ����γ�����Ҫ��ʮ��ĺ�ʱ. ÿ���������л���Ҫ��ȥʮ��us��ʱ��,����ڳ�����������Ӱ��ܴ�.
 */

public class LockSupportTest3 {
    static final int RUNS = 3;
    static final int ITERATES = 1000000;
    static AtomicReference turn = new AtomicReference();

    static final class WorkerThread extends Thread {
        volatile Thread other;
        volatile int nparks;

        public void run() {
            final AtomicReference t = turn;
            final Thread other = this.other;
            if (turn == null || other == null)
                throw new NullPointerException();
            int p = 0;
            for (int i = 0; i < ITERATES; i++) {
                while (!t.compareAndSet(other, this)) {     //�Ƚ�t.value�Ƿ���other,�����,���������Ϊt.
                    LockSupport.park();        //�������CASʧ��,��ǰ�߳�����
                    ++p;
                }
                LockSupport.unpark(other);    //���CAS�ɹ�,����other�߳�
            }
            LockSupport.unpark(other);
            nparks = p;
            System.out.println("parks:" + p);
        }
    }

    static void test() throws InterruptedException {
        WorkerThread a = new WorkerThread();
        WorkerThread b = new WorkerThread();
        a.other = b;
        b.other = a;       //CAS�Ƚ�ʱ�õ�
        turn.set(a);       //Ĭ�ϰ�turnָ��a,�������߳��е�t����a,��a����CASʱ�ͻ᷵��false,b�ͻ�ɹ�
        long startTime = System.nanoTime();
        a.start();
        b.start();
        a.join();    //ͨ��join�������߳�
        b.join();
        long endTime = System.nanoTime();
        int parkNum = a.nparks + b.nparks;
        System.out.println("Average time: " + ((endTime - startTime) / parkNum)
                + "ns");
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < RUNS; i++) {
            test();
        }
    }
}
