package com.mylearn.spi.impl;

import com.mylearn.spi.HelloInterface;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/14
 * Time: обнГ3:54
 * CopyRight: taobao
 * Descrption:
 */

public class ImgHello implements HelloInterface {
    @Override
    public void sayHello() {
        System.out.println("Imgae hello");
    }
}
