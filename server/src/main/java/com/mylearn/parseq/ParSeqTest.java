package com.mylearn.parseq;

import com.linkedin.parseq.*;
import com.linkedin.parseq.promise.Promise;
import com.linkedin.parseq.promise.Promises;
import com.linkedin.parseq.promise.SettablePromise;
import sun.net.www.http.HttpClient;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.linkedin.parseq.Tasks.par;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/10
 * Time: 13:48
 * CopyRight: taobao
 * Descrption:并发框架 parseq
 */

public class ParSeqTest {

    public static void main(String[] args) {
        try {
            final int numCores = Runtime.getRuntime().availableProcessors();
//            final ExecutorService taskScheduler = Executors.newFixedThreadPool(numCores + 1);
//            final ScheduledExecutorService timerScheduler = Executors.newSingleThreadScheduledExecutor();
            final ExecutorService taskScheduler = Executors.newFixedThreadPool(5);
            final ScheduledExecutorService timerScheduler = Executors.newScheduledThreadPool(numCores + 1);
            final Engine engine = new EngineBuilder()
                    .setTaskExecutor(taskScheduler)
                    .setTimerScheduler(timerScheduler)
                    .build();
            //单独任务：
//            singleTask(engine);

            //定义并行任务
//            final Task<String> contentTypes = getParTask();
//            final Task<String> contentTypes = getParTask2();
//            final Task<?> contentTypes = getParTask3();
            final Task<?> contentTypes = getParTask4();
            engine.run(contentTypes);
            //串行任务
//            final Task<String> sequentialTask = getSequentialTask();
//            engine.run(sequentialTask);

            engine.awaitTermination(3, TimeUnit.SECONDS);
//            System.out.println("task.get=" + task.get());
            engine.shutdown();
            taskScheduler.shutdown();
            timerScheduler.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void singleTask(Engine engine) {
        final Task<String> task = Tasks.callable("length of 'test str'", new GetContentTask("test str1"));
        Task<String> printContentType = task.andThen("print", s -> System.out.println(s));
//            Task<String> logFailure = printContentType.onFailure("print stack trace", e -> e.printStackTrace());
        engine.run(printContentType);
    }

    //并发任务：Task.par()
    private static Task<String> getParTask() {
        final Task<String> task = Tasks.callable("length of 'test str'", new GetContentTask("test str1"));
        final Task<String> task2 = Tasks.callable("length of 'test str'", new GetContentTask("test str2"));

        return Task.par(task, task2).map("concatenate", (google, bing) -> "Google: " + google + "\n" +
                "Bing: " + bing + "\n");
    }

    //并发任务：Task.par()
    private static Task<String> getParTask2() {
        final Task<String> task = Tasks.callable("length of 'test str'", new GetContentTask("test str1"));
        final Task<String> task2 = Tasks.callable("length of 'test str'", new GetContentTask("test str2"));
        return new Par2Task("test", task, task2);
    }

    private static ParTask<?> getParTask3() {

        final int iters = 500;

        final Task<?>[] tasks = new BaseTask<?>[iters];
        final AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < iters; i++) {
            tasks[i] = Task.action("task-" + i, () -> {
                // Note: We intentionally do not use CAS. We guarantee that
                // the run method of Tasks are never executed in parallel.
                final int currentCount = counter.get();
                System.out.println(Thread.currentThread() + ",i=" + currentCount);
                counter.set(currentCount + 1);
            });
        }

        final ParTask<?> par = par(Arrays.asList(tasks));
        return par;
    }

    private static ParTask<?> getParTask4() {

        final SettablePromise<String> promise1 = Promises.settable();
        final SettablePromise<Integer> promise2 = Promises.settable();

        // Used to ensure that both tasks have been run
        final CountDownLatch cdl = new CountDownLatch(2);

        final Task<String> task1 = new BaseTask<String>() {
            @Override
            public Promise<String> run(final Context context) throws Exception {
                System.out.println("task1");
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread() + ",i=" + i);
                }
                cdl.countDown();
                return promise1;
            }
        };

        final Task<Integer> task2 = new BaseTask<Integer>() {
            @Override
            public Promise<Integer> run(final Context context) throws Exception {
                System.out.println("task2");
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread() + ",i=" + i);
                }
                cdl.countDown();
                return promise2;
            }
        };

        final ParTask<Object> par = Tasks.par(task1, task2);
        return par;
    }

    //串行任务：Task.par()
    private static Task<String> getSequentialTask() {
        final Task<String> task = Tasks.callable("length of 'test str'", new GetContentTask("test str1"));
        final Task<String> task2 = Tasks.callable("length of 'test str'", new GetContentTask("test str2"));

        Task<String> shipAfterPayment =
                task.andThen("shipProductAterPayment", task2);
        return shipAfterPayment;
    }
}
