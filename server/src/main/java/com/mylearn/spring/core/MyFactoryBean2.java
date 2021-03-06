package com.mylearn.spring.core;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: 上午10:07
 * CopyRight: taobao
 * Descrption:
 * 通过FActory实现动态代理,类似于:org.springframework.aop.framework.ProxyFactoryBean .
 *
 */

public class MyFactoryBean2 implements FactoryBean<Object>, InitializingBean, DisposableBean {
    // 被代理对象实现的接口名（在使用Proxy时需要用到，用于决定生成的代理对象类型）
    private String interfaceName;

    // 被代理的对象
    private Object target;

    // 生成的代理对象
    private Object proxyObj;

    public void destroy() throws Exception {
        System.out.println("distory...");
    }


    public void afterPropertiesSet() throws Exception {

        proxyObj = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{Class.forName(interfaceName)}, new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        System.out.println("method:" + method.getName());
                        System.out.println("Method before...");
                        Object result = method.invoke(target, args);
                        System.out.println("Method after...");
                        return result;
                    }
                });

        System.out.println("afterPropertiesSet");
    }

    public Object getObject() throws Exception {
        System.out.println("getObject");
        return proxyObj;
    }

    public Class<?> getObjectType() {
        return proxyObj == null ? Object.class : proxyObj.getClass();
    }

    public boolean isSingleton() {
        return true;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxyObj() {
        return proxyObj;
    }

    public void setProxyObj(Object proxyObj) {
        this.proxyObj = proxyObj;
    }
}
