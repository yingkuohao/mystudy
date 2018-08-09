package com.mylearn.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/8/26
 * Time: 上午9:53
 * CopyRight: taobao
 * Descrption:
 * scheduleAtFixedRate : 以固定的频率执行线程任务,固定频率的含义就是可能设定的固定时间不足以完成线程任务,但是他不管,达到设定的延迟时间了就要执行下一次了
 * scheduleWithFixedDelay : 从字面意义理解就是以固定延迟来执行线程任务,它实际上是不管线程任务的执行时间的,每次都要把任务执行完后再延迟固定时间后再执行下一次.
 */

public class ScheduleExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        //我们定义两个线程,工作时间都是3秒
        Runnable beeper1 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("beep1");
            }
        };

        Runnable beeper2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("beep2");
            }
        };

         //定义两个任务线执行器,handler1以固定频率执行,handler2以完成任务后固定时间间隔执行
        ScheduledFuture<?> beepgerHandler1 = scheduler.scheduleAtFixedRate(beeper1, 5, 5, TimeUnit.SECONDS);
        ScheduledFuture<?> beepgerHandler2 = scheduler.scheduleWithFixedDelay(beeper2, 5, 5, TimeUnit.SECONDS);

        //定义两个任务终止器,在20秒后终止两个线程池.
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                beepgerHandler1.cancel(true);
                System.out.println("beeper1 canceld");
            }
        }, 20, TimeUnit.SECONDS);

        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                beepgerHandler2.cancel(true);
                System.out.println("beeper2 canceld");
            }
        }, 20, TimeUnit.SECONDS);
    }
    //结论:由于每个任务运行时间为3秒,由于handler1不管执行时间,所以每5秒执行,就是20/5=4次;
    //而hander2是任务执行后固定时间间隔执行,所以相当于执行一次任务是3+5秒=8秒,一共 20/8=2次.
}
