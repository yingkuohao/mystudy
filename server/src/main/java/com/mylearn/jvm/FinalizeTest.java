package com.mylearn.jvm;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-22
 * Time: 上午10:20
 * CopyRight:360buy
 * Descrption:
 * 	针对根搜索算法，垃圾回收需经历两个标记阶段：1. 如果进行根搜索后发现没有引用链，则进行第一次标记，然后进行一次筛选，判断是否需要执行finalize方法。
 	1.1如果对象覆盖了finalize方法，要处理一些事情，则把对象放入一个F-Queue的队列，并在稍后由一条由虚拟机自动建立的、低优先级的Finalizer线程去执行。
 	1.2如果对象没有覆盖finalize方法，或则finalize方法已经执行过了，则不进入F-Queue队列。
 	如果F-Queue有值，则进行第二次标记，如果finalize方法中重新给对象一个引用，则对象被移除F-Queue队列。
 * To change this template use File | Settings | File Templates.
 */
public class FinalizeTest {
    public static FinalizeTest finalizeTest = null;

    public static void main(String args[]) {
        finalizeTest = new FinalizeTest();
        test();  //第一次test会执行finalize方法
        test();  //第二次不会执行，finalize方法只执行一次

    }

    private static void test() {
        finalizeTest = null;   //引用释放
        System.gc();          //gc
        try {
            Thread.sleep(500);     //等待 finalizer执行
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (finalizeTest == null) {
            System.out.println("对象已死");
        } else {
            System.out.println("对象尚存活");
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize executed!");
        finalizeTest = this;    //重新给对象一个引用

    }
}
