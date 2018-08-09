package com.mylearn.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/5/14
 * Time: 19:50
 * CopyRight: taobao
 * Descrption:
 */

@Service("fangCommonSpringListener")
public class FangCommonSpringListener implements ApplicationListener<ContextRefreshedEvent> {

    boolean flag = true;
    private final static Log logger = LogFactory.getLog(FangCommonSpringListener.class);

    public void onApplicationEvent(ContextRefreshedEvent event) {
        String appName;
        logger.info("applicationContextname:disPlayName=" + event.getApplicationContext().getDisplayName() + ",appName=" + event.getApplicationContext().getApplicationName()+",flag="+flag);
        //默认web项目中（spring mvc）中，系统会有两个容器，一个是root application rot，另一个是projectName-servlet  context，所以为加载两次
    }
}
