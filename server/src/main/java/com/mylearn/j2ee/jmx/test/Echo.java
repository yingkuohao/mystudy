package com.mylearn.j2ee.jmx.test;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/20
 * Time: 下午3:00
 * CopyRight: taobao
 * Descrption:
 */

public class Echo implements EchoMBean {
    @Override
    public void print(String yourName) {
        System.out.println("Hi " + yourName + "!");
    }
}
