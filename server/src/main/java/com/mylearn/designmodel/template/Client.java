package com.mylearn.designmodel.template;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????4:53
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        Context context = new Context();

        AbstractFridge abstractFridge = new ElephantFridge();
        abstractFridge.execute(context);
    }
}
