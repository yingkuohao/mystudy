package com.mylearn.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-20
 * Time: 上午10:44
 * CopyRight:360buy
 * Descrption:
 * 正则表达式测试
 * To change this template use File | Settings | File Templates.
 */
public class RegularTest {
    public static void main(String args[]) {

        method1();
        method2();
    }

    /**
     * 基本用法
     */
    private static void method1() {
        String target = "www.360buy.com! I love u!";
        String regex = "www";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(target);
        boolean b = m.find();
        if (b) {
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");

        }
    }

    public static void method2() {

        Pattern p = Pattern.compile("m(o+)n", Pattern.CASE_INSENSITIVE);
// 用Pattern类的matcher()方法生成一个Matcher对象
        Matcher m = p.matcher("moon mooon Mon mooooon Mooon");
        StringBuffer sb = new StringBuffer();
// 使用find()方法查找第一个匹配的对象
        boolean result = m.find();
// 使用循环找出模式匹配的内容替换之,再将内容加到sb里
        while (result) {
            m.appendReplacement(sb, "moon");
            result = m.find();
        }
// 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
        m.appendTail(sb);
        System.out.println("替换后内容是" + sb.toString());
    }

//    http://www.jb51.net/article/31251.htm
}
