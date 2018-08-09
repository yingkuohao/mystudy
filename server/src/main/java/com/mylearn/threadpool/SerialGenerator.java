package com.mylearn.threadpool;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-5-14
 * Time: ????10:10
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ??????ß‹???????MAX_VAL??????MAX_VAL????0?????
 */
public class SerialGenerator {

    public static final int MAX_VAL = 999;
    public static final int R_NUM = getRNum(MAX_VAL);

    private static AtomicReference<AtomicInteger> ref = new AtomicReference<AtomicInteger>(new AtomicInteger(0));

    public static int getSerialNo() {
        AtomicInteger old = ref.get();
        int serialNo = old.getAndIncrement();
        while (serialNo > MAX_VAL) {
            AtomicInteger update = new AtomicInteger(0);
            ref.compareAndSet(old, update);
            old = ref.get();
            serialNo = old.getAndIncrement();
        }

        return serialNo;
    }

    /**
     * ???????ßﬁ?¶À????234??3¶À??
     * @param i
     * @return
     */
    private static final int getRNum(int i) {
        if (i < 0) {
            throw new RuntimeException("Illegal arg i, i=" + i);
        }

        int div = i;
        int num = 1;
        div /= 10;
        while (div > 0) {
            div /= 10;
            num++;
        }

        return num;
    }

    /**
     * ??????ß‹???????ß‹???????????????????ß‹???????
     * ?????????????”Õ0??????"015"
     * @return
     */
    public static String getSerialNoInStr() {
        int serialNo = getSerialNo();
        int rnum = getRNum(serialNo);

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<R_NUM-rnum; i++) {
            sb.append("0");
        }

        return sb.append(serialNo).toString();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //??10??
        for (int t=0; t<10; t++) {
            int taskNum = 1 + new Random().nextInt(19);
            @SuppressWarnings("unchecked")
            Future<Integer>[] fs = new Future[taskNum];
//            System.err.println(taskNum);
            ExecutorService executor = Executors.newFixedThreadPool(taskNum);
            for (int i=0; i<taskNum; i++) {
                fs[i] = executor.submit(new Callable<Integer>() {
                    int sum = 0;
                    public Integer call() throws Exception {
                        for (int i=0; i<=MAX_VAL; i++) {
                            sum += getSerialNo();
                        }

                        return new Integer(sum);
                    }
                });
            }

            int result = 0;
            for (Future<Integer> future : fs) {
                result += future.get();
            }

            int expect = 0;
            for (int i=0; i<taskNum; i++) {
                for (int j=0; j<=MAX_VAL; j++) {
                    expect += j;
                }
            }
            System.out.println(expect==result);
            System.out.println(taskNum + " : " + expect);
        }

        Random random = new Random();
        for (int i=0; i<10000; i++) {
            getSerialNoInStr();
            if (random.nextInt(1000) < 9) {
                System.out.println(getSerialNoInStr());
            }
        }
    }

}
