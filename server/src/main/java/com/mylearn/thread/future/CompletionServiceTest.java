package com.mylearn.thread.future;

import com.mylearn.thread.future.CountNum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-14
 * Time: ????2:24
 * CopyRight:360buy
 * Descrption:
 * ??????????§»??????????????????????????take???????????
 * To change this template use File | Settings | File Templates.
 */
public class CompletionServiceTest {

    public static void main(String args[]) {

        //new???callable?????
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);//???????executor

//        getResult(completionService);
        getFirstResult(completionService);

    }

    /**
     * ?????????????????????????
     * @param completionService
     */
    private static void getFirstResult(CompletionService<Integer> completionService) {
        List<Future<Integer>> lst = new ArrayList<Future<Integer>>();
        Integer finalResult = null;
        //??50??????
        for (int i = 100; i < 150; i++) {
            CountNum countNum = new CountNum(i);
            Future<Integer> curFuture = completionService.submit(countNum);    //???????callabe????FutureTask?????????????executor???????FutureTask?????????????????BlockingQueue
            lst.add(curFuture);
        }
        for (int j = 0; j < 50; j++) {
            try {
                Integer result = completionService.take().get();   //??????????
                //???????????????????????
                if (result != null) {
                    finalResult = result;
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        //????????????????????????
        for (Future<Integer> future : lst) {
            future.cancel(true);
        }


        if (finalResult != null) {
            System.out.println("finalResult =" + finalResult);
        }

    }


    private static void getResult(CompletionService<Integer> completionService) {
        for (int i = 100; i < 150; i++) {
            CountNum countNum = new CountNum(i);
            completionService.submit(countNum);    //???????callabe????FutureTask?????????????executor???????FutureTask?????????????????BlockingQueue
        }

        for (int i = 0; i < 50; i++) {
            try {
                Integer r = completionService.take().get();  //????????
                System.out.println("r= " + r);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
