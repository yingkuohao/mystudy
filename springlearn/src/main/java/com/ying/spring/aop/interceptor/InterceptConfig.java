package com.ying.spring.aop.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/14
 * Time: 下午8:17
 * CopyRight: taobao
 * Descrption:
 */
@Configuration
public class InterceptConfig {


    @Bean
    public RegexpMethodPointcutAdvisor advisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
//        advisor.setPattern("com.ying.spring.aop.interceptor.TestAnnotInterceptorClas.*");
        advisor.setPattern("com.ying.spring.aop.interceptor.*Clas.*");
        advisor.setAdvice(new MethodInterceptor() {

            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("1111");
                long begin = System.nanoTime();
                Method method = invocation.getMethod();
                long end = System.nanoTime();
                System.out.println("interceptor total time=" + (end - begin));
                return invocation.proceed();
            }

        });
        return advisor;
    }

}
