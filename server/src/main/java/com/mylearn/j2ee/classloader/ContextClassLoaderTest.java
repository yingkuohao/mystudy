package com.mylearn.j2ee.classloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/29
 * Time: 上午10:42
 * CopyRight: taobao
 * Descrption:
 */

public class ContextClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader c1 = Thread.currentThread().getContextClassLoader();
        ClassLoader c2 = ContextClassLoaderTest.class.getClassLoader();

        System.out.println("c1=" + c1.toString());
        System.out.println("c2=" + c2.toString());
        Thread innerThread1 = new InnerThread1();
        innerThread1.start();
    }

    static class InnerThread1 extends Thread {
        @Override
        public void run() {
            try {
                URL[] urls = new URL[1];
                urls[0] = new URL("jar:file:/Users/chengjing/Project/fang/gitlab/fangplatform/fangplatform-client/target/fangplatform.jar!/");
                URLClassLoader urlClassLoader = new URLClassLoader(urls);
                Class<?> clazz = urlClassLoader.loadClass("com.taobao.fang.common.BaseDO");
                System.out.println(clazz.newInstance());

                System.out.println("InnerThread1 getClassLoader: " + clazz.getClassLoader());     //urlClassLoader
                System.out.println("InnerThread1 getContextClassLoader: " + Thread.currentThread().getContextClassLoader());   //appClassLoader

                this.setContextClassLoader(urlClassLoader);
                //设置当前ContextClassLoader为urlClassLoader
                Thread innerThread2 = new InnerThread2();
                innerThread2.start();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    static class InnerThread2 extends Thread {
        @Override
        public void run() {
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                classLoader.loadClass("com.taobao.fang.common.BaseDO");
                System.out.println("InnerThread2 getContextClassLoader: " + Thread.currentThread().getContextClassLoader());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
