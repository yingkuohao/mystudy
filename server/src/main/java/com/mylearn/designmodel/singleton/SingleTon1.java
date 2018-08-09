package com.mylearn.designmodel.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-24
 * Time: ????2:37
 * CopyRight:360buy
 * Descrption: ??????????????????????????new??????
 * To change this template use File | Settings | File Templates.
 */
public class SingleTon1 {

    //???????
    private static SingleTon1 singleTon1 = new SingleTon1();

    //??��?????
    private SingleTon1() {

    }

    //???��????????????????????
    public static SingleTon1 getInstance() {
        return singleTon1;
    }

}