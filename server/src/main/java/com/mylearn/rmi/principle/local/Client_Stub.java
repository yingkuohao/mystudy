package com.mylearn.rmi.principle.local;

import com.mylearn.rmi.principle.remote.Server;

import java.io.*;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????5:24
 * CopyRight:360buy
 * Descrption: ??????????
 * 1. ?????????????????????????????????????
 * 2. ??????????????????????????????????????????§Ý????????
 * To change this template use File | Settings | File Templates.
 */
public class Client_Stub implements Server {

    private Socket socket;

    private void connect() {
        try {
            socket = new Socket("127.0.0.1", 9090);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String sayHello() {
        return say("sayHello");
    }

    public String sayLove() {
        return say("sayLove");
    }

    public String say(String fieldName) {
        connect();//??b????????t??
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(fieldName);      //????????????????§Ý?????????¡Â?????sayHello????
//            System.out.println("????????????request sayHello???");
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (String) objectInputStream.readObject();    //???????????????????????§Ý?

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
}
