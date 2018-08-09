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
public class AOPTargetClassImpl implements AOPTargetInterface {


    public void sayHello() {
        System.out.println("hello AOPTargetClassImpl");
    }

}
