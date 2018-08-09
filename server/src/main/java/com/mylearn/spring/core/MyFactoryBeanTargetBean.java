package com.mylearn.spring.core;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: ионГ10:12
 * CopyRight: taobao
 * Descrption:
 */

@Component
public class MyFactoryBeanTargetBean implements MyFactoryBeanTargetInterface {

    public void sayHello() {

        System.out.println("hello world!");
    }

}
