package com.mylearn.algorithm.sort;


import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-9
 * Time: ????11:19
 * CopyRight:360buy
 * Descrption: ???????????????????¦²???????????u????ÁÙ???n*n
 * To change this template use File | Settings | File Templates.
 */
public class BubboSort {

    public static void main(String args[]) {
        Integer[] integers = new Integer[]{12, 15, 9, 24, 6, 31};
        BubboSort bubboSort = new BubboSort();
        System.out.println("?????" + StringUtils.join(integers, ","));
        bubboSort.execute(integers);
        System.out.println("???" + StringUtils.join(integers, ","));

    }

    /**
     * ???????
     * @param objects
     */
    public void execute(Integer[] objects) {

        for (int i = objects.length-1 ; i > 0; i--) {
            //???i???¦¶??????????-1???????¡À??j+1?????
            for (int j = 0; j < i; j++) {
            //??????????????????????su??
                if (objects[j] > objects[j + 1]) {
                    //???j>j=1,???
                    swap(objects,j,j+1);
                }
            }
        }

    }

    /**
     * ????
     * @param integers
     * @param i
     * @param j
     */
    private void swap(Integer[] integers, Integer i,Integer j) {
        Integer tmp = integers[i];
        integers[i] = integers[j];
        integers[j] = tmp;
    }

}
