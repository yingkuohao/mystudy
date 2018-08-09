package com.mylearn.designmodel.state;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????3:33
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class NoonState implements BaseState {
    public void handle(Context context) {
        if (context.getHour() >=12 && context.getHour() < 18) {
            System.out.println("good noon!");
        } else {
            context.setBaseState(new NightState());
            context.doRequest();
        }
    }
}
