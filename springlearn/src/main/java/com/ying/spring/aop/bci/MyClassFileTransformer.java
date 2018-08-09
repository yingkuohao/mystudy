/**
 * Create on 2011-10-16 下午07:48:33 by tengfei.fangtf
 * 
 * Copyright 1999-2100 Alibaba.com Corporation Limited.
 * 
 * All rights reserved.
 */
package com.ying.spring.aop.bci;

import javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * 字节码转换器
 * 
 * @author tengfei.fangtf
 */
public class MyClassFileTransformer implements ClassFileTransformer {

    /**
     * 字节码加载到虚拟机前会进入这个方法
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer)
            throws IllegalClassFormatException {
        System.out.println(className);
        //如果加载Business类才拦截
        if (!"model/Business".equals(className)) {
            return null;
        }

        //javassist的包名是用点分割的，需要转换下
        if (className != null && className.indexOf("/") != -1) {
            className = className.replaceAll("/", ".");
        }
        try {
            //通过包名获取类文件
            CtClass cc = ClassPool.getDefault().get(className);
            //获得指定方法名的方法
            CtMethod m = cc.getDeclaredMethod("doSomeThing");
            //在方法执行前插入代码
            m.insertBefore("{ System.out.println(\"记录日志\"); }");
            return cc.toBytecode();
        } catch (NotFoundException e) {
        } catch (CannotCompileException e) {
        } catch (IOException e) {
            //忽略异常处理
        }
        return null;
    }

    /**
     * 在main函数执行前，执行的函数
     * 
     * @param options
     * @param ins
     */
    public static void premain(String options, Instrumentation ins) {
        //注册我自己的字节码转换器
        ins.addTransformer(new MyClassFileTransformer());
    }

}
