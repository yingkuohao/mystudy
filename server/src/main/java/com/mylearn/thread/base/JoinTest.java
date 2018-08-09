package com.mylearn.thread.base;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/29
 * Time: 下午3:53
 * CopyRight: taobao
 * Descrption:
 * join方法可以阻塞线程.
 */

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
          Thread t=new Thread(()->{
              System.out.println("First task started");
              System.out.println("sleeping for 2 seconds");
              try {
                  Thread.sleep(2000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("first tatsk completed");
          });

        Thread t2 = new Thread(()->{
            System.out.println("second task completed");
        });
        t.start();
        t.join();
        t2.start();
    }
}
