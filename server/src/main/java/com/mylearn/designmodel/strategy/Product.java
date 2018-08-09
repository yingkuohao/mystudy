package com.mylearn.designmodel.strategy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????11:24
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Product {

    private String name;
    private Double basePrice;
    private MemberStrategy memberStrategy;

    public Product(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    public Product(String name, Double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public double getFinalPrice() {
        double rate = memberStrategy.getDiscount();
        return basePrice * rate;
    }

    public MemberStrategy getMemberStrategy() {
        return memberStrategy;
    }

    public void setMemberStrategy(MemberStrategy memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
