package com.mylearn.j2ee.core;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/26
 * Time: ����3:03
 * CopyRight: taobao
 * Descrption:   L1������ʧЧ.
 */

public class L1CacheMiss {
    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 62;

    private static long[][] longs;

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(10000);
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 0L;
            }
        }
        System.out.println("starting....");
        final long start = System.nanoTime();
        long sum = 0L;
        for (int r = 0; r < RUNS; r++) {
            for (int j = 0; j < DIMENSION_2; j++) {
                for (int i = 0; i < DIMENSION_1; i++) {
                    sum += longs[i][j];                 //duration = 17817285083 ,���������longs[i+1][j]�Ͳ�һ����,��ʱ��ܿ��ܻ����cache miss����Ч�ʵ���.���±ߵĴ������һ��������
                }
            }

//            for (int i = 0; i < DIMENSION_1; i++) {
//                for (int j = 0; j < DIMENSION_2; j++) {
//                    sum += longs[i][j];        //duration = 1786187588,�ڼ���longs[i][j]ʱ,longs[i][j+1]�ܿ���Ҳ�ᱻ������cache,������������longs[i][j+1]��������L1 Cache,
//                }
//            }

        }

        System.out.println("duration = " + (System.nanoTime() - start));
    }
}



