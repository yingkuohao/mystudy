package com.mylearn.j2ee.core;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/26
 * Time: ����3:51
 * CopyRight: taobao
 * Descrption:
 */

public class Test {
    public static void main(String[] args) {
        List<Future<String>> futureList       =new  ArrayList<Future<String>>();
        futureList.add(null);
        futureList.add(null);
        futureList.add(null);
        futureList.forEach(future -> {
            System.out.println("null");
                });
        List<String> list = new ArrayList<String>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.forEach(n-> {
            System.out.println("null");
        });
        VolatileLong falseSharing=  new VolatileLong();
        System.out.println("f"+falseSharing.hashCode());
    }

    private static class VolatileLong {
         public volatile long value = 0L;
 //        public long p1, p2, p3, p4, p5, p6;//ע��
     }
}
