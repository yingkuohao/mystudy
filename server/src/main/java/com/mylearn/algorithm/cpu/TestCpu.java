package com.mylearn.algorithm.cpu;

import java.lang.management.ManagementFactory;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-5-2
 * Time: ????2:41
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestCpu {
    public static void main(String args[]) throws InterruptedException {
//        method1();
//        method2();
        method3();
        System.out.println("11");
    }

    private static void method3() throws InterruptedException {
        final double SPLIT = 0.01;
        final int COUNT = (int) (2/SPLIT);
        final double  PI =Math.PI;
        final int INTERVAL = 200;
        long[] busySpan = new long[COUNT];
        long[] idleSpan = new long[COUNT];
        int half =  INTERVAL /2;
        double  radian = 0.0;
        for(int i=0;i<COUNT;i++) {
            busySpan[i]=(long)(half + (Math.sin(PI * radian) * half));
            idleSpan[i] = INTERVAL - busySpan[i];
            radian += SPLIT;
        }
        long  startTime = 0;
        int j = 0;
        while(true) {
            j = j % COUNT;
            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < busySpan[j]) {

            }

            Thread.sleep(idleSpan[j]);
            j++;
        }


    }

    private static void method2() throws InterruptedException {
        int busyTime = 10;
        int idelTime = busyTime;

        while (true) {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < busyTime)
                ;
            Thread.sleep(idelTime);
        }
    }

    private static void method1() throws InterruptedException {

        double  x = 0;
        double  y = 0;
        while(true)  {
            y = (Math.sin(x) + 1) *1000 /2;
            long startTime = System.currentTimeMillis();
                 while (System.currentTimeMillis() - startTime < y);
                      x+=0.1;
            Thread.sleep((long)(1000-y));
        }


    }
}
