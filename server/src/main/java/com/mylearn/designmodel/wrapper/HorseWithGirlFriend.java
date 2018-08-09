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
public class HorseWithGirlFriend extends Decorator {
    public void show() {
        super.getComponent().show();
        System.out.println("我马上对象");
    }

}
