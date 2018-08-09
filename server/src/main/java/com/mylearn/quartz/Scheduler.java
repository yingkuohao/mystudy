package com.mylearn.quartz;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/9/19
 * Time: 13:45
 * CopyRight: taobao
 * Descrption:
 */

public class Scheduler {
    private List<JobDetail> jobList = new ArrayList<JobDetail>();
    private TreeSet<Trigger> triggers = new TreeSet<Trigger>();
    private Object lockObj = new Object();
   Thread  sheculerThread = new Thread(new SheculerThread(this));

    public  void schedulerJob(JobDetail jobDetail,Trigger trigger) {
        synchronized (lockObj) {
            jobList.add(jobDetail);
            //???trigger??????jobdetail??????????trigger?????jobdetail
            trigger.setJobKey(jobDetail.getJobName());
            triggers.add(trigger);
        }
    }

    public Scheduler() {

    }

    public void start() {
        System.out.println("shceduler begin start!");
        sheculerThread.start();
    }

    public void halt() {

    }

    public List<JobDetail> getJobList() {
        return jobList;
    }

    public void setJobList(List<JobDetail> jobList) {
        this.jobList = jobList;
    }

    public TreeSet<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(TreeSet<Trigger> triggers) {
        this.triggers = triggers;
    }
}
