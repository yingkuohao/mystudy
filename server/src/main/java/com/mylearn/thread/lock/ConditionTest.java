package com.mylearn.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-24
 * Time: ????1:58
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class ConditionTest {
    public static void main(String args[]) {
        Request request = new Request();
        Response response = new Response();
        request.start();
        response.start();
    }
}

class GlobalV {
    public final static Lock lock = new ReentrantLock();
    public final static Condition conditon = lock.newCondition();
    public static boolean to_proceed = false;

}

class Response extends Thread {

    public void run() {
        while (true) {
            GlobalV.lock.lock();
            try {
                while (!GlobalV.to_proceed) {
                    GlobalV.conditon.await();
                }
                System.out.println("Response:finish a job");

                GlobalV.to_proceed = false;

            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } finally {
                GlobalV.lock.unlock();
            }
        }
    }
}

class Request extends Thread {

    public void run() {
        while (true) {
            GlobalV.lock.lock();
            try {
                GlobalV.to_proceed = true;
                GlobalV.conditon.signalAll();
                System.out.println("Request:send a job to Response");
            } finally {
                GlobalV.lock.unlock();
            }
            try{
               Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
