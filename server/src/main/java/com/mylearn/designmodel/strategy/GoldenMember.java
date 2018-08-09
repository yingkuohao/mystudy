package com.mylearn.designmodel.strategy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????11:23
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class GoldenMember implements MemberStrategy{
    public double getDiscount() {
        return 0.05;
    }
}
