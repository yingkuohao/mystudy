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
        //返回二项式系数，即组合数，CnK ： 3
        int a = IntMath.binomial(3, 2);
        System.out.println("a=" + a);
        //返回 a、b的和，只要不溢出：   5
        System.out.println("checkAdd:" + IntMath.checkedAdd(3, 2));
        //返回 a、b的积，只要不溢出 ：6
        System.out.println("checkedMultiply:" + IntMath.checkedMultiply(3, 2));
        //返回 a的b次幂，只要不溢出 ：9
        System.out.println("checkedPow:" + IntMath.checkedPow(3, 2));
        //返回 a和b的差，只要不溢出 :1
        System.out.println("checkedSubtract:" + IntMath.checkedSubtract(3, 2));
        //返回a、b的商 :4
        System.out.println("divide-ceilling:" + IntMath.divide(10, 3, RoundingMode.CEILING));
        //返回a、b的商 :3
        System.out.println("divide-down:" + IntMath.divide(10, 3, RoundingMode.DOWN));
        //返回n的阶乘  :6
        System.out.println("divide-down:" + IntMath.factorial(3));
        //返回a,b的最大公约数:2
        System.out.println("divide-down:" + IntMath.gcd(6, 8));
        //返回是否是2的n次方，true，
        System.out.println("divide-down:" + IntMath.isPowerOfTwo(8));
        System.out.println("divide-down:" + IntMath.isPowerOfTwo(9));
        //log10
        System.out.println("divide-down:" + IntMath.log10(100, RoundingMode.DOWN));
        System.out.println("divide-down:" + IntMath.log10(11, RoundingMode.CEILING));

        System.out.println("divide-down:" + IntMath.log2(8, RoundingMode.DOWN));
        System.out.println("divide-down:" + IntMath.log2(9, RoundingMode.CEILING));
        //算术平均值   :3
        System.out.println("divide-down:" + IntMath.mean(3,4));
        //取模  :1
        System.out.println("divide-down:" + IntMath.mod(9,4));
        //取幂  :16
        System.out.println("divide-down:" + IntMath.pow(2,4));
        //取平方根 :2
        System.out.println("divide-down:" + IntMath.sqrt(4,RoundingMode.CEILING));

    }
}
