package com.mylearn.threadpool.sheduler;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/2/26
 * Time: 15:10
 * CopyRight: taobao
 * Descrption: ??????
 */

public class TestDelayQueue {
    public static void main(String args[]) {
        try {
            doRun();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void doRun() throws InterruptedException {
        DelayQueue<Task> taskDelayQueue = new DelayQueue<Task>();
        int count = 20;
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            taskDelayQueue.put(new Task("task" + i, 20 + random.nextInt(100)));
        }

        for (int i = 0; i < count; i++) {
            taskDelayQueue.take().run();
        }
    }

    private static final long NANO_ORIGIN = System.nanoTime();

    /**
     * ?????????????????Delayed??????§Õdelay??????compareto???????????ScheduledFutureTask
     */
    static class Task implements Runnable, Delayed {
        private String name;
        private long workTime;  //??workTime??????????????§Ö??????????????????

        Task(String name, long workTime) {
            this.name = name;
            this.workTime = workTime;
        }

        final long now() {
            return System.nanoTime() - NANO_ORIGIN;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long d = unit.convert(workTime - now(), TimeUnit.NANOSECONDS);
            return d;
        }

        @Override
        public int compareTo(Delayed o) {
            if (o == null || !(o instanceof Task)) return 1;
            if (o == this) return 0;
            Task s = (Task) o;
            if (this.workTime > s.workTime) {
                return 1;
            } else if (this.workTime == s.workTime) {
                return 0;
            } else {
                return -1;
            }
        }

        @Override
        public void run() {
            System.out.println(name + "??????§µ?worktime=" + workTime);
        }
    }
}
