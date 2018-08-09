package com.ying.spring.aop.cglib;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/9
 * Time: 下午3:50
 * CopyRight: taobao
 * Descrption:
 */

public class TestCglib {
    public static void main(String[] args) {
        MyCglibProxy proxy = new MyCglibProxy();
        //通过生成子类的方式创建代理类
        TestController testController = (TestController) proxy.getProxy(TestController.class);
        testController.testPost("abc", 123, "testBody");
    }
}
