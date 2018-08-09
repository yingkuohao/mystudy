package com.mylearn.quartz;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/9/19
 * Time: 13:39
 * CopyRight: taobao
 * Descrption:???job??
 */

public class Job {
    public void execute(Map<String, String> jobData) {
        System.out.println("job execute??");
    }
}
