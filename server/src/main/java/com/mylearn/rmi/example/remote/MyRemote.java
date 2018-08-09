package com.mylearn.rmi.example.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????3:42
 * CopyRight:360buy
 * Descrption: ????????
 * To change this template use File | Settings | File Templates.
 */
public interface MyRemote extends Remote {

    public String sayHello() throws RemoteException;

}
