package com.mylearn.designmodel.state;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????3:31
 * CopyRight:360buy
 * Descrption:
 * ????:????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class MorningState implements BaseState {

    public void handle(Context context) {

        //????0-6????????????????noonState
        if (context.getHour() <= 6 && context.getHour() >= 0) {
            System.out.println("good moring,????????");
        } else {
            context.setBaseState(new NoonState());
            context.doRequest();
        }

    }
}
