package com.mylearn.designmodel.adapter;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????2:48
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        Adaptee cat = new Adaptee();
        cat.jump();

        Target target = new Adapter(cat);
        target.fly();
    }
}
