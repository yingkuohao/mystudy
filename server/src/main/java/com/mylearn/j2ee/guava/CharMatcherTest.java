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
        String str = "FirstName LastName���� +1 123 456 789 !@#$%^&*()_+|}{:\"?><";
        //ֻ����ĳ�����͵��Ӵ�
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
        //����ת����
        System.out.println("" + CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s));
        //�Ƴ�control�ַ�
        System.out.println("removeFrom:" + CharMatcher.JAVA_ISO_CONTROL.removeFrom(str));
        //ȥ�����˿ո񣬲����м�������ո��滻�ɵ����ո�
        System.out.println("removeFrom:" + CharMatcher.WHITESPACE.trimAndCollapseFrom(str, ' '));
        //��*���滻��������
        System.out.println("removeFrom:" + CharMatcher.JAVA_DIGIT.replaceFrom(str, '*'));
        //ֻ�������ֺ�Сд��ĸ
        System.out.println("removeFrom:" + CharMatcher.JAVA_DIGIT.or(CharMatcher.ANY.JAVA_LOWER_CASE).retainFrom(str));

        System.out.println("removeFrom:" + CharMatcher.inRange('a','z').retainFrom(str));

    }
}
