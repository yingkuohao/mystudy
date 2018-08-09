package com.mylearn.nio.base;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-14
 * Time: ????4:24
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String args[]) {
        SimpleReadWrite simpleReadWrite = new SimpleReadWrite();
        String path = "F://prs_20140113090145.txt";
        FileInputStream fileInputStream = null;
        try {
            long begin = System.nanoTime();
            FileChannel fileChannel = new RandomAccessFile(new File(path), "r").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int len = 0;
            try {
                StringBuilder sb = new StringBuilder();
                while ((len = fileChannel.read(byteBuffer)) > -1) {   //??5?read??????????6????????????????????????¦Ë??????u?????¦Â??????-1
                    byteBuffer.flip();  //?????????????????postion¦Ë???0??limit?position¦Ë??
                    sb.append(new String(byteBuffer.array(), 0, len));
                }
                System.out.println("sb.tostring =" + sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            long end = System.nanoTime();
            System.out.println("total=" + (end - begin)/1000/1000);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
