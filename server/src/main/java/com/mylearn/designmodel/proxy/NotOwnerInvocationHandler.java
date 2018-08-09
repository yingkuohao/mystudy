package com.mylearn.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-30
 * Time: ????11:17
 * CopyRight:360buy
 * Descrption:
 * ?????????????????????????????get?????????????owner??????
 * To change this template use File | Settings | File Templates.
 */
public class NotOwnerInvocationHandler implements InvocationHandler {

    private Subject subject;

    public NotOwnerInvocationHandler(Subject subject) {
        this.subject = subject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (method.getName().startsWith("get") || method.getName().equals("setHotOrNotRaing")) {
            return  method.invoke(subject, args);
        } else if (method.getName().startsWith("set")) {
            //?????????????????????????????????
            throw new IllegalAccessException();
        }
        return null;
    }
}
