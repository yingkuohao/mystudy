package com.mylearn.j2ee.jmx.jvm.msg;

import com.mylearn.j2ee.jmx.jvm.util.JmonitorConstants;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午3:33
 * CopyRight: taobao
 * Descrption:
 */

public class GetAttributeResp extends BaseMessage {

    private Object value;

    public GetAttributeResp(Object value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return JmonitorConstants.MSG_GETATTRIBUTE_RESP;
    }

    @Override
    public Object getBody() {
        return value;
    }

    @Override
    public boolean isRequestFormat() {
        return false;
    }
}
