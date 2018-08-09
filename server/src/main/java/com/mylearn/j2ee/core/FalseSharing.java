package com.mylearn.j2ee.core;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/4/26
 * Time: ����2:16
 * CopyRight: taobao
 * Descrption:α����,              http://coderplay.iteye.com/blog/1486649
 *
 * �� Class A{
 *     int x;
 *     int y;
 * }
 * x��y������ͬһ�����ٻ�����,���һ���߳��޸�x;��һ���޸�y,����ȴ�x�޸���ɺ����ʵʩ.
 * ��Ȼ�����س��޸ĸ��Զ����ı���,������Ϊ��Щ��������������ͬһ�����ٻ�����,���ܾ�Ӱ����.
 * ��ͷ�����α����,false sharing,α���������ܵ�����ɱ��.
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
//        public long p1, p2, p3, p4, p5, p6;//ע��
    }
}
