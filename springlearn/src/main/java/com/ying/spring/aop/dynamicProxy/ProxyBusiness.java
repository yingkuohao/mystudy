/**
 * Create on 2011-10-12 下午02:03:12 by tengfei.fangtf
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * 
 * All rights reserved.
 */
package com.ying.spring.aop.dynamicProxy;

import com.ying.spring.aop.Business;
import com.ying.spring.aop.IBusiness;
import com.ying.spring.aop.IBusiness2;

import java.lang.reflect.Method;


/**
 * 织入器生成的代理类
 * 
 * @author tengfei.fangtf
 */
public class ProxyBusiness implements IBusiness, IBusiness2 {

    private DynamicProxyDemo.LogInvocationHandler h;

    @Override
    public void doSomeThing2() {
        try {
            Method m = (h.target).getClass().getMethod("doSomeThing", null);
            h.invoke(this, m, null);
        } catch (Throwable e) {
            // 异常处理 1（略）
        }
    }

    @Override
    public boolean doSomeThing() {
        try {
            Method m = (h.target).getClass().getMethod("doSomeThing2", null);
            return (Boolean) h.invoke(this, m, null);
        } catch (Throwable e) {
            // 异常处理 1（略）
        }
        return false;
    }

    public ProxyBusiness(DynamicProxyDemo.LogInvocationHandler h) {
        this.h = h;
    }

    //测试用
    public static void main(String[] args) {
        //构建AOP的Advice
        staticDynamic();
    }

    public static void staticDynamic() {
        DynamicProxyDemo.LogInvocationHandler handler = new DynamicProxyDemo.LogInvocationHandler(new Business());
        new ProxyBusiness(handler).doSomeThing();
        new ProxyBusiness(handler).doSomeThing2();
    }

}
