/*
package com.mylearn.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * User: chenbaoan@360buy.com
 * Date: 11-10-4
 * Time: ????11:54
 *//*

public class WebHelper {

    */
/**
     * ????????????ID
     *
     * @return long
     *//*

    public static final long getUserId() {
        if (LoginContext.getLoginContext() != null) {
            return LoginContext.getLoginContext().getUserId();
        } else {
            return 0;
        }
    }

    */
/**
     * ?????????????????????
     *
     * @return long
     *//*

    public static final String getPin() {
        if (LoginContext.getLoginContext() != null) {
            return LoginContext.getLoginContext().getPin();
        } else {
            return null;
        }
    }

    */
/**
     * ?????????????????????
     *//*

    public static final String getNick() {
        if (LoginContext.getLoginContext() != null) {
            return LoginContext.getLoginContext().getNick();
        } else {
            return null;
        }
    }

    */
/**
     * ???????????????????
     *
     * @return
     *//*

    public static final Map<String, Object> getLoginInfo() {
        Map<String, Object> loginInfo = new HashMap<String, Object>();
        loginInfo.put("userId", getUserId());
        loginInfo.put("pin", getPin());
        loginInfo.put("nick", getNick());
        return loginInfo;
    }

    */
/**
     * get the user ip
     *
     * @return
     *//*

    public static final String getIpAddress() {
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(StrutsStatics.HTTP_REQUEST);
        String ip = request.getRemoteAddr();
        if (request.getHeader("x-forwarded-for") != null
                && !"unknown".equalsIgnoreCase(request.getHeader("x-forwarded-for"))) {
            ip = ip + "," + request.getHeader("x-forwarded-for");
        }
        return ip;
    }
}
*/
