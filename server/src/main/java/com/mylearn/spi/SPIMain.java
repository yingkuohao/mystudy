package com.mylearn.spi;

import java.util.ServiceLoader;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/14
 * Time: 下午3:55
 * CopyRight: taobao
 * Descrption:
 *  在resource目录下放置META-INF目录,然后建立接口名的同名文件,内容为具体的接口类名全称
 */

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<HelloInterface> loader = ServiceLoader.load(HelloInterface.class);
        for (HelloInterface hello : loader) {
            hello.sayHello();
        }
    }
}
