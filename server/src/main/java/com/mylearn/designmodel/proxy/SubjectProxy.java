package com.mylearn.designmodel.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-30
 * Time: ????4:18
 * CopyRight:360buy
 * Descrption: ???????  ??????????
 * To change this template use File | Settings | File Templates.
 */
public class SubjectProxy {

    public static Subject getOwnerProxy(Subject subject) {
        return (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new OwnerInvocationHandler(subject));
    }

    public static Subject getNotOwnerProxy(Subject subject) {
        return (Subject) Proxy.newProxyInstance(subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new NotOwnerInvocationHandler(subject));
    }

}
