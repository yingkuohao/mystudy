package com.mylearn.thread;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 12-12-21
 * Time: ????1:49
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class IntegerTest {
    private int i = 0;

    public int increamentCommon() {
        return i++;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
