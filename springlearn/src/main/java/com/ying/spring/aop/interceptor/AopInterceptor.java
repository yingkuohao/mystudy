package com.ying.spring.aop.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/14
 * Time: 下午7:06
 * CopyRight: taobao
 * Descrption:
 */

public class AopInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before");
        Object obj = invocation.proceed();
        System.out.println("end");
        return obj;
    }
}
