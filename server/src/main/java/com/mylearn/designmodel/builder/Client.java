package com.mylearn.designmodel.builder;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-26
 * Time: 上午10:29
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        Dog dog =new Dog.Builder("花花").age(1).height(50.0f).weight(50.0f).sex("boy").breed("拉布拉多").fur("yellow").build();
        System.out.println(dog.toString());
    }
}
