package com.mylearn.socket.standard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-8-27
 * Time: ????5:25
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class SocketServer {
       /*
        public SocketServer(String port) {
        }
    */

        public static void main(String args[]) {
            SocketServer socketServer = new SocketServer();
            try {
                socketServer.server();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void server() throws IOException {

            //??b?????serversocket
            ServerSocket serverSocket = new ServerSocket(LocalCons.port);

            //???
            while (true) {
                //??????????????????????????????????4?????????l??????????????????????
                //??????????????????????????????????????????
                Socket socket = serverSocket.accept();

                /*??t???b???????????????????????socket??????socket????????inputStream??outputStream???????????}?????4????????
                ??????????????I/O?????????????????Socket?????????????????InputStream??Outputstream???????????��???????????��???
                ?????????????????????��???????��??OutputStream?????sendQ?????��????????????????????????????InputStream??RecvQ?��???????
                RecvQ???????????OutputStream??write???????????????RecvQ????????????????SendQ?????????
                */
                System.out.println("???????????");
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                byte[] readResult = new byte[1024];
                inputStream.read(readResult);
                System.out.println("????????????????" + new String(readResult));


                //yingkhtodo:description:??????????????????????????????????read?????????????��???
        /*        StringBuilder sb = new StringBuilder();
                while (inputStream.read(readResult) > 0) {
                    System.out.println("111");
                    sb.append(new String(readResult));
                }
                System.out.println("????????????????" + sb.toString());*/

                String responseMsg = "response";

                outputStream.write(responseMsg.getBytes());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                outputStream.close();
                inputStream.close(); //????????????????????
                socket.close();
                System.out.println("????????????");
            }

        }
    }
