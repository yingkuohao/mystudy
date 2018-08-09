package com.mylearn.rmi.example.remote;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????3:46
 * CopyRight:360buy
 * Descrption:  ???????????
 * To change this template use File | Settings | File Templates.
 */
public class MyRemoteImpl implements MyRemote, Serializable {
    public String sayHello() throws RemoteException {
        return "server say Hello!";
    }

    public static void main(String args[]) {
        MyRemote myRemote = new MyRemoteImpl();
        try {
            Registry registry = LocateRegistry.createRegistry(2001);  //???????
            registry.rebind("RemoteHello", myRemote);     //????????
            System.out.println("server is ready");

            try {
                Thread.sleep(1000 * 20); //?????????????????runmain??????????sleep??????????????20?????
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
