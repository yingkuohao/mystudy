package com.ying.spring.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/9
 * Time: 下午3:48
 * CopyRight: taobao
 * Descrption:
 */

public class MyCglibProxy  implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz){
     //设置需要创建子类的类
     enhancer.setSuperclass(clazz);
     enhancer.setCallback(this);
     //通过字节码技术动态创建子类实例
     return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("后置代理");
        return result;
    }
}
