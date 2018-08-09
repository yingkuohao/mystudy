package com.mylearn.threadpool.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-10-28
 * Time: ????10:58
 * To change this template use File | Settings | File Templates.
 */
public class QueueTest {
    public static void main(String args[]) {
        final BlockingQueue queue = new ArrayBlockingQueue(5);
        init(queue);
        System.out.println("queue.size=" + queue.size() + ",    top element:" + queue.element());
        // queue.add("f");  //1. add?????????????????
        /*boolean bool = queue.offer("f");  //2. offer?????????????????false
        System.out.println("queue.size=" + queue.size() + ",   ?????:" + bool);*/
 /*       try {
            queue.put("f");  //3.put??????????put???????¡À??systemout??????????§µ???????????take???
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println("queue.size=" + queue.size() + ",   put????:");*/
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                boolean bool = false;
                try {
                    bool = queue.offer("f", 5, TimeUnit.SECONDS); //5.offer?????????????????????????????????????false???????????????§á??????????????????????
                    System.out.println("queue.size=" + queue.size() + ",   ?????:" + bool);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });
        thread1.start();
        //???????????????????????????????offer?????????
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (queue.size() > 0) {
                    try {
                        String str = (String) queue.take();
                        System.out.println("queue.size=" + queue.size() + ",   ?????:" + str);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

            }
        });
        thread.start();


    }

    private static void init(BlockingQueue queue) {
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");
    }

}
