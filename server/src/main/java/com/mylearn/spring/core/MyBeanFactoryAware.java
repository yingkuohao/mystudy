package com.mylearn.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: 上午9:40
 * CopyRight: taobao
 * Descrption:
 * 同BeanNameAware类似,BeanFactoryAware的作用是让Bean获取配合他们的BeanFactory的引用.
 * 据源码分析,默认的beanFActory是DefaultListableBeanFactory
 * 让Bean对工厂有知觉
 */
@Component("myBeanFactoryAware001")
public class MyBeanFactoryAware implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--this.beanFActory="+beanFactory.getClass().getName());
    }
}
