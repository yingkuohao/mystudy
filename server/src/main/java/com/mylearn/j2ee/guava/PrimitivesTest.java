package com.mylearn.j2ee.guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/29
 * Time: 14:31
 * CopyRight: taobao
 * Descrption: Guava�ṩ������ͨ�ù��ߣ�����ԭ����������ͼ���API�Ľ�����ԭ�����ͺ��ֽ�������໥ת����
 * �Լ���ĳЩԭ�����͵��޷�����ʽ��֧�֡�
 * ԭ������        Guava������
 * byte             Bytes
 * short            Shorts
 * int              Ints
 * long             Longs
 * float            Floats
 * double           Doubles
 * char             Chars
 * boolean          Booleans
 */

public class PrimitivesTest {
    public static void main(String args[]) {

        int value = Ints.checkedCast(20l);
        try {
            //�����
            int value2 = Ints.checkedCast(12345678901l);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //�Ƚ�
        System.out.println("compare:" + Ints.compare(3, 4));
        //����ƴ��
        int[] insa = new int[]{1, 2, 3};
        int[] insb = new int[]{4, 5, 6};
        int[] intsc = Ints.concat(insa, insb);
        System.out.println("concat:" + Ints.join(",", intsc));
        //�Ƿ����
        System.out.println("contains:" + Ints.contains(insa, 1));

        System.out.println("fromByteArray:" + Ints.fromByteArray(new byte[]{1, 2, 3, 4, 5, 6,}));
        //join��תΪ�ַ���
        System.out.println("join:" + Ints.join(",", insa));
        System.out.println("hashCode:" + Ints.hashCode(20));
        //index
        System.out.println("index:" + Ints.indexOf(insa, 3));
        System.out.println("index:" + Ints.indexOf(intsc, insa));
        //�����Сֵ
        System.out.println("max:" + Ints.max(intsc));
        System.out.println("min:" + Ints.min(intsc));

        //asList
        List<Integer> integerList = Ints.asList(1, 2, 3);
        System.out.println("integerList=" + integerList);
        //toArray
        int[] intsd=Ints.toArray(integerList);
        System.out.println("toArray:" + Ints.join(",", intsd));

    }
}
