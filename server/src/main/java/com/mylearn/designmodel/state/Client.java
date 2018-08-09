package com.mylearn.designmodel.state;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????3:36
 * CopyRight:360buy
 * Descrption:
 * ????????????context????????
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
     Context context = new Context();
        context.setBaseState(new MorningState());
        context.setHour(5);
        context.doRequest();

        context.setHour(19);
        context.doRequest();

    }
}
