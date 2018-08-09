package com.mylearn.j2ee.jmx.jvm;

import com.mylearn.j2ee.jmx.jvm.collector.JvmCollector;
import com.mylearn.j2ee.jmx.jvm.util.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:20
 * CopyRight: taobao
 * Descrption:
 */

public class JvmController {
    private final static Log LOG = LogFactory.getLog(FileUtils.class);

    public static void getJvmInfoData(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String jvmInfo = JvmCollector.buildJvmInfoHtml();
            Map<String, Object> content = new HashMap<String, Object>();
            content.put("data", jvmInfo);
            result.put("content", content);
        } catch (Exception e) {
            result.put("success", false);
            result.put("errorMsg", e.getMessage());
            LOG.error(e.getMessage(), e);
        }
//        outputToJSON(response, result);
    }

}
