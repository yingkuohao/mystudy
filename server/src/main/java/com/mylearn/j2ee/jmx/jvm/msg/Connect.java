package com.mylearn.j2ee.jmx.jvm.msg;

import com.mylearn.j2ee.jmx.jvm.util.JmonitorConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午4:09
 * CopyRight: taobao
 * Descrption:
 */

public class Connect extends BaseMessage {

    private String appNum;

    public Connect(String appNum) {
        this.appNum = appNum;
    }

    @Override
    public String getType() {
        return JmonitorConstants.MSG_CONNECT;
    }

    @Override
    public Map<String, Object> getBody() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("VERSION", JmonitorConstants.VERSION);
        map.put("APP_NUM", appNum);
        // 兼容dragoon协议,instNum和pid暂时都不支持
        map.put("INST_NUM", null);
        map.put("PID", null);
        return map;
    }

    @Override
    public boolean isRequestFormat() {
        return true;
    }

}
