package com.mylearn.thread.tools;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-12
 * Time: ????2:16
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class ThreadCAStest {
    public static void main(String args[]) {
        final StackTest test =new StackTest();
        test.lst.add("abc");
        test.lst.add("123");
        test.lst.add("345");
        test.lst.add("456");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        for(int i=0;i<2;i++) {
            final int finalI = i;
            Thread thread =new Thread(new Runnable() {
            public void run() {
                test.exchange(finalI);
//                try {
////                    Thread.sleep(1000);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
////                }
            }
        }) ;
            thread.start();
        }
    }



  public void change() {

  }

}
