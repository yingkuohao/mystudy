package com.ying.spring.aop.cglib;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/9
 * Time: 下午3:47
 * CopyRight: taobao
 * Descrption:
 */

public class TestController {
    void testPost(String appKey, Integer token, @TestAnnot String body) {
        System.out.println("111");
    }
}
