package com.mylearn.j2ee.guava;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/28
 * Time: 10:26
 * CopyRight: taobao
 * Descrption:
 */

public class IntMathTest {
    public static void main(String args[]) {
        //���ض���ʽϵ�������������CnK �� 3
        int a = IntMath.binomial(3, 2);
        System.out.println("a=" + a);
        //���� a��b�ĺͣ�ֻҪ�������   5
        System.out.println("checkAdd:" + IntMath.checkedAdd(3, 2));
        //���� a��b�Ļ���ֻҪ����� ��6
        System.out.println("checkedMultiply:" + IntMath.checkedMultiply(3, 2));
        //���� a��b���ݣ�ֻҪ����� ��9
        System.out.println("checkedPow:" + IntMath.checkedPow(3, 2));
        //���� a��b�ĲֻҪ����� :1
        System.out.println("checkedSubtract:" + IntMath.checkedSubtract(3, 2));
        //����a��b���� :4
        System.out.println("divide-ceilling:" + IntMath.divide(10, 3, RoundingMode.CEILING));
        //����a��b���� :3
        System.out.println("divide-down:" + IntMath.divide(10, 3, RoundingMode.DOWN));
        //����n�Ľ׳�  :6
        System.out.println("divide-down:" + IntMath.factorial(3));
        //����a,b�����Լ��:2
        System.out.println("divide-down:" + IntMath.gcd(6, 8));
        //�����Ƿ���2��n�η���true��
        System.out.println("divide-down:" + IntMath.isPowerOfTwo(8));
        System.out.println("divide-down:" + IntMath.isPowerOfTwo(9));
        //log10
        System.out.println("divide-down:" + IntMath.log10(100, RoundingMode.DOWN));
        System.out.println("divide-down:" + IntMath.log10(11, RoundingMode.CEILING));

        System.out.println("divide-down:" + IntMath.log2(8, RoundingMode.DOWN));
        System.out.println("divide-down:" + IntMath.log2(9, RoundingMode.CEILING));
        //����ƽ��ֵ   :3
        System.out.println("divide-down:" + IntMath.mean(3,4));
        //ȡģ  :1
        System.out.println("divide-down:" + IntMath.mod(9,4));
        //ȡ��  :16
        System.out.println("divide-down:" + IntMath.pow(2,4));
        //ȡƽ���� :2
        System.out.println("divide-down:" + IntMath.sqrt(4,RoundingMode.CEILING));

    }
}
