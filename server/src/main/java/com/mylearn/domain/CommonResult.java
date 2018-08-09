package com.mylearn.domain;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: yfyangkun
 * Date: 12-6-29
 * Time: ????4:26
 * To change this template use File | Settings | File Templates.
 */
public class CommonResult implements Serializable {


    private static final long serialVersionUID = 2065761509463083294L;
    public boolean success = true;
    protected String message;
    protected String code;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CommonResult");
        sb.append("{success=").append(success);
        sb.append(", code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
