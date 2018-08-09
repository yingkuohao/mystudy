package com.mylearn.socket.standard;

import com.mylearn.socket.standard.LocalCons;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-27
 * Time: 下午4:22
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class SocketClient {

    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new Client(i));
            t.start();
        }

    }


    static class Client implements Runnable {

        int i;

        Client(int i) {
            this.i = i;
        }

        public void run() {
            try {
                // 构造一个客户端套接字
                Socket client = new Socket(LocalCons.ip, LocalCons.port);

                OutputStream outputStream = client.getOutputStream();
                InputStream inputStream = client.getInputStream();

                String requestMsg = "客户端[" + i + "]发送一个请求： hello Server!";
                outputStream.write(requestMsg.getBytes());

                Thread.sleep(1000);
                System.out.println("客户端[" + i + "]发送请求ok");


                byte[] receiveMsg = new byte[1024];
                StringBuffer sb = new StringBuffer();
                while (inputStream.read(receiveMsg) > 0) {
                    System.out.println("22");
                    sb.append(new String(receiveMsg));
                }
                System.out.println("服务端返回信息：" + sb.toString());

                Thread.sleep(1000);
                outputStream.close();
                inputStream.close();
                client.close();
                System.out.println("客户端[\" + i + \"]退出");
            } catch (IOException e) {

                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

        }
    }
}
