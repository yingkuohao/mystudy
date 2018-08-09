package com.mylearn.thread.future;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-14
 * Time: 上午10:58
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class CountNum implements Callable {

    private Integer max;

    public CountNum(Integer max) {
        this.max = max;
    }

    public Integer call() throws Exception {

        System.out.println("执行call方法");
        Random random = new Random();
        int i = random.nextInt(max);
        System.out.println("获得随机数i=" + i);
//        Thread.sleep(1000*200);
//        System.out.println("CountNum线程睡眠一秒");
        return i;
    }
}
