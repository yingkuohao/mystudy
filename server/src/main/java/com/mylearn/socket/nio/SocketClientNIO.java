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
 * Time: ����8:37
 * CopyRight:360buy
 * Descrption:  NIOʵ�ֵ�socket�ͻ���
 * 1. ��һ��socketChannel
 * 2. ����������˵��׽�������
 * 3. ��һ�������̣߳�������Ϣ��socketChannel��
 * 4. ��ѯ����ȡsocketchannel����Ϣ��������ͨ��socketChannel�е���Ϣ��������������ˣ������õ�Ϊ�գ������Ϊ�գ����ص��ǡ�BYE�������˳�
 * To change this template use File | Settings | File Templates.
 */
public class SocketClientNIO {
    public static void main(String args[]) {
        SocketClientNIO socketClientNIO = new SocketClientNIO();
        socketClientNIO.client();
    }

    public void client() {
        try {
            SocketChannel sc = SocketChannel.open(); //���׽���ͨ��
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("localhost", LocalCons.port));  //����������˵�����


            try {
                while (!sc.finishConnect()) {
                }
            } catch (IOException ex) {
                Logger.getLogger(SocketClientNIO.class.getName()).log(Level.SEVERE,
                        null, ex);
            }

            int len = 0;
            Thread keyborad = new Thread(new KeyBoardInput(sc));
            keyborad.start();    //�������߳�
            while (true) {

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                StringBuffer sb = new StringBuffer();
                Thread.sleep(500);
                //read�������أ� ��ȡ���ֽ���������Ϊ�㣬�����ͨ���ѵ�������ĩβ���򷵻� -1
                while ((len = sc.read(buffer)) > 0) { //��ȡ��buffer,ע������Ҫ��Ϊ0������Ϊ-1������һֱ����ѯ����Ϊ��������ݶ�������������ˣ����û�����ݵĻ�����Ϊ0
                    System.out.println("len= " + len);
                    buffer.flip();
                    sb.append(new String(buffer.array(), 0, len));
                }

                if ("BYE".equals(sb.toString())) {
                    System.out.println("�ͻ����˳�");
                    keyborad.interrupt();//�ر�������
                    sc.close(); //�ر� SocketChannel
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
