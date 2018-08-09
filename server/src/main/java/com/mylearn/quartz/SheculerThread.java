package com.mylearn.quartz;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/9/19
 * Time: 13:45
 * CopyRight: taobao
 * Descrption:
 */

public class SheculerThread implements Runnable {
    private boolean shutDown = false;
    private Object lockObj = new Object();

    private Scheduler scheduler;

    public SheculerThread(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void halt() {
        synchronized (lockObj) {
            shutDown = true;
            lockObj.notifyAll();
        }
    }

    public void run() {
        while (!shutDown) {
            synchronized (lockObj) {
                try {
                    //1. ????????§Ö?trigger
                    Trigger trigger = scheduler.getTriggers().pollFirst();
                    if (trigger == null) {
                        lockObj.wait(100);
                        continue;
                    }
                    //2.
                    long curr = System.currentTimeMillis();
                    long nextTime = trigger.getNextFireTime();
                    while (nextTime > curr && !shutDown) {
                        curr = System.currentTimeMillis();
                        if (nextTime > curr + 1) {
                            lockObj.wait(nextTime - curr);
                        }
                        if (!shutDown) {
                            //???trigger????????jobdetail
                            int index = scheduler.getJobList().indexOf(new JobDetail(trigger.getJobKey(), new HashMap<String, String>()));
                            JobDetail jobDetail = scheduler.getJobList().get(index);
                            //???jobdetai???job??????
                            Job job = jobDetail.getClazz().newInstance();
                            job.execute(jobDetail.getJobData());
                            trigger.resert();
                            nextTime = trigger.getNextFireTime();
                            if (nextTime != -1) {
                                scheduler.getTriggers().add(trigger);
                            } else {
                                break;
                            }
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
