package com.mylearn.nio.base;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-28
 * Time: 上午11:57
 * CopyRight:360buy
 * Descrption: 简单的读写文件，普通IO和NIO的区别
 * To change this template use File | Settings | File Templates.
 */
public class SimpleReadWrite {
    public static int bufSize = 1024 * 16 * 2;
    public static String ENTERSTR = "\n";  //换行结尾
    public static BlockingQueue blockingQueue = new LinkedBlockingQueue();
    public static int count = 0;
    public static String dummy = "DUMMY";

    public static void main(String args[]) {
        SimpleReadWrite simpleReadWrite = new SimpleReadWrite();
        String path = "F://prs_20140113090145.txt";
//        String path = "D:\\Workspace\\mylearn\\domain\\src\\main\\java\\com\\mylearn\\nio\\base\\SimpleReadWrite.java";
        FileInputStream fileInputStream = null;
        try {
            long begin = System.nanoTime();
            fileInputStream = new FileInputStream(new File(path));
            simpleReadWrite.NIORead(fileInputStream);
            long end = System.nanoTime();
            System.out.println("total=" + (end - begin));
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.\

        }
    }

    /*   public static void main(String args[]) {
        String path = "D:\\appstore-gateway.log";
        File file = new File(path);
        try {
            //读
            FileInputStream inputStream = new FileInputStream(file);  //获取一个文件输入流
            SimpleReadWrite simpleRead = new SimpleReadWrite();
//            simpleRead.IORead(inputStream);
//            simpleRead.NIORead(inputStream);

            //写
            String pathWrite = "D:\\test.txt";
            File fileWrite = new File(pathWrite);
            FileOutputStream fileOutputStream = new FileOutputStream(fileWrite);
//            simpleRead.writeIO(fileOutputStream);
//            simpleRead.writeNIO(fileOutputStream);

            //读写结合

            simpleRead.copyFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 普通的IO读写
     */
    public void IORead(FileInputStream inputStream) {

        try {
            byte[] bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes));

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * NIO读取流
     * 1. 创建channel
     * 2. 创建buffer
     * 3. 从channel读取到buffer
     *
     * @param inputStream
     */
    public void NIORead(FileInputStream inputStream) {
        FileChannel fileChannel = inputStream.getChannel();  //获取一个文件管道
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);                     //获取一个ByteBuffer
        int len = 0;
        try {
            StringBuilder sb = new StringBuilder();
            while ((len = fileChannel.read(byteBuffer)) > -1) {   //管道的read方法：从通道读取到缓冲区，读了多少就返回最后的位置，如果读到流的末尾，返回-1
                byteBuffer.flip();  //开启缓冲区读取模式，重置postion位置为0，limit为position位置
                sb.append(new String(byteBuffer.array(), 0, len));
            }
            System.out.println("sb.tostring =" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeIO(FileOutputStream fileOutputStream) {
        String writeMsg = "测试写入文件IO";
        try {
            fileOutputStream.write(writeMsg.getBytes());  //普通io的读写流是单向的，必须各自new各自的流
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNIO(FileOutputStream fileOutputStream) {
        String writeMsg = "测试写入文件NIO";
        FileChannel fileChannel = fileOutputStream.getChannel(); //打开管道，管道是双向的，可以读，可以写
        byte[] bytes = writeMsg.getBytes();
        try {
            fileChannel.write(ByteBuffer.wrap(bytes, 0, bytes.length)); //写入到buffer，如果下次再写，记得clear buffer
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    /**
     * 利用nio的读写完成一次文件的拷贝。
     */
    public void copyFile() {
        File fileFrom = new File("D:\\appstore-gateway-debug.log");
        File fileTo = new File("D:\\test.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(fileFrom);
            FileChannel channelFrom = fileInputStream.getChannel();     //创建输入管道

            FileOutputStream fileOutputStream = new FileOutputStream(fileTo);
            FileChannel chanelTo = fileOutputStream.getChannel();   //创建输出管道

            int len = 0;
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024); //创建缓冲区
            StringBuilder sb = new StringBuilder();
            while ((len = channelFrom.read(byteBuffer)) > -1) {
                byteBuffer.flip();//切换到写模式，position设为0，否则写不了
                sb.append(new String(byteBuffer.array(), 0, len));
                chanelTo.write(byteBuffer); //把缓冲区的数据写入到写管道
                byteBuffer.clear();   //写完后clear一下，重置position和limit，方便下次的读取
            }
            System.out.println("拷贝结束" + sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static void bigFileRead(String inputPath) {
        //输出
        File fileInput = new File(inputPath);
        FileChannel fcout = null;
        try {
            FileChannel fcin = new RandomAccessFile(fileInput, "r").getChannel();
            ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);
            readFileByLine(fcin, rBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    /**
     * 按行读写文件
     *
     * @param fcin    输入channel
     * @param rBuffer 读buffer
     * @throws java.io.FileNotFoundException
     */
    public static void readFileByLine(FileChannel fcin, ByteBuffer rBuffer) throws FileNotFoundException {

        //3.遍历总表";与目标表比较。用正则匹配";
        try {
            byte[] bs = new byte[bufSize];
            StringBuffer strBuf = new StringBuffer("");
            int rSize;
            while (fcin.read(rBuffer) != -1) {
                rSize = rBuffer.position(); //得到当前读取的单位数据位置。
                rBuffer.rewind(); //将position置为0，用于重复读
                rBuffer.get(bs, 0, rSize);  //获取 bufSize个字节的数据到buffer
                rBuffer.clear();   //清空buffer，准备再次被写入，position变为0，limit变为capacity

                String tempString = new String(bs); //得到读取的字符串
                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(ENTERSTR, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);  //按行取字符串
                    line = strBuf.append(line).toString();
                    // 拿到字符串后开始处理匹配逻辑
//                    System.out.println("line=" + line);
                    try {
                        blockingQueue.put(line);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                //每次只取1024个字节，而excel1024个字节可能对应的行数是不定的，所以肯定有剩余字符，这里是对它们的处理
                if (rSize > tempString.length()) {
                    strBuf.append(tempString.substring(fromIndex, tempString.length()));
                } else {
                    if (fromIndex < rSize)
                        strBuf.append(tempString.substring(fromIndex, rSize));
                }
            }

            fcin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
