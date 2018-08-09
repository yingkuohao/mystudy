package com.mylearn.quartz;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/9/19
 * Time: 13:44
 * CopyRight: taobao
 * Descrption:???trigger??????jobdetail??????????trigger?????jobdetail
 */

public class Trigger implements Comparable<Trigger> {
    private String jobKey;
    private long nextFireTime;


    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public long getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(long nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public int compareTo(Trigger o) {
        return (int) (this.nextFireTime - o.nextFireTime);
    }

    public void resert() {
        setNextFireTime(-1);//???
    }
}
