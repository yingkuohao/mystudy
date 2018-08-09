package com.ying.spring.aop;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/9
 * Time: 下午3:15
 * CopyRight: taobao
 * Descrption:
 */

public class Business implements IBusiness, IBusiness2 {
    @Override
    public boolean doSomeThing() {
        System.out.println("doSomething");
        return true;
    }

    @Override
    public void doSomeThing2() {
        System.out.println("doSomething2");

    }
}
