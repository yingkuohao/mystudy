package com.mylearn.spring.annotation;

import com.mylearn.spring.core.MyFactoryBean1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: ÏÂÎç5:39
 * CopyRight: taobao
 * Descrption:
 */

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("com.mylearn.spring.annotation");
        DemoService ds = context.getBean(DemoService.class);
        ds.doSth();
    }
}
