package com.mylearn.thread.collection;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/11/21
 * Time: 下午2:22
 * CopyRight: taobao
 * Descrption:
 */

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMapTest concurrentHashMapTest = new ConcurrentHashMapTest();
//        concurrentHashMapTest.testPutIfAbsent();
        concurrentHashMapTest.test1();

    }

    void testPutIfAbsent() {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
        String key = "zhangsan";
        String value1 = concurrentHashMap.putIfAbsent(key, "123");
        System.out.println("value1=" + value1);

        String value2 = concurrentHashMap.putIfAbsent(key, "124");
        System.out.println("value2=" + value2);
        String finalValue = concurrentHashMap.get(key);
        System.out.println("finalValue=" + finalValue);
    }

    void test1() {
        List<Long> list = Lists.newArrayList(1l, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l, 12l, 13l, 14l, 15l);
        int count = 15;
        Map<Long, String> concurrentHashMap = new ConcurrentHashMap<Long, String>();
        String key = "zhangsan";
//               String value1 = concurrentHashMap.putIfAbsent(key, "123");
        CountDownLatch countDownLatch = new CountDownLatch(count);
        CountDownLatch countDownLatchBegin = new CountDownLatch(1);
        ConcurrentHashMap loginTerminalMap = new ConcurrentHashMap();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatchBegin.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (Long i : list) {
                        String threadName = Thread.currentThread().getName();
                        /*              String value = concurrentHashMap.putIfAbsent(i, threadName);
                        if (value != null) {
                        System.out.println("put ifAbsent error,i="+i+",timeStamp={}" + threadName + "value={}" + value);
                            continue;
                        }*/

                        if (concurrentHashMap.containsKey(i)) {
                            System.out.println("put ifAbsent error,i=" + i + ",timeStamp={}" + threadName + "value={}" + threadName);
                            continue;
                        }
                        concurrentHashMap.put(i,threadName);
                        try {
                            Thread.sleep(200);
                            System.out.println("biz work!");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("put ifAbsent success,i=" + i + ",timeStamp={}" + threadName + "value={}" + threadName);

                    }
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }
        long begin = System.currentTimeMillis();
        countDownLatchBegin.countDown();
        try {
            countDownLatch.await();
            long end = System.currentTimeMillis();
            System.out.println(" get terminal end,useMap=" + (end - begin));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
