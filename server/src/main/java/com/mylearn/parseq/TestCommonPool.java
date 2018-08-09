package com.mylearn.parseq;

import com.linkedin.parseq.Task;
import com.linkedin.parseq.Tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/10
 * Time: 17:02
 * CopyRight: taobao
 * Descrption:
 */

public class TestCommonPool {

    public static void main(String[] args) {
        final Callable<String> task =  new GetContentTask("test str1");
        final Callable<String> task2 =  new GetContentTask("test str2");
        final ExecutorService taskScheduler = Executors.newFixedThreadPool(5);
        taskScheduler.submit(task);
        taskScheduler.submit(task2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        taskScheduler.shutdown();
    }
}
