package com.mylearn.socket.nio;

import com.mylearn.socket.standard.LocalCons;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-27
 * Time: 下午8:37
 * CopyRight:360buy
 * Descrption:  NIO实现的socket客户端
 * 1. 打开一个socketChannel
 * 2. 建立到服务端的套接字链接
 * 3. 开一个输入线程，输入信息到socketChannel；
 * 4. 轮询，读取socketchannel的信息，本例中通常socketChannel中的信息都被服务端消费了，所以拿到为空，如果不为空，返回的是“BYE”，则退出
 * To change this template use File | Settings | File Templates.
 */
public class SocketClientNIO {
    public static void main(String args[]) {
        SocketClientNIO socketClientNIO = new SocketClientNIO();
        socketClientNIO.client();
    }

    public void client() {
        try {
            SocketChannel sc = SocketChannel.open(); //打开套接字通道
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("localhost", LocalCons.port));  //建立到服务端的链接


            try {
                while (!sc.finishConnect()) {
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketClientNIO.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

            int len = 0;
            Thread keyborad = new Thread(new KeyBoardInput(sc));
            keyborad.start();    //打开输入线程
            while (true) {

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                StringBuffer sb = new StringBuffer();
                Thread.sleep(500);
                //read方法返回： 读取的字节数，可能为零，如果该通道已到达流的末尾，则返回 -1
                while ((len = sc.read(buffer)) > 0) { //读取到buffer,注意这里要设为0，别设为-1，否则一直在轮询，因为输入的数据都被服务端消费了，如果没有数据的话返回为0
                    System.out.println("len= " + len);
                    buffer.flip();
                    sb.append(new String(buffer.array(), 0, len));
                }

                if ("BYE".equals(sb.toString())) {
                    System.out.println("客户端退出");
                    keyborad.interrupt();//关闭输入流
                    sc.close(); //关闭 SocketChannel
                    sc.socket().close();//
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }

}
