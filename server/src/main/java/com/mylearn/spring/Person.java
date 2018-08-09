package com.mylearn.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/3
 * Time: 13:46
 * CopyRight: taobao
 * Descrption: 定义一个person类，持有一个Car对象
 */

@Service("person")
public class Person {


    @Autowired
    Car car;

    public void run() {
        System.out.println("I am begin running!");
        car.fire();
    }

}
