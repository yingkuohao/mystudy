package com.mylearn.threadlocal.demo1;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-2-19
 * Time: ????9:55
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class SyncThreadTest implements Runnable {

    Student student = new Student();

    public void run() {
        String currentName = Thread.currentThread().getName();
        System.out.println(currentName + "is running....");

        synchronized (student) {
            Random random = new Random();
            int age = random.nextInt(100);
            student.setAge(age);
            System.out.println(currentName + "is set age " + age);
            System.out.println(currentName + "is get first age " + student.getAge());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            System.out.println(currentName + "is get second age " + student.getAge());

        }

    }

    public static void main(String args[]) {
        SyncThreadTest syn = new SyncThreadTest();

        Thread t1 = new Thread(syn, "Thread 1");

        Thread t2 = new Thread(syn, "Thread 2");

        t1.start();
        t2.start();
    }
}
