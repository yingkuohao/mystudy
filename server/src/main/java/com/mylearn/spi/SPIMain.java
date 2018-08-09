package com.mylearn.spi;

import java.util.ServiceLoader;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/14
 * Time: ����3:55
 * CopyRight: taobao
 * Descrption:
 *  ��resourceĿ¼�·���META-INFĿ¼,Ȼ�����ӿ�����ͬ���ļ�,����Ϊ����Ľӿ�����ȫ��
 */

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<HelloInterface> loader = ServiceLoader.load(HelloInterface.class);
        for (HelloInterface hello : loader) {
            hello.sayHello();
        }
    }
}
