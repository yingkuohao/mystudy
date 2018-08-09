package com.mylearn.threadpool;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-20
 * Time: ????10:14
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class SimpleTest extends Task {

    private String name;

    public SimpleTest(String name) {
        this.name = name;
    }

    @Override
    public void deal() {
        System.out.println("finish a task ??--->" + this.name);
    }


}
