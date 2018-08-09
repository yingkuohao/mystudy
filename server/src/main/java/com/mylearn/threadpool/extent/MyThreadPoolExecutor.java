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
 * Time: ����11:44
 * CopyRight:360buy
 * Descrption:  �̳� ThreadPoolExecutor��ʵ��һЩ���ӣ�hook����������before��after�� ��
 * ThreadPoolExecutor�ǿ���չ�ģ����ṩ�˼��������������и�д�ķ�������beforeExecute��afterExecute��
 * terminated����ִ��������߳��н�����beforeExecute��afterExecute����������Щ�����л����������־��
 * ��ʱ����صȹ��ܡ����������������ػ����׳��쳣��aferExeutor���ᱻ���á����beforeExecute�׳�һ��RuntimeException
 * ����ô���񽫲��ᱻִ�У�����afterExecuteҲ���ᱻ���á�
 * ���̳߳���ɹرղ���ʱ����terminated��Ҳ���������������Ѿ���ɲ������й������߳�Ҳ�Ѿ��رպ�terminated������
 * ���ͷ�executor�����������������ĸ�����Դ�����⻹����ִ�з���֪ͨ����¼��־�����ռ�finalizeͳ����Ϣ�Ȳ�����
 * To change this template use File | Settings | File Templates.
 */
public class MyThreadPoolExecutor extends ThreadPoolExecutor {

    private static final String MYTHREADPOOLEXECUTOR_LOGGER = "MYTHREADPOOLEXECUTOR_LOGGER";
    private final static Log logThread = LogFactory.getLog(MYTHREADPOOLEXECUTOR_LOGGER);

    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();     //��ʼʱ��threadlocal
    private final AtomicLong numTasks = new AtomicLong(); // ִ�е���������������ͳ��
    private final AtomicLong totalTime = new AtomicLong();  //ִ���������ʱ�䣬����ͳ��

    private boolean isPause;    //��ͣ��־
    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * beforeExecute����¼һЩ��־
     * @param t
     * @param r
     */
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        pauseLock.lock();
        try {
            //���isPauseΪtrue��executor��ִͣ��
            while (isPause) {
                System.out.println(String.format("Thread %s: ��ͣ", t));
                unpaused.await(); // ��ǰ�߳��ڽӵ��ź�֮ǰһֱ���ڵȴ�״̬
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logThread.error("beforeExecute ��ͣ�쳣", e);
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
     * ��ͣ����
     */
    public void pause() {
        pauseLock.lock();
        try {
            System.out.println("��ͣ�̳߳�");
            logThread.info("��ͣ�̳߳�");
            isPause = true;
        } finally {
            pauseLock.unlock();
        }
    }

    /**
     * �ָ�����
     */
    public void resume() {
        pauseLock.lock();
        try {
            System.out.println("�����̳߳�");
            logThread.info("�����̳߳�");
            isPause = false;
            unpaused.signalAll();  //�������������߳�
        } finally {
            pauseLock.unlock();
        }

    }


}
