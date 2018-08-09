package com.ying.spring.aop.jsvassist;

import com.ying.spring.aop.Business;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/9
 * Time: 下午4:05
 * CopyRight: taobao
 * Descrption:
 */

public class FinalJavasistTest {
    public static void main(String[] args) {
        try {
            //获取存放CtClass的容器ClassPool
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get("com.ying.spring.aop.jsvassist.FinalClass1");
            //获得指定方法名的方法
            CtMethod m = cc.getDeclaredMethod("test");
            //在方法执行前插入代码
            m.insertBefore("{ System.out.println(\"记录日志\"); }");
            ((FinalClass1) cc.toClass().newInstance()).test();
        } catch (Exception e) {
            System.out.println("e" + e);
        }
    }
}
