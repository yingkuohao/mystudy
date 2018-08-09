package com.mylearn.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * User: Created by chenbaoan@360buy.com
 * Date: 2011-4-27
 * Time: 11:21:30.
 */
public class MD5Util {
    private final static Logger log = Logger.getLogger(MD5Util.class);

    private MD5Util() {
    }

    static MessageDigest getDigest() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error("MD5Util getDigest NoSuchAlgorithmException" + e);
        }
        return md;
    }

    public static byte[] md5(byte data[]) {
        return getDigest().digest(data);
    }

    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

/*
    public static String md5Hex(byte data[]) {
        return HexUtil.toHexString(md5(data));
    }

    public static String md5Hex(String data) {
        return HexUtil.toHexString(md5(data));
    }
*/

/*    public static void main(String[] args) {
        String token = "ebook!@#$%^&*()admin";
        String timestamp = DateUtil.date2String(new Date());
        System.out.println(timestamp + "|" + MD5Util.md5Hex(timestamp + token));
    }*/
}
