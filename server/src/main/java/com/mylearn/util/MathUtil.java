package com.mylearn.util;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/9/6
 * Time: 15:52
 * CopyRight: taobao
 * Descrption:  数学相关util
 */

public class MathUtil {

    //保留几位精度
    public static BigDecimal scale(BigDecimal b, int scale) {
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        BigDecimal b = BigDecimal.valueOf(1.0);
        BigDecimal c = scale(b, 2);
        System.out.println("c=" + c);
    }
}
