package com.mylearn.rules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/10/31
 * Time: 下午3:44
 * CopyRight: taobao
 * Descrption:
 */

public class OOPRules {

    public static void main(String[] args) {
        //equals.
        String a = "a";
        String b = "b";

        //1.1对象比较,Object的equals方法很容易抛空指针,应使用常量或者有只值的对象来调用equals.
        if ("a".equals(a)) {
            System.out.println("true");
        }

        //1.2推荐使用jdk7以后引入的工具类
        if (Objects.equals(a, b)) {
            System.out.println("true");
        }

        //1.3 所有包装类对象间的值比较,全部使用equals   ,对于Integer -128-127的数据会在IntegerCache.cache产生,
        // 复用已有对象,这个区间的Intger值可以使用==判断,但是这个区间外的所有数据都会落在堆上,不会复用已有对象,所以推荐equals
        Integer var1 = 123;
        Integer var2 = 123;
        if (var1.equals(var2)) {
            System.out.println("true");
        }
        // 关于

        //集合
        //1. 使用toArray(T[] array)方法,不要直接使用无参的toAray方法,会有类型强制转换.
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");

        String[] array = new String[list.size()];
        array = list.toArray(array);
        //注意sublist的使用,这里会有问题,父list也会变
        List<String> listSub = list.subList(0, 1);
        listSub.add("c");
        System.out.println("list=" + list.toString());
        //2.   asList是Arrays的内部类,不能add,否则报运行时异常
        String[] str = new String[]{"a", "b"};
        List listTmp = Arrays.asList(str);
        listTmp.add("c");
    }


}
