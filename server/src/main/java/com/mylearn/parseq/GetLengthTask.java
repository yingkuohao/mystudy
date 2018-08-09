package com.mylearn.parseq;

import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/10
 * Time: 13:49
 * CopyRight: taobao
 * Descrption:
 */

public class GetLengthTask implements Callable<Integer> {
    private final String _string;

    public GetLengthTask(String string) {
        _string = string;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("_string=" + _string);
        for (int i = 0; i < 10; i++) {
                  Thread.sleep(100);
                  System.out.println(Thread.currentThread() + "_string=" + _string + ",i=" + i);
              }
        return _string.length();
    }
}
