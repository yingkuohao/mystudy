package com.mylearn.quartz;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/9/19
 * Time: 13:41
 * CopyRight: taobao
 * Descrption:???jobdetail ?????job??????????????job?????????
 */

public class JobDetail {

    private Class<? extends Job> clazz;
    private String jobName;
    private HashMap<String, String> jobData;

    public JobDetail(String jobName, HashMap<String, String> jobData) {
        this.jobName = jobName;
        this.jobData = jobData;
    }

    public JobDetail(Class<? extends Job> clazz, String jobName) {
        this();
        this.clazz = clazz;
        this.jobName = jobName;
    }

    public JobDetail() {
        jobData = new HashMap<String, String>();
    }

    public JobDetail(String job1, Class<Job> jobClass) {
        this();
             this.clazz = clazz;
             this.jobName = jobName;
    }

    public Class<? extends Job> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Job> clazz) {
        this.clazz = clazz;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public HashMap<String, String> getJobData() {
        return jobData;
    }

    public void setJobData(HashMap<String, String> jobData) {
        this.jobData = jobData;
    }
}
