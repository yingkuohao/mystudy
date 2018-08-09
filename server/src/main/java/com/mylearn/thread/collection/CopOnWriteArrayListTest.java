package com.mylearn.thread.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-7
 * Time: ????6:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class CopOnWriteArrayListTest {
    public static void main(String args[]) {

        CopOnWriteArrayListTest copOnWriteArrayListTest = new CopOnWriteArrayListTest();
//        List<String> lst = new ArrayList<String>();
        CopyOnWriteArrayList<String> lst = new CopyOnWriteArrayList<String>();
        lst.add("zhangsan");
        lst.add("lisi");
        lst.add("wangwu");
        lst.add("zhaoliu");
        Thread thread1 = new Thread(copOnWriteArrayListTest.new Thread1(lst));
        Thread thread2 = new Thread(copOnWriteArrayListTest.new Thread2(lst));
        Thread thread3 = new Thread(copOnWriteArrayListTest.new Thread3(lst));
        thread1.start();
//        thread2.start();
        thread3.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        System.out.println(lst.isEmpty());
        for(String cur: lst) {
            System.out.println("main cur:" +cur);
        }

    }


    /**
     * ???????????get???
     */
    class Thread1 implements Runnable {

        private List<String> lst;

        Thread1(List<String> lst) {
            this.lst = lst;
        }

        public void run() {
            methodFor();
//          methodIterator();
        }

        //???????????
        private void methodIterator() {
            Iterator iterator = lst.iterator();
            while (iterator.hasNext()) {
                String cur = (String) iterator.next();   //java.util.ConcurrentModificationException
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                System.out.println("remove :" + cur);
            }
        }

        //for-eache????
        private void methodFor() {
            for (String cur : lst) {    //java.util.ConcurrentModificationException
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("cur:" + cur.toString());
            }
        }
    }


    /**
     * ???2???????
     */
    class Thread2 implements Runnable {

        private List<String> lst;

        Thread2(List<String> lst) {
            this.lst = lst;
        }

        public void run() {
            removeIterator();
        }

        /**
         * ??????remove
         */
        private void removeIterator() {
            Iterator itrator = lst.iterator();
            while (itrator.hasNext()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String cur = (String) itrator.next();
                System.out.println("remove :" + cur);
                itrator.remove();     //????????Iterator??remove?????????????????? ConcurrentModificationException
            }
        }

    }

    /**
     * ??CopyOnWriteArrayList???? ???????remove?????????
     */
    class Thread3 implements Runnable {

        private CopyOnWriteArrayList<String> lst;

        Thread3(CopyOnWriteArrayList<String> lst) {
            this.lst = lst;
        }

        public void run() {
            removeCopyOnWrite();
        }


        private void removeCopyOnWrite() {

            for (String cur : lst) {
                System.out.println("remove :" + cur);
                lst.remove(cur);
            }
        }
    }

}
