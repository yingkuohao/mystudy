package com.mylearn.domain;


/**
 * 登录和注册返回结果对象
 * Created by IntelliJ IDEA.
 * User: yfyangkun
 * Date: 12-7-4
 * Time: 上午10:15
 * To change this template use File | Settings | File Templates.
 */
public class LoginAndRegisterResult extends CommonResult {

    private static final long serialVersionUID = 1223159060628800282L;

    private String userPin;
    private String appLogin;

    public String getUserPin() {
        return userPin;
    }

    public void setUserPin(String userPin) {
        this.userPin = userPin;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("LoginAndRegisterResult");
        sb.append("{userPin='").append(userPin).append('\'');
        sb.append('}');
        return super.toString() + sb.toString();
    }

    public String getAppLogin() {
        return appLogin;
    }

    public void setAppLogin(String appLogin) {
        this.appLogin = appLogin;
    }
}
