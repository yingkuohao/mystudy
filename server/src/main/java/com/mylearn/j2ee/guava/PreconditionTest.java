package com.mylearn.j2ee.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/3/27
 * Time: 11:04
 * CopyRight: taobao
 * Descrption:
 */

public class PreconditionTest {

    public static void main(String args[]) {
        try {
            checkPerson(20, "zhangsan");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            checkPerson(20, "");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            checkPerson(20, null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            checkPerson(0, "zhangsan");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        try {
            //检查index作为索引值对于某个列表、数组是否有效，0<=index<size
            int i = Preconditions.checkElementIndex(4, list.size());
            System.out.println("i=" + i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //校验index作为位置值对于某个列表、数组是否有效，0<=index<=size
            int i = Preconditions.checkPositionIndex(4, list.size());
            System.out.println("i=" + i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //校验是否有效的索引区间
            Preconditions.checkPositionIndexes(3, 10, list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkPerson(int age, String name) throws Exception {
        //检查boolean是否为true，用来检查传递给方法的参数
        Preconditions.checkArgument(name.length() > 0, "name为空");
        //检查value是否为null，该方法直接返回value
        Preconditions.checkNotNull(name, "name is null");
        Preconditions.checkArgument(age > 0, "age 必须大于0");
        System.out.println("age:" + age + ",name:" + name);
    }
}
