package com.mylearn.spring;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/3
 * Time: 13:46
 * CopyRight: taobao
 * Descrption:����һ��Car�࣬ͨ��serviceע������Ϊspring��һ��bean
 */

@Component("car")
public class Car {

    public void fire() {
        System.out.println(" I am fired!");
    }

}
