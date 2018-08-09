package com.mylearn.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: 下午4:45
 * CopyRight: taobao
 * Descrption:
 *   ClassPathXmlApplicationContext的refresh方法启动spring容器,主要的步骤有如下几步:
 *   1.初始化BeanFactory
 *   2. 调用BeanFactoryProcessor钩子
 *   3. 注册BeanPostProcessor
 *   4. 初始化其他单例bean
 *   其他的步骤我省略了,具体见refresh方法的注释.
 *   上面四不分别对应我们执行本类的main方法打印出的几个内容:
 *   1. 初始化BeanFactory主要是读取common-aop.xml,生成一堆beanDefinition,放入beanFactory
 *   2. 第二步时就会实例化实现了BeanFactoryProcessor接口的类,调用其postProcessBeanFactory方法,这里对应的是BeanFactoryPostProcessorTest类
 *   3. 第三步会从beanFactory中拿到所有实现了BeanFactoryProcessor接口的类,完成实例化,这里会实例化 BeanPostProcessorTest和BeanTest两个类
 *   4. 初始化其他通过xml配置或者注解配置的bean
 *
 *   而每个bean初始化的过程都是调用的BeanFactory的getBean方法,其过程大概如下:
 *  -1. 自身的构造方法
 -   2. 执行beanNameAware钩子方法
 -   3. 执行BeanFactoryAware钩子方法
 -   4. 执行ApplicationContextAware钩子方法
       5. 执行BeanPostProcessor的before方法
 -   6.执行InitializingBean钩子方法：afterProperties
 -   7.执行init方法，xml中指定的init-method
      8. 执行 BeanPostProcessor的after方法
 *      具体步骤可以参考BeanTest初始化时打印到控制台的内容
 *
 *
 * 源码分析的文章可参考:
 * http://blog.csdn.net/linuu/article/details/50865358

 http://blog.csdn.net/linuu/article/details/50886872

 http://feiyeguohai.iteye.com/blog/1190463
 */

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("common-aop.xml");
        //测试代理(FactoryBean)
        MyFactoryBean1.Car car = (MyFactoryBean1.Car) context.getBean("myCar");
        MyFactoryBean1 myFactoryBean1 = context.getBean("&myCar", MyFactoryBean1.class);
        MyFactoryBeanTargetInterface targetBean = context.getBean("myFactoryBeanTargetBean", MyFactoryBeanTargetInterface.class);
        MyFactoryBeanTargetInterface proxyBean = context.getBean("myProxyBean", MyFactoryBeanTargetInterface.class);
        targetBean.sayHello();
        System.out.println("proxybean say");
        proxyBean.sayHello();
        //测试BeanFactoryPostProcessor和BeanPostProcessor
        BeanTest beanTest = (BeanTest) context.getBean("beanTest", BeanTest.class);
        System.out.println("beanTest=" + beanTest.toString());
        ((AbstractApplicationContext) context).registerShutdownHook();
    }
}
