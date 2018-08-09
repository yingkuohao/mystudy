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
 * Time: ����4:44
 * CopyRight: taobao
 * Descrption:
 * 1. ����ִ�й��캯��
 * 2. Ȼ��ִ��BeanNameAware����ӿ��еķ���
 * 3.Ȼ��ִ�е���BeanFactoryAware����ӿ��еķ���
 * 4. ִ��InitializingBean�ӿ��е�afterPropertiesSet����
 * 5. ִ��������xml�ж����init-method����
 * 6. ִ��BeanFacotryProcessor�������
 * ����BeanPostProcessorû��ִ�е�ǰ��ĳ�ʼ��
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
        //PostConstruct���õ���CommonAnnotationBeanPostProcessor,�䱾��Ҳ��һ��BeanPostProcessor,���Ի���InitialBean��init-method֮ǰִ��
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
