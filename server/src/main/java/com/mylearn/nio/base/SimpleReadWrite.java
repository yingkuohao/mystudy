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
 * Time: ����11:57
 * CopyRight:360buy
 * Descrption: �򵥵Ķ�д�ļ�����ͨIO��NIO������
 * To change this template use File | Settings | File Templates.
 */
public class SimpleReadWrite {
    public static int bufSize = 1024 * 16 * 2;
    public static String ENTERSTR = "\n";  //���н�β
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
            //��
            FileInputStream inputStream = new FileInputStream(file);  //��ȡһ���ļ�������
            SimpleReadWrite simpleRead = new SimpleReadWrite();
//            simpleRead.IORead(inputStream);
//            simpleRead.NIORead(inputStream);

            //д
            String pathWrite = "D:\\test.txt";
            File fileWrite = new File(pathWrite);
            FileOutputStream fileOutputStream = new FileOutputStream(fileWrite);
//            simpleRead.writeIO(fileOutputStream);
//            simpleRead.writeNIO(fileOutputStream);

            //��д���

            simpleRead.copyFile();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * ��ͨ��IO��д
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
     * NIO��ȡ��
     * 1. ����channel
     * 2. ����buffer
     * 3. ��channel��ȡ��buffer
     *
     * @param inputStream
     */
    public void NIORead(FileInputStream inputStream) {
        FileChannel fileChannel = inputStream.getChannel();  //��ȡһ���ļ��ܵ�
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);                     //��ȡһ��ByteBuffer
        int len = 0;
        try {
            StringBuilder sb = new StringBuilder();
            while ((len = fileChannel.read(byteBuffer)) > -1) {   //�ܵ���read��������ͨ����ȡ�������������˶��پͷ�������λ�ã������������ĩβ������-1
                byteBuffer.flip();  //������������ȡģʽ������postionλ��Ϊ0��limitΪpositionλ��
                sb.append(new String(byteBuffer.array(), 0, len));
            }
            System.out.println("sb.tostring =" + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void writeIO(FileOutputStream fileOutputStream) {
        String writeMsg = "����д���ļ�IO";
        try {
            fileOutputStream.write(writeMsg.getBytes());  //��ͨio�Ķ�д���ǵ���ģ��������new���Ե���
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeNIO(FileOutputStream fileOutputStream) {
        String writeMsg = "����д���ļ�NIO";
        FileChannel fileChannel = fileOutputStream.getChannel(); //�򿪹ܵ����ܵ���˫��ģ����Զ�������д
        byte[] bytes = writeMsg.getBytes();
        try {
            fileChannel.write(ByteBuffer.wrap(bytes, 0, bytes.length)); //д�뵽buffer������´���д���ǵ�clear buffer
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    /**
     * ����nio�Ķ�д���һ���ļ��Ŀ�����
     */
    public void copyFile() {
        File fileFrom = new File("D:\\appstore-gateway-debug.log");
        File fileTo = new File("D:\\test.txt");

        try {
            FileInputStream fileInputStream = new FileInputStream(fileFrom);
            FileChannel channelFrom = fileInputStream.getChannel();     //��������ܵ�

            FileOutputStream fileOutputStream = new FileOutputStream(fileTo);
            FileChannel chanelTo = fileOutputStream.getChannel();   //��������ܵ�

            int len = 0;
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024); //����������
            StringBuilder sb = new StringBuilder();
            while ((len = channelFrom.read(byteBuffer)) > -1) {
                byteBuffer.flip();//�л���дģʽ��position��Ϊ0������д����
                sb.append(new String(byteBuffer.array(), 0, len));
                chanelTo.write(byteBuffer); //�ѻ�����������д�뵽д�ܵ�
                byteBuffer.clear();   //д���clearһ�£�����position��limit�������´εĶ�ȡ
            }
            System.out.println("��������" + sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public static void bigFileRead(String inputPath) {
        //���
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
     * ���ж�д�ļ�
     *
     * @param fcin    ����channel
     * @param rBuffer ��buffer
     * @throws java.io.FileNotFoundException
     */
    public static void readFileByLine(FileChannel fcin, ByteBuffer rBuffer) throws FileNotFoundException {

        //3.�����ܱ�";��Ŀ���Ƚϡ�������ƥ��";
        try {
            byte[] bs = new byte[bufSize];
            StringBuffer strBuf = new StringBuffer("");
            int rSize;
            while (fcin.read(rBuffer) != -1) {
                rSize = rBuffer.position(); //�õ���ǰ��ȡ�ĵ�λ����λ�á�
                rBuffer.rewind(); //��position��Ϊ0�������ظ���
                rBuffer.get(bs, 0, rSize);  //��ȡ bufSize���ֽڵ����ݵ�buffer
                rBuffer.clear();   //���buffer��׼���ٴα�д�룬position��Ϊ0��limit��Ϊcapacity

                String tempString = new String(bs); //�õ���ȡ���ַ���
                int fromIndex = 0;
                int endIndex = 0;
                while ((endIndex = tempString.indexOf(ENTERSTR, fromIndex)) != -1) {
                    String line = tempString.substring(fromIndex, endIndex);  //����ȡ�ַ���
                    line = strBuf.append(line).toString();
                    // �õ��ַ�����ʼ����ƥ���߼�
//                    System.out.println("line=" + line);
                    try {
                        blockingQueue.put(line);
                    } catch (InterruptedException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    strBuf.delete(0, strBuf.length());
                    fromIndex = endIndex + 1;
                }
                //ÿ��ֻȡ1024���ֽڣ���excel1024���ֽڿ��ܶ�Ӧ�������ǲ����ģ����Կ϶���ʣ���ַ��������Ƕ����ǵĴ���
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
