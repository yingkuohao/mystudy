package com.ying.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/14
 * Time: 下午6:45
 * CopyRight: taobao
 * Descrption:
 */

@Aspect   //定义一个切面
@Configuration
public class AopAspect {
    @Pointcut("execution(* com.ying.spring.aop.aspect.*Impl.*(..))")
    public void excudeService() {
    }

    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) {
        Object obj = null;
        System.out.println("before");
        try {
            long begin = System.nanoTime();
            String methodName = pjp.getSignature().getName();
            String body = "";
            Class clas = pjp.getTarget().getClass();
            Method method = BeanUtils.findDeclaredMethodWithMinimalParameters(clas, methodName);
            long end = System.nanoTime();
            System.out.println("total time=" + (end - begin));
            obj = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("end");
        return obj;
    }
}
