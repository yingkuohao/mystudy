package com.mylearn.j2ee.guava;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/30
 * Time: 10:57
 * CopyRight: taobao
 * Descrption:
 */

public class DateFilter {
    public static void main(String args[]) {
        String target = "18701521008";
        String target2 = "010-3789125-8849";
        String target3 = "(010)12345678-8845";
        System.out.println("" + checkPhone(target));
        System.out.println("" + checkPhone(target2));
        System.out.println("" + checkPhone(target3));
        String limit1 = "rp:1;appkey:21805915;dr:70年;outerId:5035";
        System.out.println("" + checkRigthLimit(limit1));
        String limit2 = "dr:70年";
        System.out.println("" + checkRigthLimit(limit2));
        String limit3 = "rp:1;dr:70";
        System.out.println("" + checkRigthLimit(limit3));

    }

    /**
     * 匹配电话号码
     *
     * @param target
     * @return
     */
    public static String checkPhone(String target) {
        return checkStr(target, SEL_PHONE_REGEX);
    }

    public static String checkRigthLimit(String target) {
        return checkStr(target, RIGHT_LIMIT_REGEX);
    }

    public static String checkStr(String target, String regex) {
        if(StringUtils.isBlank(regex)) {
            return null;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(target);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }


    //固化和电话的正则
    public static String SEL_PHONE_REGEX = "^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$|(^(13[0-9]|15[0|3|6|7|8|9]|18[7|8|9])\\d{8}$)";
    // 产权年限正则
    public static String RIGHT_LIMIT_REGEX = "dr:[0-9]{1,2}";
    // 产权年限前缀
    public static String RIGHT_LIMIT_PREFIX = "dr:";
}
