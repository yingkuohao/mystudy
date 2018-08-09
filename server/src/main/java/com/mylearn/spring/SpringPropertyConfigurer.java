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
 * Descrption:动态修改placeholder的内容,实现了  BeanFactoryPostProcessor，用于在bean初始化之前
 * 拿到一些配置信息，做一些前置的处理 。这里继承PropertyPlaceholderConfigurer，覆盖其mergeProperties
 * 方法，先调用父类的merge方法，再根据环境变量动态替换其他通用的变量，如IC的配置项等
 */
public class SpringPropertyConfigurer extends PropertyPlaceholderConfigurer {
    private final static Log log = LogFactory.getLog(SpringPropertyConfigurer.class);


    @Override
    protected Properties mergeProperties() throws IOException {
        System.out.println("PropertyPlaceholderConfigurer execute");
        Properties mergeProperties =new Properties();
        //先设置几个默认值，否则调用super报错
        setDailyValue(mergeProperties);
        //调父类的mergeProperties方法，把service。properties中的KV加进来
        mergeProperties.putAll(super.mergeProperties());
        this.setProperties(mergeProperties); //关键方法,调用的PropertyPlaceholderConfigurer中的方法,
        //通过这个方法将自定义加载的properties文件加入spring中

        log.error("init fangcomon properties end,properties=" + mergeProperties.stringPropertyNames());
        return mergeProperties;
    }

    private void setDailyValue(Properties mergeProperties) {
        mergeProperties.put("mysqlurl", "127.0.0.1");
        mergeProperties.put("mysqlport", "3306");
    }

}
