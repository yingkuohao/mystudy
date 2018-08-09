package com.mylearn.thread.tools;

import com.mylearn.util.DateUtil;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-20
 * Time: ????10:59
 * CopyRight:360buy
 * Descrption:    CountDownLatch ??????
 * To change this template use File | Settings | File Templates.
 */
public class CountDownLatchDemo {

    public static void main(String args[]) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker worker1 = new Worker("zhangsna", 5000, countDownLatch);
        Worker worker2 = new Worker("lisi", 8000, countDownLatch);
        worker1.start();
        worker2.start();
        countDownLatch.await();//??????§Û?????????
        System.out.println("all work complete at" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
    }


    static class Worker extends Thread {
        String workerName;
        int workTime;
        CountDownLatch countDownLatch;

        public Worker(String workerName, int workTime, CountDownLatch countDownLatch) {
            this.workerName = workerName;
            this.workTime = workTime;
            this.countDownLatch = countDownLatch;
        }

        public void run() {
            System.out.println("Worker " + workerName + " do work begin at" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
            doWork();     //????
            System.out.println("Worker " + workerName + " do work complete at" + DateUtil.date2String(new Date(), DateUtil.DATE_FORMAT_1));
            countDownLatch.countDown(); //????????????1
        }

        private void doWork() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public String getWorkerName() {
            return workerName;
        }

        public void setWorkerName(String workerName) {
            this.workerName = workerName;
        }

        public int getWorkTime() {
            return workTime;
        }

        public void setWorkTime(int workTime) {
            this.workTime = workTime;
        }

        public CountDownLatch getCountDownLatch() {
            return countDownLatch;
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
    }

}
