package com.mylearn.j2ee.core;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/26
 * Time: 下午2:16
 * CopyRight: taobao
 * Descrption:伪共享,              http://coderplay.iteye.com/blog/1486649
 *
 * 如 Class A{
 *     int x;
 *     int y;
 * }
 * x和y被放在同一个高速缓存区,如果一个线程修改x;另一个修改y,必须等待x修改完成后才能实施.
 * 虽然两个县城修改各自独立的变量,但是因为这些独立变量被放在同一个高速缓存区,性能就影响了.
 * 这就发生了伪共享,false sharing,伪共享是性能的无声杀手.
 *
 *   http://www.jdon.com/42451
 *   http://www.it165.net/pro/html/201403/11104.html
 */

public class FalseSharing implements Runnable {

    public static int NUM_THREADS = 4; // change
    public final static long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;
    private static VolatileLong[] longs;

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
          Thread.sleep(10000);
          System.out.println("starting....");
          if (args.length == 1) {
              NUM_THREADS = Integer.parseInt(args[0]);
          }

          longs = new VolatileLong[NUM_THREADS];
          for (int i = 0; i < longs.length; i++) {
              longs[i] = new VolatileLong();
          }
          final long start = System.nanoTime();
          runTest();
          System.out.println("duration = " + (System.nanoTime() - start));
      }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++
                ) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    private static class VolatileLong {
        public volatile long value = 0L;
//        public long p1, p2, p3, p4, p5, p6;//注释
    }
}
