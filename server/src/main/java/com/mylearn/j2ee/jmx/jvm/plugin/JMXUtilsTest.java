package com.mylearn.j2ee.jmx.jvm.plugin;

import com.mylearn.j2ee.jmx.jvm.msg.GetAttribute;
import com.mylearn.j2ee.jmx.jvm.msg.GetAttributeResp;
import com.mylearn.j2ee.jmx.jvm.util.FileUtils;
import com.mylearn.j2ee.jmx.jvm.util.JMXUtils;
import com.mylearn.j2ee.jmx.jvm.util.JmonitorConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:31
 * CopyRight: taobao
 * Descrption:
 */

public class JMXUtilsTest {
    private final static Log LOG = LogFactory.getLog(JMXUtilsTest.class);

     @SuppressWarnings("unchecked")
     public static void getJVMInfo() throws Exception {
         GetAttribute getAttribute = new GetAttribute();
         getAttribute.setObjectName(JmonitorConstants.JMX_JVM_INFO_NAME);
         List<String> attributeNames = new ArrayList<String>();
         attributeNames.add("StartTime");
         attributeNames.add("JVM");
         attributeNames.add("JavaVersion");
         attributeNames.add("PID");
         attributeNames.add("InputArguments");
         attributeNames.add("JavaHome");
         attributeNames.add("Arch");
         attributeNames.add("OSName");
         attributeNames.add("OSVersion");
         attributeNames.add("JavaSpecificationVersion");
         attributeNames.add("JavaLibraryPath");
         attributeNames.add("AvailableProcessors");
         attributeNames.add("LoadedClassCount");
         attributeNames.add("TotalLoadedClassCount");
         attributeNames.add("UnloadedClassCount");
         attributeNames.add("TotalCompilationTime");
         attributeNames.add("FileEncode");
         getAttribute.setAttributeNames(attributeNames);
         Map<String, Object> result = (Map<String, Object>) JMXUtils.getAttributeFormatted(getAttribute);
         GetAttributeResp attributeResp = new GetAttributeResp(result);
         System.out.println("JVMInfo:");
         System.out.println(attributeResp.buildMsg());
     }

     @SuppressWarnings("unchecked")
     public static void getException() throws Exception {
         GetAttribute getAttribute = new GetAttribute();
         getAttribute.setObjectName(JmonitorConstants.JMX_EXCEPTION_NAME);
         List<String> attributeNames = new ArrayList<String>();
         attributeNames.add("ErrorList");
         getAttribute.setAttributeNames(attributeNames);
         Map<String, Object> result = (Map<String, Object>) JMXUtils.getAttributeFormatted(getAttribute);
         GetAttributeResp attributeResp = new GetAttributeResp(result);
         System.out.println("Exception:");
         System.out.println(attributeResp.buildMsg());
     }

     public static void logError1() {
         LOG.error("error info 1");
         LOG.error("error info 1");
     }

     public static void logError2() {
         LOG.error("error info 2");
         LOG.error("error info 2");
         LOG.error("error info 2");
     }

     public static void getAllInfo() throws Exception {
//         PropertyConfigurator.configure("D:/jmonitor_log4j.properties");
         logError1();
         logError2();
         JMXUtils.regMbean();
         getJVMInfo();
         getException();
     }

     public static void main(String[] args) throws Exception {
         getAllInfo();
         Thread.sleep(Long.MAX_VALUE);
     }



}
