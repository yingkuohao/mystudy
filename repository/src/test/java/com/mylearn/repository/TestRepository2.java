package com.mylearn.repository;

import com.mylearn.repository.mapper.TestInterface;
import com.mylearn.repository.mapper.UserMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/3
 * Time: 14:04
 * CopyRight: taobao
 * Descrption:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-main.xml"})
public class TestRepository2 {


    @Test
    public void testPerson() {
        System.out.println("\n---test begin!---");
        //1. 这一步初始化bean后的userMapper事一个MapperFactoryBean
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-main.xml");
        //2.这一步通过getBean方法调用AbstractBeanFactory的getBean才得到最后的代理类:MapperProxy
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
        //3. 具体调用接口方法时,通过jdk的动态代理执行MapperProxy的invoke方法
        User user = userMapper.getUser(1);
        Assert.assertNotNull(user);
        System.out.println("user=" + user.toString());
        //这一步可以得到代理类,不报错
        TestInterface testInterface = (TestInterface) context.getBean("testInterface");
        //这一步执行时调invoke方法,然后构造MapperMetod对象时会校验失败
        testInterface.sayHello();

    }

}
