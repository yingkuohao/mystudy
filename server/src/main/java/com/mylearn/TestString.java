package com.mylearn;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-5-8
 * Time: ����9:50
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestString {

    public static void main(String args[]) {
        TestString testString = new TestString();
        testString.subString("���ABC",6);
    /*    String target ="我爱�?23中华";

       String result = testString.splitString(target,11,"...");
       String result2 = testString.splitString1(target,10,"...");
       String result3 =  StringUtils.abbreviate(target,5);

        System.out.println("reslut1 = " + result);
        System.out.println("reslut2 = " + result2);
        System.out.println("reslut3 = " + result3);*/
    }

    public void  subString(String s,int length) {
        System.out.println("bytes:="+s.getBytes().length);
        System.out.println("s.length:="+s.length());

        System.out.println(s.getBytes()[length-1]);
        System.out.println(s.getBytes()[length-2]);
        System.out.println(s.getBytes()[length-3]);
        Thread thread=new Thread();

    }

    /**
     * ֧�ָ����ַ�
     *
     * @param str
     * @param len
     * @param elide ʡ�Է�
     * @return
     */
    private String splitString1(String str, int len, String elide) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        char[] chars = str.toCharArray();
        int sum = 0;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = String.valueOf(chars[i]).getBytes();
            sum += bytes.length;
            if (sum > len) {
                index = i;
                break;
            }
            if (sum == len) {
                index = i + 1;
                break;
            }
        }

        if (index != 0) {
            return str.substring(0, index) + elide;
        }
        return str;
    }


    /**
     * �ַ��ֽڽ�ȡ
     *
     * @param str   ԭ�ַ�
     * @param len   ��ȡ�ֽڳ���
     * @param elide ʡ�Է�(...)
     * @return String
     * @author kinglong
     * @since 2006.07.20
     */
    private String splitString(String str, int len, String elide) {
        if (str == null) {
            return "";
        }
        byte[] strByte = str.getBytes();
        int strLen = strByte.length;
        int elideLen = (elide.trim().length() == 0) ? 0 : elide.getBytes().length;
        if (len >= strLen || len < 1) {
            return str;
        }
        if (len - elideLen > 0) {
            len = len - elideLen;
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            int value = (int) strByte[i];
            if (value < 0) {
                count++;
            }
        }
        if (count % 2 != 0) {
            len = (len == 1) ? len + 1 : len - 1;
        }
        return new String(strByte, 0, len) + elide.trim();
    }


}
