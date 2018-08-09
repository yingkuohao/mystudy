package com.mylearn.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: ����4:42
 * CopyRight: taobao
 * Descrption:
 * �������������Զ����޸��µ�beanʵ��
 */
@Component
public class

BeanPostProcessorTest implements BeanPostProcessor {

    public BeanPostProcessorTest() {
        System.out.println("---BeanPostProcessorTest init ");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("----- BeanPostProcessor before--- :" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("----- BeanPostProcessor after--- ::" + beanName);
        if (bean instanceof BeanTest) {
            System.out.println("----bean instanceof BeanTest");
            ((BeanTest) bean).setColor("yellow!");   //�޸�bean������
        }
        return bean;
    }
}
