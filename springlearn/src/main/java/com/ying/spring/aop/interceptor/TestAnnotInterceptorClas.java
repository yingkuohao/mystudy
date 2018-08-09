package com.ying.spring.aop.interceptor;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/15
 * Time: 上午10:15
 * CopyRight: taobao
 * Descrption:
 */

@Component
public class TestAnnotInterceptorClas {

    public void sayHello() {
        System.out.println("hello TestAnnotInterceptorClas");
    }

}
