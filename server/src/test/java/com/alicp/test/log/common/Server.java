package com.alicp.test.log.common;
import com.mylearn.j2ee.jmx.jvm.manager.CollectManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/1/22
 * Time: 下午4:18
 * CopyRight: taobao
 * Descrption:
 */

public class Server {
    ServerSocket ss ;
     Socket serverSocket;
     InputStream inStream;
     OutputStream outStream;

     public Server(){
      try {
       System.out.println("====================Server==================");
       ss = new ServerSocket(19777);
       serverSocket= ss.accept();
       System.out.println("--------------some guest connected----------------");
       inStream = serverSocket.getInputStream();
       outStream = serverSocket.getOutputStream();

      } catch (Exception e) {
       e.printStackTrace();
      }
     }

     public void conn(){
      new Thread(){
       public void run(){
        try {
         InputStreamReader iReader = new InputStreamReader(inStream);
         BufferedReader iBufferStream = new BufferedReader(iReader);
         String inMessage = iBufferStream.readLine();


         while(inMessage!="exit"){
          System.out.println("h say <<---  " + inMessage);
          System.out.print(" u say --->>  ");
          byte[] outArr = new byte[100];
          System.in.read(outArr);
          outStream.write(outArr);
          System.out.println("====== " + new String(outArr).toString());

          inMessage = iBufferStream.readLine();
         }
        } catch (IOException e) {
         e.printStackTrace();
        }
       }
      }.start();
     }

     public static void main(String args[]){
      Server server = new Server();
      server.conn();
         CollectManager.startCollect();
     }

}
