package com.mylearn.thread.future;

import com.sun.org.apache.xpath.internal.functions.FuncTrue;

import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-7-15
 * Time: ????4:21
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class  FutureTest {
    public static void main(String args[]) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        //submitTest(executorService);   //??????submit
        futureTaskTest(executorService); //???????execute
    }

    /**
     *  FutureTask??Future?????????Future???????Runnable??????????Executor4??§³?
     * @param executorService
     */
    private static void futureTaskTest(ExecutorService executorService) {
        FutureTask futureTask = new FutureTask(new Callable() {
            public Object call() throws Exception {
                System.out.println("dosomething");
                            return "ok";  //To
            }
        }
        );
        executorService.execute(futureTask);
    }

    private static void submitTest(ExecutorService executorService) {
        Future future = executorService.submit(new Callable() {
            public Object call() throws Exception {
                System.out.println("dosomething");
                return "ok";  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        try {
            Object obj = future.get();
            System.out.println(obj.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ExecutionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
