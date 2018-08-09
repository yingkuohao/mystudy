package com.mylearn.j2ee.jmx.jvm.collector;

import com.mylearn.j2ee.jmx.jvm.JVMInfo;
import com.mylearn.j2ee.jmx.jvm.msg.GetAttribute;
import com.mylearn.j2ee.jmx.jvm.util.JMXUtils;
import com.mylearn.j2ee.jmx.jvm.util.JmonitorConstants;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:16
 * CopyRight: taobao
 * Descrption:
 */

public class JvmCollector  extends BaseCollector {

    private static ArrayBlockingQueue<Map<String, Object>> memeoryQueue = new ArrayBlockingQueue<Map<String, Object>>(
                                                                                                                      queueSize);

    private static ArrayBlockingQueue<Map<String, Object>> gcQueue      = new ArrayBlockingQueue<Map<String, Object>>(
                                                                                                                      queueSize);

    private static ArrayBlockingQueue<Map<String, Object>> threadQueue  = new ArrayBlockingQueue<Map<String, Object>>(
                                                                                                                      queueSize);

    private static JVMInfo jvmInfo      = JVMInfo.getInstance();

    @SuppressWarnings("unchecked")
    public static void doCollect() {

        // memory
        GetAttribute memoryAttribute = new GetAttribute();
        memoryAttribute.setAttributeNames(Arrays.asList("HeapMemoryInit", "HeapMemoryMax", "HeapMemoryUsed",
                                                        "NonHeapMemoryInit", "NonHeapMemoryMax", "NonHeapMemoryUsed",
                                                        "PermGenMax", "PermGenUsed", "OldGenMax", "OldGenUsed",
                                                        "EdenSpaceMax", "EdenSpaceUsed", "SurvivorMax", "SurvivorUsed"));
        memoryAttribute.setObjectName(JmonitorConstants.JMX_JVM_MEMORY_NAME);
        Map<String, Object> memoryMap = (Map<String, Object>) JMXUtils.getAttributeFormatted(memoryAttribute);
        memoryMap.put("timeStamp", new Date());
        checkQueueSize(memeoryQueue);
        memeoryQueue.offer(memoryMap);
        // gc
        GetAttribute gcAttribute = new GetAttribute();
        gcAttribute.setAttributeNames(Arrays.asList("YoungGCCollectionCount", "YoungGCCollectionTime",
                                                    "FullGCCollectionCount", "FullGCCollectionTime",
                                                    "SpanYoungGCCollectionCount", "SpanYoungGCCollectionTime",
                                                    "SpanFullGCCollectionCount", "SpanFullGCCollectionTime"));
        gcAttribute.setObjectName(JmonitorConstants.JMX_JVM_GC_NAME);
        Map<String, Object> gcMap = (Map<String, Object>) JMXUtils.getAttributeFormatted(gcAttribute);
        gcMap.put("timeStamp", new Date());
        checkQueueSize(gcQueue);
        gcQueue.offer(gcMap);

        // thread
        GetAttribute threadAttribute = new GetAttribute();
        threadAttribute.setAttributeNames(Arrays.asList("DaemonThreadCount", "ThreadCount", "TotalStartedThreadCount",
                                                        "DeadLockedThreadCount", "ProcessCpuTimeRate"));
        threadAttribute.setObjectName(JmonitorConstants.JMX_JVM_THREAD_NAME);
        Map<String, Object> threadMap = (Map<String, Object>) JMXUtils.getAttributeFormatted(threadAttribute);
        threadMap.put("timeStamp", new Date());
        checkQueueSize(threadQueue);
        threadQueue.offer(threadMap);

    }

    public static List<Map<String, Object>> getJvmMemoryModelList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> model : memeoryQueue) {
            list.add(model);
        }
        return list;
    }

    public static List<Map<String, Object>> getJvmGCModelList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> model : gcQueue) {
            list.add(model);
        }
        return list;
    }

    public static List<Map<String, Object>> getJvmThreadInfoList() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> model : threadQueue) {
            list.add(model);
        }
        return list;
    }

    public static String buildJvmInfoHtml() {
        String result = "<table>";
        result += "<tr><td class='name'>主机名</td><td>" + jvmInfo.getHostname() + "</td></tr>";
        result += "<tr><td class='name'>IP</td><td>" + jvmInfo.getLocalIP() + "</td></tr>";
        result += "<tr><td class='name'>进程ID</td><td>" + jvmInfo.getPID() + "</td></tr>";
        result += "<tr><td class='name'>启动时间</td><td>" + BaseCollector.DateFormat.format(jvmInfo.getStartTime())
                  + "</td></tr>";
        result += "<tr><td class='name'>启动参数</td><td>" + jvmInfo.getInputArguments() + "</td></tr>";
        result += "<tr><td class='name'>硬件平台</td><td>" + jvmInfo.getArch() + "</td></tr>";
        result += "<tr><td class='name'>可用CPU个数</td><td>" + jvmInfo.getAvailableProcessors() + "</td></tr>";
        result += "<tr><td class='name'>操作系统</td><td>" + jvmInfo.getOSName() + "[" + jvmInfo.getOSVersion() + "]"
                  + "</td></tr>";
        result += "<tr><td class='name'>文件编码</td><td>" + jvmInfo.getFileEncode() + "</td></tr>";
        result += "<tr><td class='name'>JVM名称</td><td>" + jvmInfo.getJVM() + "</td></tr>";
        result += "<tr><td class='name'>JavaVersion</td><td>" + jvmInfo.getJavaVersion() + "</td></tr>";
        result += "<tr><td class='name'>JavaSpecVersion</td><td>" + jvmInfo.getJavaSpecificationVersion()
                  + "</td></tr>";
        result += "<tr><td class='name'>JavaHome</td><td>" + jvmInfo.getJavaHome() + "</td></tr>";
        result += "<tr><td class='name'>JavaLibraryPath</td><td>" + jvmInfo.getJavaLibraryPath() + "</td></tr>";
        result += "<tr><td class='name'>当前装载的类总数</td><td>" + jvmInfo.getLoadedClassCount() + "</td></tr>";
        result += "<tr><td class='name'>总共装载过的类总数</td><td>" + jvmInfo.getTotalLoadedClassCount() + "</td></tr>";
        result += "<tr><td class='name'>卸载的类总数</td><td>" + jvmInfo.getUnloadedClassCount() + "</td></tr>";
        result += "<tr><td class='name'>总共编译时间</td><td>" + jvmInfo.getTotalCompilationTime() + "</td></tr>";
        result += "</table>";
        return result;
    }
}
