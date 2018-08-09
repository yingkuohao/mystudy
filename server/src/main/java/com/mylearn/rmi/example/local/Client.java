package com.mylearn.rmi.example.local;

import com.mylearn.rmi.example.remote.MyRemote;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????3:59
 * CopyRight:360buy
 * Descrption:    ????????
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2001);
            MyRemote myRemote = (MyRemote) registry.lookup("RemoteHello");   //????????????????

            String s = myRemote.sayHello();//????????????
            System.out.println("s=" + s);
        } catch (NotBoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
