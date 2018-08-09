package com.mylearn.threadpool.blockingqueue;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-8-1
 * Time: ????7:34
 * To change this template use File | Settings | File Templates.
 */
public class BlockingQueueTest {

    private static AtomicInteger count = new AtomicInteger(0);
    private static AtomicInteger countCreate = new AtomicInteger(0);

    private static File dummy = new File("");//  ?????

    public static void main(String args[]) {
        BlockingQueue fileQueue = new ArrayBlockingQueue(5);
        String path = "F:\\360buy";
        File root = new File(path);
        FileCrawler fileCrawler = new FileCrawler(fileQueue, root);
        Indexer indexer = new Indexer(fileQueue);


        for (File file : root.listFiles()) {
            new Thread(new FileCrawler(fileQueue, file)).start();
        }

        for (int i = 0; i < 7; i++) {
            new Thread(new Indexer(fileQueue)).start();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        System.out.println("????????" + countCreate.get());
        System.out.println("??????????" + count.get());
    }


    static class FileCrawler implements Runnable {
        private final BlockingQueue fileQueue;
        private final File root;

        FileCrawler(BlockingQueue fileQueue, File root) {
            this.fileQueue = fileQueue;
            this.root = root;
        }

        public void run() {
            try {
                System.out.println("??????????" + fileQueue.size());
                crawl(root);
                fileQueue.put(dummy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void crawl(File root) throws InterruptedException {
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        crawl(file);
                    } else {
                        fileQueue.put(file);
                        countCreate.incrementAndGet();
                    }
                }
            }
        }
    }


    static class Indexer implements Runnable {

        private final BlockingQueue fileQueue;

        Indexer(BlockingQueue fileQueue) {
            this.fileQueue = fileQueue;
        }

        public void run() {
            boolean flag = true;
            while (flag) {
                try {
                    System.out.println("???????????" + fileQueue.size());
                    File file = (File) fileQueue.take();
                    if (dummy==file) {
                        fileQueue.put(file);
                        flag = false;
                    } else {
                        count.incrementAndGet();
                        System.out.println(file.getName());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
    }

}





