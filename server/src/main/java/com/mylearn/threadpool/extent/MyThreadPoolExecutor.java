package com.mylearn.threadpool.extent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-9
 * Time: 上午11:44
 * CopyRight:360buy
 * Descrption:  继承 ThreadPoolExecutor，实现一些钩子（hook）方法，如before，after等 。
 * ThreadPoolExecutor是可扩展的，它提供了几个可以在子类中改写的方法，如beforeExecute，afterExecute和
 * terminated，在执行任务的线程中将调用beforeExecute和afterExecute方法。在这些方法中还可以添加日志、
 * 计时、监控等功能。无论任务正常返回还是抛出异常，aferExeutor都会被调用。如果beforeExecute抛出一个RuntimeException
 * ，那么任务将不会被执行，并且afterExecute也不会被调用。
 * 在线程池完成关闭操作时调用terminated，也就是在所有任务都已经完成并且所有工作者线程也已经关闭后。terminated可以用
 * 来释放executor在其生命周期里分配的各种资源，此外还可以执行发送通知、记录日志或者收集finalize统计信息等操作。
 * To change this template use File | Settings | File Templates.
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private static final String MYTHREADPOOLEXECUTOR_LOGGER = "MYTHREADPOOLEXECUTOR_LOGGER";
    private final static Log logThread = LogFactory.getLog(MYTHREADPOOLEXECUTOR_LOGGER);

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();     //开始时间threadlocal
    private final AtomicLong numTasks = new AtomicLong(); // 执行的任务总数，用于统计
    private final AtomicLong totalTime = new AtomicLong();  //执行任务的总时间，用于统计

    private boolean isPause;    //暂停标志
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * beforeExecute，记录一些日志
     * @param t
     * @param r
     */
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            //如果isPause为true，executor暂停执行
            while (isPause) {
                System.out.println(String.format("Thread %s: 暂停", t));
                unpaused.await(); // 当前线程在接到信号之前一直处于等待状态
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logThread.error("beforeExecute 暂停异常", e);
        } finally {
            pauseLock.unlock();
        }
        logThread.info(String.format("Thread %s: start %s", t, r));
        System.out.println(String.format("Thread %s: before", t));
        startTime.set(System.nanoTime());
    }


    protected void afterExecute(Runnable r, Throwable t) {
        try {
//            System.out.println(String.format("Thread %s: after", t));
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            logThread.info(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
        } finally {
            super.afterExecute(r, t);
        }

    }

    protected void terminated() {
        try {
            logThread.info(String.format("Terminated:avg time=%dns", totalTime.get() / numTasks.get()));

        } finally {
            super.terminated();
        }
    }


    /**
     * 暂停方法
     */
    public void pause() {
        pauseLock.lock();
        try {
            System.out.println("暂停线程池");
            logThread.info("暂停线程池");
            isPause = true;
        } finally {
            pauseLock.unlock();
        }
    }

    /**
     * 恢复方法
     */
    public void resume() {
        pauseLock.lock();
        try {
            System.out.println("唤醒线程池");
            logThread.info("唤醒线程池");
            isPause = false;
            unpaused.signalAll();  //唤醒所有阻塞线程
        } finally {
            pauseLock.unlock();
        }

    }


}
