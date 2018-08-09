package com.mylearn.designmodel.adapter;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????2:20
 * CopyRight:360buy
 * Descrption: ????????
 * To change this template use File | Settings | File Templates.
 */
public class Adapter1 extends Adaptee implements Target  {


    public void fly() {
        //???????§Ö????????????4????????????
        super.jump();
        super.jump();
        super.jump();
        System.out.println("I can fly!");
    }
}
