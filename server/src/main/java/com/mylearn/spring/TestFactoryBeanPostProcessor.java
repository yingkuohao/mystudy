package com.mylearn.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/2
 * Time: 15:25
 * CopyRight: taobao
 * Descrption:BeanFactoryPostProcessor���ô�������������spring bean��ʼ��֮ǰ��һЩ����
 * ���ȡbeandefinition����һ��bean�Ķ���ȵ�
 */
//@Service("testFactoryBeanPostProcessor")
public class TestFactoryBeanPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
   /*     String targetValue = "test";
        changeIcPv(beanFactory, "itemServiceClient", "itemService", "version", targetValue);
        String targetValue2 = "common.ic.service.version";
        changeIcPv(beanFactory, "itemQueryServiceClient", "itemQueryService", "version", targetValue2);
        changeIcPv(beanFactory, "spuServiceClient", "spuService", "version", targetValue2);

        String targetValue3 = "common.consume.ic.tair.version";
        changeIcPv(beanFactory, "itemTairManager", "configID", targetValue3);
*/
        String[] benNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : benNames) {
            System.out.println("beanName=" + beanName);
        }
        System.out.println("11");
    }

/*    private void changeIcPv(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition bd = beanFactory.getBeanDefinition("springPropertyConfigurer");
        MutablePropertyValues pv = bd.getPropertyValues();
        //�ҵ�Ŀ�����ԣ��޸�Ŀ��ֵ
        if (pv.contains("itemService")) {
//            pv.addPropertyValue("remark", "�ѱ�ע��Ϣ�޸�һ��");
            PropertyValue itemServicePv = pv.getPropertyValue("itemService");
            BeanDefinitionHolder beanDefinitionHolder = (BeanDefinitionHolder) itemServicePv.getValue();
            MutablePropertyValues mutablePropertyValues = beanDefinitionHolder.getBeanDefinition().getPropertyValues();
            mutablePropertyValues.addPropertyValue("version","test");
        }
    }*/

    private void changeIcPv(ConfigurableListableBeanFactory beanFactory, String beanName, String props, String childProp, String targetValue) {
        BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
        MutablePropertyValues pv = bd.getPropertyValues();
        //�ҵ�Ŀ�����ԣ��޸�Ŀ��ֵ
        if (pv.contains(props)) {
//            pv.addPropertyValue("remark", "�ѱ�ע��Ϣ�޸�һ��");
            PropertyValue itemServicePv = pv.getPropertyValue(props);
            BeanDefinitionHolder beanDefinitionHolder = (BeanDefinitionHolder) itemServicePv.getValue();
            MutablePropertyValues mutablePropertyValues = beanDefinitionHolder.getBeanDefinition().getPropertyValues();

            mutablePropertyValues.addPropertyValue(childProp, targetValue);
        }
    }

    private void changeIcPv(ConfigurableListableBeanFactory beanFactory, String beanName, String props, String targetValue) {
        BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
        MutablePropertyValues pv = bd.getPropertyValues();
        //�ҵ�Ŀ�����ԣ��޸�Ŀ��ֵ
        if (pv.contains(props)) {
            pv.addPropertyValue(props, targetValue);
        }
    }
}
