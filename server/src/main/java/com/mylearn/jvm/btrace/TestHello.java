package com.mylearn.jvm.btrace;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/10/13
 * Time: 14:59
 * CopyRight: taobao
 * Descrption:
 */

public class TestHello {
    public static void main(String[] args) throws InterruptedException {
        TestHello th = new TestHello();
        while (true) {
            Thread.sleep(200 * 10);
            System.out.println("hello start!");
            Random random = new Random();
            int i= random.nextInt(9);
            System.out.println("random="+i);
            th.test( i+ 1);
        }

    }

    private Map<String, String> model = new HashMap<String, String>();

    public boolean test(int age) {
        String variable1 = "Ĭ����ʾ��Ϣ:������Ĳ����ǣ�" + age;
        String variable2 = "��ӭ����������԰";

        model.put("variable1", variable1);
        model.put("variable2", variable2);
        System.out.println("age="+age);
        if (age >= 1 && age <= 5) {
            variable1 = "��������������1��5֮��";
            variable2 = "��ӭС��������������Ϸ";

            model.put("variable1", variable1);
            model.put("variable2", variable2);

            return true;
        } else if (age > 5 && age <= 10) {
            variable1 = "��������������5��10֮��";
            variable2 = "��ӭС���ѽ���Ӣ�����ֳ�";

            model.put("variable1", variable1);
            model.put("variable2", variable2);

            return false;
        } else {
            return true;
        }
    }

}
