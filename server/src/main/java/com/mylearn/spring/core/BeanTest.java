package com.mylearn.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: 下午4:44
 * CopyRight: taobao
 * Descrption:
 * 1. 首先执行构造函数
 * 2. 然后执行BeanNameAware这个接口中的方法
 * 3.然后执行的是BeanFactoryAware这个接口中的方法
 * 4. 执行InitializingBean接口中的afterPropertiesSet方法
 * 5. 执行我们在xml中定义的init-method方法
 * 6. 执行BeanFacotryProcessor这个方法
 * 发现BeanPostProcessor没有执行当前类的初始化
 *
 *
 * -----1. BeanTest create
 ----2. call BeanNameAware interface name is:beanTest
 ----3. call BeanFactoryAware interface
 ----4. call ApplicationContextAware interface
 ----- BeanPostProcessor before--- :beanTest
 ----6. call InitializingBean interface
 ----7. call bean init method
 ----- BeanPostProcessor after--- ::beanTest
 */

//@Component
public class BeanTest implements InitializingBean, DisposableBean,BeanNameAware,BeanFactoryAware,ApplicationContextAware {

    private String name;
    private String color;

    public BeanTest() {
        System.out.println("-----1. BeanTest create");
    }

    @PostConstruct
    public void postConstruct() {
        //PostConstruct调用的是CommonAnnotationBeanPostProcessor,其本质也是一个BeanPostProcessor,所以会在InitialBean和init-method之前执行
       System.out.println("InitSequenceBean: postConstruct");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("----6. call InitializingBean interface");

    }

    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("----call DisposableBean interface");
    }

    public void _init() {
        System.out.println("----7. call bean init method");
    }

    public void _destory() {
        System.out.println("----call bean destory method");
    }

    public void setSomething(Object something) {
        System.out.println("DI ----call setSomething method");
    }



    @Override
    public void setBeanName(String name) {
        // TODO Auto-generated method stub
        System.out.println("----2. call BeanNameAware interface name is:" + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("----3. call BeanFactoryAware interface");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("----4. call ApplicationContextAware interface");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("--setName----");
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        System.out.println("--setColor----");
        this.color = color;
    }


}
