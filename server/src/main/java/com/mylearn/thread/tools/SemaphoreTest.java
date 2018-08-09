package com.mylearn.thread.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-11
 * Time: ????4:05
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class SemaphoreTest {

    public static void main(String args[]) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(3);   //??????
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task(semaphore));   //10?????
            executorService.submit(thread);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        executorService.shutdown();
    }

    static class Task implements Runnable {

        private Semaphore semaphore;

        Task(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public Semaphore getSemaphore() {
            return semaphore;
        }

        public void setSemaphore(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                semaphore.acquire();  //?????????????????
                System.out.println(Thread.currentThread().getName() + "dosomething!"+System.currentTimeMillis());
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();     //????????????????????
            }
        }
    }

}
