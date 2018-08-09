package com.mylearn.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-25
 * Time: ????10:52
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class ReentranLockTest {

    public static void main(String args[]) {

        Lock lock = new ReentrantLock();

        WorkerOne workerOne = new WorkerOne(lock);
        workerOne.setName("WorkerOne");

        WorkerTwo workerTwo = new WorkerTwo(lock);
        workerTwo.setName("WorkerTwo");

        workerTwo.start();   //}?????????????

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        workerOne.start();
    }

}

class WorkerOne extends Thread {

    private Lock lock;

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    WorkerOne(Lock lock) {
        this.lock = lock;
    }

    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + ":step into critical section,try");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":step into critical section,begin");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println(Thread.currentThread().getName() + ":step into critical section,end");
        } finally {
            lock.unlock();
        }
    }
}


class WorkerTwo extends Thread {

    private Lock lock;

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    WorkerTwo(Lock lock) {
        this.lock = lock;
    }

    private void sayHello() {
        lock.lock();       //???????????????????????
        try {
            System.out.println(Thread.currentThread().getName() + ":call say Hello()");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            lock.unlock();
        }

    }

    public void run() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ":step into critical section,begin");
            sayHello(); //??????????
            System.out.println(Thread.currentThread().getName() + ":step into critical section, end");
        } finally {
            lock.unlock();
        }
    }
}
