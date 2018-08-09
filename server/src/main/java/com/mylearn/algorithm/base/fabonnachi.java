package com.mylearn.algorithm.base;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-5-16
 * Time: ????2:13
 * CopyRight:360buy
 * Descrption:  ????œý??????
 * To change this template use File | Settings | File Templates.
 */
public class fabonnachi {

    public static void main(String args[]) {

        fabonnachi fabonnachi = new fabonnachi();
        int n = 40;
        int a[] = new int[100];
        System.out.println("22");
        long begin = System.currentTimeMillis();
        int a2 = fabonnachi.mehotd2(n);
        long end = System.currentTimeMillis();
        long begin3 = System.currentTimeMillis();
        int a3 = fabonnachi.method3(n);
        long end3 = System.currentTimeMillis();
        long begin4 = System.currentTimeMillis();
        int a4 = fabonnachi.method4(n, a);
        long end4 = System.currentTimeMillis();

        System.out.println("a2 = " + a2 + "time:" + (end - begin));
        System.out.println("a3 = " + a3 + "time:" + (end3 - begin3));
        System.out.println("a4 = " + a4 + "time:" + (end4 - begin4));

    }


    /**
     * ????œý
     *
     * @param n
     * @return
     */
    private int method4(int n, int a[]) {


        if (n == 0 || n == 1) {
            a[n] = 1;
        } else {
            a[n] = method4(n - 1, a) + a[n - 2];
        }
        return a[n];
    }


    /**
     * ????/???
     *
     * @param n
     * @return
     */
    private int mehotd2(int n) {

        if (n == 0 || n == 1) {
            return n;
        } else {
            return mehotd2(n - 1) + mehotd2(n - 2);
        }

    }

    /**
     * ????
     *
     * @param n
     * @return
     */
    private int method3(int n) {
        int a[] = new int[100];
        a[0] = 0;
        a[1] = 1;
        for (int i = 2; i <= n; i++) {

            a[i] = a[i - 1] + a[i - 2];
        }
//           int sum = method1(n,a);
        return a[n];
    }


}
