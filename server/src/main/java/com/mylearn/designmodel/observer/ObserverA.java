package com.mylearn.designmodel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-10-31
 * Time: ????12:02
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class ObserverA implements Observer {

    private Observable observable;

    public Observable getObservable() {
        return observable;
    }

    public void setObservable(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);   //???????? ??Observable????????????Vector??????????????????????
    }

    /**
     * ???????¨ºt?????Observable??????????????????????????¨´?????update??????
     *
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg) {
        if (o instanceof GameMsg) {
            //??-???
            String msg = ((GameMsg) o).getMsg();
            display(msg);
        }
    }

    public void display(String msg) {
        System.out.println("oye??get a game msg:" + msg);
    }

}
