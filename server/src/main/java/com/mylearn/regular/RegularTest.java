package com.mylearn.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-20
 * Time: ����10:44
 * CopyRight:360buy
 * Descrption:
 * ������ʽ����
 * To change this template use File | Settings | File Templates.
 */
public class RegularTest {
    public static void main(String args[]) {

        method1();
        method2();
    }

    /**
     * �����÷�
     */
    private static void method1() {
        String target = "www.360buy.com! I love u!";
        String regex = "www";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(target);
        boolean b = m.find();
        if (b) {
            System.out.println("ƥ��");
        } else {
            System.out.println("��ƥ��");

        }
    }

    public static void method2() {

        Pattern p = Pattern.compile("m(o+)n", Pattern.CASE_INSENSITIVE);
// ��Pattern���matcher()��������һ��Matcher����
        Matcher m = p.matcher("moon mooon Mon mooooon Mooon");
        StringBuffer sb = new StringBuffer();
// ʹ��find()�������ҵ�һ��ƥ��Ķ���
        boolean result = m.find();
// ʹ��ѭ���ҳ�ģʽƥ��������滻֮,�ٽ����ݼӵ�sb��
        while (result) {
            m.appendReplacement(sb, "moon");
            result = m.find();
        }
// ������appendTail()���������һ��ƥ����ʣ���ַ����ӵ�sb�
        m.appendTail(sb);
        System.out.println("�滻��������" + sb.toString());
    }

//    http://www.jb51.net/article/31251.htm
}
