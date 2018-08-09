package com.mylearn.spring.core;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: ����5:43
 * CopyRight: taobao
 * Descrption:
 * BeanNameAware �ص�����
 * ���ĳ��bean��Ҫ���������ļ��б����id����,�����ʹ��BeanNameAware�ӿ�,�ýӿ��ṩ�˻ص����������.
 * ʵ�ָýӿڵ�bean,�ܷ��ʵ������id����,�ýӿ��ṩһ��setBeanName(String name) ����,
 * �÷����Ĳ�������bean��id.�÷�����������ϵ����֮��,��ʼ���ص�(afterPropertieseSet,��init-methodָ���ķ���)֮ǰ��ִ��.
 * �ص�setBeanName��������bean����Լ���id .
 * ����֮,�ڳ�����ʹ��  BeanFactory.getBean(String beanName)֮ǰ��Bean�����־��Ѿ��趨���ˡ����ԣ������п��Ծ����ʹ��BeanName�����õ�����û�б���ʼ����
 *
 *
 * ��Bean��Name��֪��
 */

@Component("myBeanNameAware001")
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println("--BeanNameAware -- my name is " + name );
    }
}
