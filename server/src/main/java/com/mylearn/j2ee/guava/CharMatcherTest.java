package com.mylearn.j2ee.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/7
 * Time: 10:28
 * CopyRight: taobao
 * Descrption:
 */

public class CharMatcherTest {
    public static void main(String args[]) {
        String str = "FirstName LastName汉子 +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
        //只保留某种类型的子串
        System.out.println("retainFrom" + CharMatcher.DIGIT.retainFrom(str));
        System.out.println("retainFrom" + CharMatcher.JAVA_LETTER.retainFrom(str));
        System.out.println("retainFrom" + CharMatcher.JAVA_LETTER_OR_DIGIT.retainFrom(str));

        String s = "id\n" +
                "building_id\n" +
                "status\n" +
                "content\n" +
                "info_type\n" +
                "detail_url\n" +
                "start_time\n" +
                "end_time\n" +
                "title\n";
        //类型转换，
        System.out.println("" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s));
        //移出control字符
        System.out.println("removeFrom:" + CharMatcher.JAVA_ISO_CONTROL.removeFrom(str));
        //去除两端空格，并把中间的连续空格替换成单个空格
        System.out.println("removeFrom:" + CharMatcher.WHITESPACE.trimAndCollapseFrom(str, ' '));
        //用*号替换所有数字
        System.out.println("removeFrom:" + CharMatcher.JAVA_DIGIT.replaceFrom(str, '*'));
        //只保留数字和小写字母
        System.out.println("removeFrom:" + CharMatcher.JAVA_DIGIT.or(CharMatcher.ANY.JAVA_LOWER_CASE).retainFrom(str));

        System.out.println("removeFrom:" + CharMatcher.inRange('a','z').retainFrom(str));

    }
}
