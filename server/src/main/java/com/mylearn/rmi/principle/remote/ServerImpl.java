package com.mylearn.rmi.principle.remote;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-30
 * Time: ????5:24
 * CopyRight:360buy
 * Descrption: ????????????????????Serilizable???
 * To change this template use File | Settings | File Templates.
 */
public class ServerImpl implements Server, Serializable {
    public String sayHello() {
        return "server say hello!";  //To change body of implemented methods use File | Settings | File Templates.
    }


    public String sayLove() {
        return "server say love!";  //To change body of implemented methods use File | Settings | File Templates.
    }
}
