package com.ying.spring.aop.aspect;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/14
 * Time: 下午6:47
 * CopyRight: taobao
 * Descrption:
 */

@Component
public class AOPTargetClassImpl2 implements AOPTargetInterface2 {


    public void sayHello2() {
        System.out.println("hello AOPTargetClassImpl2");
    }

}
