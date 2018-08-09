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
        //1. ��һ����ʼ��bean���userMapper��һ��MapperFactoryBean
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-main.xml");
        //2.��һ��ͨ��getBean��������AbstractBeanFactory��getBean�ŵõ����Ĵ�����:MapperProxy
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
        //3. ������ýӿڷ���ʱ,ͨ��jdk�Ķ�̬����ִ��MapperProxy��invoke����
        User user = userMapper.getUser(1);
        Assert.assertNotNull(user);
        System.out.println("user=" + user.toString());
        //��һ�����Եõ�������,������
        TestInterface testInterface = (TestInterface) context.getBean("testInterface");
        //��һ��ִ��ʱ��invoke����,Ȼ����MapperMetod����ʱ��У��ʧ��
        testInterface.sayHello();

    }

}
