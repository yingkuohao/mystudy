package com.mylearn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/5/15
 * Time: 11:30
 * CopyRight: taobao
 * Descrption:
 */

@Service("resourcePathExposer")
public class ResourcePathExposer implements ServletContextAware {

    private ServletContext servletContext;
    private final static Log logger = LogFactory.getLog(ResourcePathExposer.class);

    @PostConstruct
    public void init() {
        //初始化projectName到系统中
        logger.info("ServletContextAware init!servletContext=");
        System.out.println("ServletContextAware execute!");
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        logger.info("ServletContextAware setServletContext");
        this.servletContext = servletContext;
    }
}
