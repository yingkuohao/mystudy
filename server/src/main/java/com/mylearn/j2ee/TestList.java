package com.mylearn.j2ee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-20
 * Time: ????2:35
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestList {
    public static void main(String args[]) {
        String s = "a,b,c,d,e,f,g";
        String[] pinsArray = s.split(",");
        List<String> pinList = Arrays.asList(pinsArray);

        List<String> lst = new ArrayList<String>();
        lst.add("a");
        lst.add("b");
        lst.add("c");
        pinList.removeAll(lst);
    }

    public static void method1(List<String> target,String[] strs) {
          for(String s: strs) {

          }

    }
}
