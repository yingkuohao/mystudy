package com.mylearn.thread.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-12
 * Time: ????2:17
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class StackTest {

    List<String> lst = new ArrayList<String>();


    public void exchange(int i) {
        String cur = lst.get(i);
          int index = i;
        for (; ; ) {
            Object y;
            System.out.println(Thread.currentThread().getName() + "-index=" + index);
            String slot = lst.get(index);
            y = slot;
            String you = (String) y;

            System.out.println(Thread.currentThread().getName() + "-y=" + y);
            System.out.println(Thread.currentThread().getName() +"-slot=" + slot);
            System.out.println(Thread.currentThread().getName() +"-you=" + you);

        }

    }
}
