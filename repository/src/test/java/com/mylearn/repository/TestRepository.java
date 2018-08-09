package com.mylearn.repository;

import com.mylearn.repository.mapper.TestInterface;
import com.mylearn.repository.mapper.UserMapper;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@ContextConfiguration(locations = {"classpath*:/spring-dao.xml"})
public class TestRepository {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TestInterface testInterface;

    @Test
    public void testHello() {
        System.out.println("\n123123123");
        testInterface.sayHello();   //��������ӿ�û�а�SELECT�ȱ�ǩ,������ִ��invoke����ʱ,��У��ʧ��,���ջᱨ��

    }

    @Test
    public void testUser() {
        System.out.println("\n123123123");

            User user = userMapper.getUser(1);
            Assert.assertNotNull(user);
            System.out.println("user=" + user.toString() + ",---i=" );
    }

}
