package com.mylearn.zookeeper.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 18/3/19
 * Time: ����2:48
 * CopyRight: ying
 * Descrption:
 */

public class MutexObj {

    public static   Lock lock=new ReentrantLock();
    public static  Condition condition = lock.newCondition(); // �������
    public static Integer mutex = 1;
}
