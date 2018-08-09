package com.mylearn.j2ee.core;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/26
 * Time: 下午3:03
 * CopyRight: taobao
 * Descrption:   L1缓存行失效.
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
                    sum += longs[i][j];                 //duration = 17817285083 ,而如果访问longs[i+1][j]就不一样了,这时候很可能会产生cache miss导致效率低下.比下边的代码多了一个数量级
                }
            }

//            for (int i = 0; i < DIMENSION_1; i++) {
//                for (int j = 0; j < DIMENSION_2; j++) {
//                    sum += longs[i][j];        //duration = 1786187588,在加载longs[i][j]时,longs[i][j+1]很可能也会被加载至cache,所以立即访问longs[i][j+1]将会命中L1 Cache,
//                }
//            }

        }

        System.out.println("duration = " + (System.nanoTime() - start));
    }
}



