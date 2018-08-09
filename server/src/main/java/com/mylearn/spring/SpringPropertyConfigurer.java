package com.mylearn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/2
 * Time: 11:41
 * CopyRight: taobao
 * Descrption:��̬�޸�placeholder������,ʵ����  BeanFactoryPostProcessor��������bean��ʼ��֮ǰ
 * �õ�һЩ������Ϣ����һЩǰ�õĴ��� ������̳�PropertyPlaceholderConfigurer��������mergeProperties
 * �������ȵ��ø����merge�������ٸ��ݻ���������̬�滻����ͨ�õı�������IC���������
 */
public class SpringPropertyConfigurer extends PropertyPlaceholderConfigurer {
    private final static Log log = LogFactory.getLog(SpringPropertyConfigurer.class);


    @Override
    protected Properties mergeProperties() throws IOException {
        System.out.println("PropertyPlaceholderConfigurer execute");
        Properties mergeProperties =new Properties();
        //�����ü���Ĭ��ֵ���������super����
        setDailyValue(mergeProperties);
        //�������mergeProperties��������service��properties�е�KV�ӽ���
        mergeProperties.putAll(super.mergeProperties());
        this.setProperties(mergeProperties); //�ؼ�����,���õ�PropertyPlaceholderConfigurer�еķ���,
        //ͨ������������Զ�����ص�properties�ļ�����spring��

        log.error("init fangcomon properties end,properties=" + mergeProperties.stringPropertyNames());
        return mergeProperties;
    }

    private void setDailyValue(Properties mergeProperties) {
        mergeProperties.put("mysqlurl", "127.0.0.1");
        mergeProperties.put("mysqlport", "3306");
    }

}
