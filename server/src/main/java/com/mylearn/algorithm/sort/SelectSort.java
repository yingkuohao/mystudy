package com.mylearn.algorithm.sort;


import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-9
 * Time: ????11:36
 * CopyRight:360buy
 * Descrption: ???????:?????¦²??????????§³?????Îª??? n*n
 * To change this template use File | Settings | File Templates.
 */
public class SelectSort {

    public static void main(String args[]) {
        Integer[] integers = new Integer[]{12, 15, 9, 24, 6, 31};
        SelectSort selectSort = new SelectSort();
        System.out.println("?????" + StringUtils.join(integers, ","));
        selectSort.execute(integers);
        System.out.println("???" + StringUtils.join(integers, ","));
    }

    public void execute(Integer[] integers) {

        int tmp;
        for (int i = 0; i < integers.length - 1; i++) {
            for (int j = i + 1; j < integers.length; j++) {
                tmp = integers[i]; //tmp?????????§³?
                if (integers[j].intValue() < tmp) {
                    //????
                    integers[i] = integers[j];
                    integers[j] = tmp;
                }
            }
        }
    }
}
