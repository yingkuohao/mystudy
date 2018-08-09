package com.mylearn.util;

import com.mylearn.consts.BatchConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * User: chenbaoan@360buy.com
 * Date: 11-10-20
 * Time: ????11:48
 */
public class LocalHostUtil {
    private final static Log log = LogFactory.getLog(LocalHostUtil.class);

    private static String hostName;
    private static String hostAddress;

    static {
        try {
            hostName = InetAddress.getLocalHost().getHostName();
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("init hostname error!", e);
        }
    }

    /**
     * ????????????
     *
     * @return host
     */
    public static String getMachineName() {
        return hostName;
    }

    public static String getHostAddress() {
        return hostAddress;
    }

    public static int getIpEnd() {
        if (StringUtils.isEmpty(hostAddress)) {
            return 0;
        }
        int i = hostAddress.lastIndexOf(BatchConstants.STRING_DOT);
        int lastIp = Integer.parseInt(hostAddress.substring((i + 1), hostAddress.length())) % 127;
        //+10???????
        if (lastIp < 10) {
            return lastIp + 10;
        }
        return lastIp;

    }
}
