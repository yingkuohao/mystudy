package com.mylearn.algorithm.sort;


import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-9
 * Time: ????1:46
 * CopyRight:360buy
 * Descrption: ?????????????????????}????????????????????????��??????????????????????????????????
 * ????????????��??????????????��????w????????��?????????��???????????? ??��???:n*n
 * To change this template use File | Settings | File Templates.
 */
public class InsertSort {

    public static void main(String args[]) {
        Integer[] integers = new Integer[]{12, 15, 9, 24, 6, 31};
        InsertSort insertSort = new InsertSort();
        System.out.println("?????" + StringUtils.join(integers, ","));
        insertSort.execute(integers);
        System.out.println("???" + StringUtils.join(integers, ","));
    }


    public void execute(Integer[] integers) {

        for (int i = 1; i < integers.length; i++) {
            //?????????????
            int tmp = integers[i];
            int j = i;
            //????????????????????????��???????????????j-1??????j????????????��????tmp?????????��?
            while(j>0&&integers[j-1]>tmp) {
                integers[j] = integers[j-1];
                j--;
            }
             integers[j] = tmp;
        }
    }

}
