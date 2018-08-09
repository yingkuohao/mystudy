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
            //���index��Ϊ����ֵ����ĳ���б������Ƿ���Ч��0<=index<size
            int i = Preconditions.checkElementIndex(4, list.size());
            System.out.println("i=" + i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //У��index��Ϊλ��ֵ����ĳ���б������Ƿ���Ч��0<=index<=size
            int i = Preconditions.checkPositionIndex(4, list.size());
            System.out.println("i=" + i);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            //У���Ƿ���Ч����������
            Preconditions.checkPositionIndexes(3, 10, list.size());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void checkPerson(int age, String name) throws Exception {
        //���boolean�Ƿ�Ϊtrue��������鴫�ݸ������Ĳ���
        Preconditions.checkArgument(name.length() > 0, "nameΪ��");
        //���value�Ƿ�Ϊnull���÷���ֱ�ӷ���value
        Preconditions.checkNotNull(name, "name is null");
        Preconditions.checkArgument(age > 0, "age �������0");
        System.out.println("age:" + age + ",name:" + name);
    }
}
