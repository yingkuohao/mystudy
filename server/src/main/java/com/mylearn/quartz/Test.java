package com.mylearn.quartz;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/9/19
 * Time: 14:13
 * CopyRight: taobao
 * Descrption:
 */

public class Test {
    public static void main(String args[]) {
        Long timeMill=1496964116000l;
        Instant instant=   Instant.ofEpochMilli(timeMill);
       final String TIME_FMROAT = "yyyy-MM-dd HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(TIME_FMROAT);
        LocalDateTime today = LocalDateTime.now();          //yingkhtodo:desc:test
        today=  today.minusHours(9);
        LocalDateTime yesterday = today.minusDays(1);
        String startDate = dateTimeFormatter.format(yesterday);
        String endDate = dateTimeFormatter.format(today);


        Calendar time = Calendar.getInstance();
           time.add(Calendar.DAY_OF_MONTH, -1);
           time.set(Calendar.HOUR_OF_DAY, 0);
           time.set(Calendar.SECOND, 0);
           time.set(Calendar.MINUTE, 0);
           time.set(Calendar.MILLISECOND, 0);
           Date startTime = time.getTime(); // 开始时间
        time.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = time.getTime(); // 结束时间
        System.out.println("startTime="+startTime.getTime()+"endTime="+endTime.getTime());   //1495036800000


        JobDetail jobDetail1 = new JobDetail("job1", Job.class);
        jobDetail1.getJobData().put("type", "job1");

        JobDetail jobDetail2 = new JobDetail("job2", Job.class);
        jobDetail2.getJobData().put("type", "job2");
        Trigger trigger1 = new Trigger();
        trigger1.setNextFireTime(System.currentTimeMillis() + 30001);
        Trigger trigger2 = new Trigger();
        trigger2.setNextFireTime(System.currentTimeMillis() + 10001);

        Scheduler scheduler = new Scheduler();
        scheduler.schedulerJob(jobDetail1, trigger1);
        scheduler.schedulerJob(jobDetail2, trigger2);

        scheduler.start();
        try {
            Thread.sleep(1000l);
            scheduler.halt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
