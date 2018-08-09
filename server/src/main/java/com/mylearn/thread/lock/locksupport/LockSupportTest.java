package com.mylearn.thread.lock.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/27
 * Time: ÏÂÎç1:31
 * CopyRight: taobao
 * Descrption:
 */

public class LockSupportTest {
    public static void main(String[] args) {

//        LockSupport.park();
        System.out.println("----start---");
        LockSupport.parkNanos(1000000000);
        System.out.println("----end---");


        System.out.println("----start---");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.parkNanos(1000000000);
        System.out.println("----end---");


        System.out.println("----start---");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        LockSupport.parkNanos(1000000000);
        System.out.println("----inter---");
        LockSupport.parkNanos(1000000000);
        System.out.println("----end---");
    }
}
