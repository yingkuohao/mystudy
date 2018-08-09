package com.mylearn.designmodel.adapter;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????2:20
 * CopyRight:360buy
 * Descrption:  ??????????
 * To change this template use File | Settings | File Templates.
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void fly() {
        //???????§Ö????????????4????????????
        adaptee.jump();
        adaptee.jump();
        adaptee.jump();
        System.out.println("I can fly!");
    }
}
