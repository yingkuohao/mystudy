package com.mylearn.designmodel.wrapper;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-26
 * Time: 上午11:54
 * CopyRight:360buy
 * Descrption: 装饰者子类：继承Decorator
 * To change this template use File | Settings | File Templates.
 */
public class HorseWithMoney extends Decorator {
    public void show() {
        super.getComponent().show(); //复用已有的实现
        System.out.println("我马上有钱"); //扩展对象的行为
    }
}
