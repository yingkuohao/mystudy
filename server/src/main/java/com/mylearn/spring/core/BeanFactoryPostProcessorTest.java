package com.mylearn.spring.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: ����4:40
 * CopyRight: taobao
 * Descrption:
 * BeanFActoryProcessor�ӿ������Զ���ȥ�޸�Ӧ���������е�beanDefinition������ײ�������bean������ֵ,
 *
 * �������Ա���BeanFactoryProcessor��BeanProcessor������: ǰ���ǴӸ������޸�Bean�Ķ���,����ֻ���޸�
 * ʵ�����Ķ���,����ʱ�޸�,������˵:BeanDefinition��һ����Ƭ,���㷢�������Ƭ������ʱ,��ظ����������Ƭ
 * ��factory������,�����BeanFactoryProcessor�Ĺ���,��BeanPostProcessorֻ���ñ���ʱ�޸���һ�����Զ���.
 *
 * �ο�:http://blog.csdn.net/linuu/article/details/50853687
 *
 * spring�е�  PropertyPlaceholderConfigurerʵ����BeanFactoryPostProcessor�ӿڣ�
 */
@Component
public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("--begin BeanFactoryPostProcessorTest--");

        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("   definition bean name:" + name);

        }
        //�޸�bean����,����Ϊname���Ը���һ����ʼֵ
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("beanTest");
        MutablePropertyValues mutablePropertyValues = beanDefinition.getPropertyValues();
        mutablePropertyValues.add("name", "benDefinitonName");
        System.out.println("--end BeanFactoryPostProcessorTest--");

    }
}
