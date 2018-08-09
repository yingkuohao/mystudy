package com.mylearn.designmodel.observer;

import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-31
 * Time: ????11:58
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class GameMsg extends Observable {

    private String msg;
    private static String flag = "game";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        sendMsg();
    }

    private void sendMsg() {
        super.setChanged();  //????
        super.notifyObservers(); //???????
    }
}
