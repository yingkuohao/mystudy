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
 * Time: ����10:07
 * CopyRight: taobao
 * Descrption:
 * ͨ��FActoryʵ�ֶ�̬����,������:org.springframework.aop.framework.ProxyFactoryBean .
 *
 */

public class MyFactoryBean2 implements FactoryBean<Object>, InitializingBean, DisposableBean {
    // ���������ʵ�ֵĽӿ�������ʹ��Proxyʱ��Ҫ�õ������ھ������ɵĴ���������ͣ�
    private String interfaceName;

    // ������Ķ���
    private Object target;

    // ���ɵĴ������
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
