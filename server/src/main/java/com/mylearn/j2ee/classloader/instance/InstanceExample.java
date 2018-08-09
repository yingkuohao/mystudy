package com.mylearn.j2ee.classloader.instance;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-30
 * Time: ????6:13
 * CopyRight:360buy
 * Descrption:  ????????4?????
 * To change this template use File | Settings | File Templates.
 */
public class InstanceExample {
    public static void main(String args[]) {
        //1.????1??new????
        InstanceExample instanceExample = new InstanceExample();

        try {
            //2.????2??????
            Class clazz = Class.forName("com.mylearn.j2ee.classloader.instance.InstanceExample");
            InstanceExample instanceExample1 = (InstanceExample) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        try {
            //3.??????
            InstanceExample instanceExample2 = (InstanceExample) instanceExample.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //4.?????§Ý?

    }
}
