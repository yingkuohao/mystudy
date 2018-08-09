package com.mylearn.parse;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.JavaFileObject;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/3/22
 * Time: ����5:13
 * CopyRight: taobao
 * Descrption:
 */
@PrintMe
public class Test {
    public static void main(String[] args) {
        long end1 = System.currentTimeMillis();
          System.out.println("111");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end2 = System.currentTimeMillis();
//        System.out.println("endTime1="+end1);
//        System.out.println("endTime2="+end2);
        System.out.println("time="+(end2-end1));
      }

}
