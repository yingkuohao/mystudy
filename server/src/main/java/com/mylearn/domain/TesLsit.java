package com.mylearn.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.mylearn.domain
 * User: yingkuohao
 * Date: 12-2-16
 * Time: ????12:32
 * CopyRight:360buy
 * Descrption:
 */
public class TesLsit {
     public static void main(String args[]) {
        List<String> lst = new ArrayList<String>();
        lst.add("first");
        lst.add("second");
        lst.add("third");
        lst.add("four");
        lst.add("five");
        lst.add("six");
        lst.add("seven");
        for (String s : lst) {
            System.out.println("s = " + s);
            lst.remove(s);
        }

    }
}
