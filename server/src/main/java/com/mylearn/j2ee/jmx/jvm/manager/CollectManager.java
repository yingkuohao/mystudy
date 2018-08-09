package com.mylearn.j2ee.jmx.jvm.manager;

import com.mylearn.j2ee.jmx.jvm.collector.JvmCollector;
import com.mylearn.j2ee.jmx.jvm.util.JsonUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:24
 * CopyRight: taobao
 * Descrption:
 */

public class CollectManager {

    private static Log LOG = LogFactory.getLog(CollectManager.class);

    private static final ScheduledExecutorService minExecutorService = Executors.newScheduledThreadPool(1);

    // private static final ScheduledExecutorService halfHourExecutorService = Executors.newScheduledThreadPool(1);

    public synchronized static void startCollect() {

        minExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                JvmCollector.doCollect();
             /*   ExceptionCollector.doCollect();
                SpringMethodCollector.doCollect();
                WebUrlCollector.doCollect();
                WebIPCollector.doCollect();
                WebProfileCollector.doCollect();

                // 如果不是用druid,可能会报warning
                try {
                    SqlCollector.doCollect();
                } catch (Exception e) {
                    LOG.warn(e.getMessage());
                }*/

                LOG.info("Collect:" + new Date());
            }

        }, 5L, 10L, TimeUnit.SECONDS);
    }

    public synchronized static void geMsg() {

        minExecutorService.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                List<Map<String, Object>> gcList = JvmCollector.getJvmGCModelList();
                System.out.println("gclist=" + JsonUtils.toJsonString(gcList));

                LOG.info("Collect:" + new Date());
            }

        }, 5L, 10L, TimeUnit.SECONDS);
        // halfHourExecutorService.scheduleAtFixedRate(new Runnable() {
        //
        // @Override
        // public void run() {
        // }
        //
        // }, 5L, 1800L, TimeUnit.SECONDS);
    }
}
