package com.mylearn.designmodel.strategy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????10:17
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        MemberStrategy memberStrategy = new GoldenMember();
        Product product = new Product("xbox", 1800.00);
        product.setMemberStrategy(memberStrategy);

        double finalPrice = product.getFinalPrice();

        System.out.println("?????????xbox??????????" + finalPrice);

    }

}
