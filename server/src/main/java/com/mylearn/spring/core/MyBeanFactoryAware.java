package com.mylearn.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: ����9:40
 * CopyRight: taobao
 * Descrption:
 * ͬBeanNameAware����,BeanFactoryAware����������Bean��ȡ������ǵ�BeanFactory������.
 * ��Դ�����,Ĭ�ϵ�beanFActory��DefaultListableBeanFactory
 * ��Bean�Թ�����֪��
 */
@Component("myBeanFactoryAware001")
public class MyBeanFactoryAware implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--this.beanFActory="+beanFactory.getClass().getName());
    }
}
