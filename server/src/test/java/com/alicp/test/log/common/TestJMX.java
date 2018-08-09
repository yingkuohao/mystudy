package com.alicp.test.log.common;

import com.mylearn.j2ee.jmx.jvm.JmonitorClient;
import com.mylearn.j2ee.jmx.jvm.collector.JvmCollector;
import com.mylearn.j2ee.jmx.jvm.manager.CollectManager;
import com.mylearn.j2ee.jmx.jvm.util.JMXUtils;
import com.mylearn.j2ee.jmx.jvm.util.JsonUtils;
import com.mylearn.taobao.metadata.reactor.FangEvent;
import com.mylearn.taobao.metadata.reactor.FangReactorPublisher;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.lang.management.RuntimeMXBean;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/7/16
 * Time: 16:09
 * CopyRight: taobao
 * Descrption:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/common-aop.xml"})
public class TestJMX {
    @Resource
    FangReactorPublisher fangReactorPublisher;


    @Test
    public void testJmonitorClient() throws Exception {


        PropertyConfigurator.configure("/Users/chengjing/Project/selfLearn/master/mystudy/server/src/main/resources/log4j.properties");
//        PropertyConfigurator.configure("D:/log4j.properties");
        JMXUtils.regMbean();
        JmonitorClient client = new JmonitorClient();
        client.start();
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void testGC() throws Exception {
        JMXUtils.regMbean();
        Thread.sleep(1000);
        CollectManager.startCollect();
        CollectManager.geMsg();
//        List<Map<String, Object>> gcList = JvmCollector.getJvmGCModelList();
//        System.out.println("gclist=" + JsonUtils.toJsonString(gcList));
        Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void testAttatch() {
        try {
            //Attach 到5656的JVM进程上，后续Attach API再讲解
            VirtualMachine virtualmachine = VirtualMachine.attach("5925");

            //让JVM加载jmx Agent，后续讲到Java Instrutment再讲解
            String javaHome = virtualmachine.getSystemProperties().getProperty("java.home");
            String jmxAgent = javaHome + File.separator + "lib" + File.separator + "management-agent.jar";

            virtualmachine.loadAgent(jmxAgent, "com.sun.management.jmxremote");


            //获得连接地址
            Properties properties = virtualmachine.getAgentProperties();
            String address = (String) properties.get("com.sun.management.jmxremote.localConnectorAddress");

            //Detach
            virtualmachine.detach();

            JMXServiceURL url = new JMXServiceURL(address);
            JMXConnector connector = JMXConnectorFactory.connect(url);

            MBeanServerConnection mbsc = connector.getMBeanServerConnection();
            ObjectName runtimeObjName = new ObjectName("java.lang:type=Runtime");

            RuntimeMXBean rmxb = ManagementFactory.newPlatformMXBeanProxy(connector
                    .getMBeanServerConnection(), "java.lang:type=Runtime", RuntimeMXBean.class);
            String pid = rmxb.getName().split("@")[0];
            System.out.println("pid=" + pid);
            System.out.println("systempropr"+ rmxb.getSystemProperties().toString());
            System.out.println("厂商:" + (String) mbsc.getAttribute(runtimeObjName, "VmVendor"));
            System.out.println("程序:" + (String) mbsc.getAttribute(runtimeObjName, "VmName"));
            System.out.println("版本:" + (String) mbsc.getAttribute(runtimeObjName, "VmVersion"));
            Date starttime = new Date((Long) mbsc.getAttribute(runtimeObjName, "StartTime"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("启动时间:" + df.format(starttime));

            Long timespan = (Long) mbsc.getAttribute(runtimeObjName, "Uptime");
            System.out.println("连续工作时间:" + TestJMX.formatTimeSpan(timespan));
            //------------------------ JVM -------------------------
            //堆使用率
            ObjectName heapObjName = new ObjectName("java.lang:type=Memory");
            MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName, "HeapMemoryUsage"));
            long maxMemory = heapMemoryUsage.getMax();//堆最大
            long commitMemory = heapMemoryUsage.getCommitted();//堆当前分配
            long usedMemory = heapMemoryUsage.getUsed();
            System.out.println("heap:" + (double) usedMemory * 100 / commitMemory + "%");//堆使用率

            MemoryUsage nonheapMemoryUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(heapObjName, "NonHeapMemoryUsage"));
            long noncommitMemory = nonheapMemoryUsage.getCommitted();
            long nonusedMemory = heapMemoryUsage.getUsed();
            System.out.println("nonheap:" + (double) nonusedMemory * 100 / noncommitMemory + "%");

            ObjectName permObjName = new ObjectName("java.lang:type=MemoryPool,name=Perm Gen");
            MemoryUsage permGenUsage = MemoryUsage.from((CompositeDataSupport) mbsc.getAttribute(permObjName, "Usage"));
            long committed = permGenUsage.getCommitted();//持久堆大小
            long used = heapMemoryUsage.getUsed();//
            System.out.println("perm gen:" + (double) used * 100 / committed + "%");//持久堆使用率

            //tomcat:


        } catch (AgentLoadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String formatTimeSpan(long span) {
        long minseconds = span % 1000;

        span = span / 1000;
        long seconds = span % 60;

        span = span / 60;
        long mins = span % 60;

        span = span / 60;
        long hours = span % 24;

        span = span / 24;
        long days = span;
        return (new Formatter()).format("%1$d天 %2$02d:%3$02d:%4$02d.%5$03d", days, hours, mins, seconds, minseconds).toString();
    }

}
