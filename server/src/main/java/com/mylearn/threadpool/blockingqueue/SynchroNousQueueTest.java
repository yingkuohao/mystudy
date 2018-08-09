package com.mylearn.threadpool.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-29
 * Time: ????11:06
 * CopyRight:360buy
 * Descrption:  ??????§µ??????????????????
 * To change this template use File | Settings | File Templates.
 */
public class SynchroNousQueueTest {
    public static void main(String args[]) {
//        final SynchronousQueue synchronousQueue = new SynchronousQueue();
        SynchroNousQueueTest synchroNousQueueTest = new SynchroNousQueueTest();
        final MyShnchronouseQueue<String> synchronousQueue = synchroNousQueueTest.new MyShnchronouseQueue<String>();
        //1?????????????????
        Thread threadPut = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        synchronousQueue.put(i + "");
                        System.out.println("synchronousQueue,insert element:" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        //2?????????????????
        Thread threadTask = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        synchronousQueue.take();
                        System.out.println("synchronousQueue,output element:" + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        threadPut.start();
        threadTask.start();
    }

    class MyShnchronouseQueue<E> {
        Lock lock = new ReentrantLock();
        Condition isFull = lock.newCondition();
        Condition isEmpty = lock.newCondition();
        boolean flag = false;   //???????
        E item = null;     //?????????

        public void put(E e) throws InterruptedException {
            lock.lock();
            try {
                while (flag) {    // ???????true???put??????await
                    isEmpty.await();
                }
                //???????false?????true??item?????????????????
                flag = true;
                item = e;
                isFull.signalAll();
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public synchronized E take() throws InterruptedException {
            lock.lock();
            try {
                while (!flag) {      // ???????false???take??????await
                    isFull.await();
                }
                //???????true?????false?????item?????????????????
                flag = false;
                E e = item;
                item = null;
                isEmpty.signalAll();
                return e;
            } catch (Exception e1) {
                e1.printStackTrace();
            } finally {
                lock.unlock();
            }
            return null;
        }
    }
}
