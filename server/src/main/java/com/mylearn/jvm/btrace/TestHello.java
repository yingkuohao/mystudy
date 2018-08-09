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
        String variable1 = "默认提示信息:你输入的参数是：" + age;
        String variable2 = "欢迎观临网上乐园";

        model.put("variable1", variable1);
        model.put("variable2", variable2);
        System.out.println("age="+age);
        if (age >= 1 && age <= 5) {
            variable1 = "你的输入参数介于1和5之间";
            variable2 = "欢迎小朋友玩益智类游戏";

            model.put("variable1", variable1);
            model.put("variable2", variable2);

            return true;
        } else if (age > 5 && age <= 10) {
            variable1 = "你的输入参数介于5和10之间";
            variable2 = "欢迎小朋友进入英语游乐场";

            model.put("variable1", variable1);
            model.put("variable2", variable2);

            return false;
        } else {
            return true;
        }
    }

}
