package com.mylearn.spring.core;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: 下午5:43
 * CopyRight: taobao
 * Descrption:
 * BeanNameAware 回调本身
 * 如果某个bean需要访问配置文件中本身的id属性,则可以使用BeanNameAware接口,该接口提供了回调本身的能力.
 * 实现该接口的bean,能访问到本身的id属性,该接口提供一个setBeanName(String name) 方法,
 * 该方法的参数就是bean的id.该方法在依赖关系设置之后,初始化回调(afterPropertieseSet,或init-method指定的方法)之前被执行.
 * 回调setBeanName方法可让bean获得自己的id .
 * 换言之,在程序中使用  BeanFactory.getBean(String beanName)之前，Bean的名字就已经设定好了。所以，程序中可以尽情的使用BeanName而不用担心它没有被初始化。
 *
 *
 * 让Bean对Name有知觉
 */

@Component("myBeanNameAware001")
public class MyBeanNameAware implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println("--BeanNameAware -- my name is " + name );
    }
}
