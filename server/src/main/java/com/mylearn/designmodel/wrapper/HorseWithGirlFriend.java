package com.mylearn.designmodel.wrapper;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-26
 * Time: ����11:54
 * CopyRight:360buy
 * Descrption: װ�������ࣺ�̳�Decorator
 * To change this template use File | Settings | File Templates.
 */
public class HorseWithGirlFriend extends Decorator {
    public void show() {
        super.getComponent().show();
        System.out.println("�����϶���");
    }

}
