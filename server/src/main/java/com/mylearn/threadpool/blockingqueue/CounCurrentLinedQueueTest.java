package com.mylearn.threadpool.blockingqueue;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????10:08
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class CounCurrentLinedQueueTest {
    public static void main(String args[]) {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
        concurrentLinkedQueue.offer("a");
        concurrentLinkedQueue.offer("b");
        concurrentLinkedQueue.offer("c");

    }
}
