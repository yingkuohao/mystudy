package com.mylearn.j2ee.jmx.jvm.msg;

import com.mylearn.j2ee.jmx.jvm.util.JmonitorConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:52
 * CopyRight: taobao
 * Descrption:
 */

public class Heartbeat extends BaseMessage {

    @Override
    public String getType() {
        return JmonitorConstants.MSG_HEARTBEAT;
    }

    @Override
    public Map<String, Object> getBody() {
        Map<String, Object> map = new HashMap<String, Object>();
        Date now = new Date();
        map.put(JmonitorConstants.MSG_TS, now.getTime());
        return map;
    }

    public static void main(String[] args) {
        Heartbeat heartbeat = new Heartbeat();
        System.out.println(heartbeat.buildMsg());
    }

    @Override
    public boolean isRequestFormat() {
        return true;
    }

}
