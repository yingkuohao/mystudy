package com.mylearn.util.excel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Package:com.mylearn.util.excel
 * User: yingkuohao
 * Date: 12-2-23
 * Time: ????3:19
 * CopyRight:360buy
 * Descrption:
 */
public class RegexUtil {
    /**
     * ?§Ø????????????
     *
     * @param email
     * @return
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }
}
