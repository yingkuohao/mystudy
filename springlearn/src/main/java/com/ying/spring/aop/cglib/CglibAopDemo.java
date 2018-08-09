package com.ying.spring.aop.cglib;

import java.lang.annotation.*;
import java.lang.reflect.Method;

import com.ying.spring.aop.Business;
import com.ying.spring.aop.IBusiness2;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 字节码生成机制演示AOP
 *
 * @author tengfei.fangtf
 */
public class CglibAopDemo {

    public static void main(String[] args) {
//        byteCodeGe();
        testAnnot();
    }

    public static void byteCodeGe() {
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(Business.class);
        //设置需要织入的逻辑
        enhancer.setCallback(new LogIntercept());
        //创建子类
        IBusiness2 newBusiness = (IBusiness2) enhancer.create();
        newBusiness.doSomeThing2();
    }

    /**
     * 记录日志
     */
    public static class LogIntercept implements MethodInterceptor {

        @Override
        public Object intercept(Object target, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            //执行原有逻辑
            Object rev = proxy.invokeSuper(target, args);
            //执行织入的日志
            if (method.getName().equals("doSomeThing2")) {
                System.out.println("记录日志");
            }
            return rev;
        }
    }

    static void testAnnot() {
        //创建一个织入器
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(TestController.class);
        //设置需要织入的逻辑
        enhancer.setCallback(new LogIntercept());
        //创建子类
        TestController testController = (TestController) enhancer.create();

        testController.testPost("abc", 123, "testBody");
    }

    class TestController {

        public TestController() {
        }

        void testPost(String appKey, Integer token, @TestAnnot String body) {
            System.out.println("111");
        }

    }

    @Target({ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface TestAnnot {
        boolean required() default true;
    }
}
