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
public class HorseWithMoney extends Decorator {
    public void show() {
        super.getComponent().show(); //�������е�ʵ��
        System.out.println("��������Ǯ"); //��չ�������Ϊ
    }
}
