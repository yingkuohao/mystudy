package com.mylearn.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/21
 * Time: ����4:45
 * CopyRight: taobao
 * Descrption:
 *   ClassPathXmlApplicationContext��refresh��������spring����,��Ҫ�Ĳ��������¼���:
 *   1.��ʼ��BeanFactory
 *   2. ����BeanFactoryProcessor����
 *   3. ע��BeanPostProcessor
 *   4. ��ʼ����������bean
 *   �����Ĳ�����ʡ����,�����refresh������ע��.
 *   �����Ĳ��ֱ��Ӧ����ִ�б����main������ӡ���ļ�������:
 *   1. ��ʼ��BeanFactory��Ҫ�Ƕ�ȡcommon-aop.xml,����һ��beanDefinition,����beanFactory
 *   2. �ڶ���ʱ�ͻ�ʵ����ʵ����BeanFactoryProcessor�ӿڵ���,������postProcessBeanFactory����,�����Ӧ����BeanFactoryPostProcessorTest��
 *   3. ���������beanFactory���õ�����ʵ����BeanFactoryProcessor�ӿڵ���,���ʵ����,�����ʵ���� BeanPostProcessorTest��BeanTest������
 *   4. ��ʼ������ͨ��xml���û���ע�����õ�bean
 *
 *   ��ÿ��bean��ʼ���Ĺ��̶��ǵ��õ�BeanFactory��getBean����,����̴������:
 *  -1. ����Ĺ��췽��
 -   2. ִ��beanNameAware���ӷ���
 -   3. ִ��BeanFactoryAware���ӷ���
 -   4. ִ��ApplicationContextAware���ӷ���
       5. ִ��BeanPostProcessor��before����
 -   6.ִ��InitializingBean���ӷ�����afterProperties
 -   7.ִ��init������xml��ָ����init-method
      8. ִ�� BeanPostProcessor��after����
 *      ���岽����Բο�BeanTest��ʼ��ʱ��ӡ������̨������
 *
 *
 * Դ����������¿ɲο�:
 * http://blog.csdn.net/linuu/article/details/50865358

 http://blog.csdn.net/linuu/article/details/50886872

 http://feiyeguohai.iteye.com/blog/1190463
 */

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("common-aop.xml");
        //���Դ���(FactoryBean)
        MyFactoryBean1.Car car = (MyFactoryBean1.Car) context.getBean("myCar");
        MyFactoryBean1 myFactoryBean1 = context.getBean("&myCar", MyFactoryBean1.class);
        MyFactoryBeanTargetInterface targetBean = context.getBean("myFactoryBeanTargetBean", MyFactoryBeanTargetInterface.class);
        MyFactoryBeanTargetInterface proxyBean = context.getBean("myProxyBean", MyFactoryBeanTargetInterface.class);
        targetBean.sayHello();
        System.out.println("proxybean say");
        proxyBean.sayHello();
        //����BeanFactoryPostProcessor��BeanPostProcessor
        BeanTest beanTest = (BeanTest) context.getBean("beanTest", BeanTest.class);
        System.out.println("beanTest=" + beanTest.toString());
        ((AbstractApplicationContext) context).registerShutdownHook();
    }
}
