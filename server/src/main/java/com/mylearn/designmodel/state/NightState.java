package com.mylearn.designmodel.state;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????3:35
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class NightState implements BaseState {

    public void handle(Context context) {
        if (context.getHour() >= 18 && context.getHour() < 24) {
            System.out.println("good night!");
        }
    }
}
