package com.mylearn.designmodel.singleton;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-24
 * Time: ????2:41
 * CopyRight:360buy
 * Descrption:????????????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class SingleTon2 {

    private static SingleTon2 singleTon2 = null;

    private SingleTon2() {

    }

    /**
     * ????????
     * @return
     */
    public static SingleTon2 getInstance() {
        if (singleTon2 == null) {
            singleTon2 = new SingleTon2();
        }
        return singleTon2;
    }

    /**
     * ????????????
     *
     * @return
     */
    public static SingleTon2 getInstance2() {
        if (singleTon2 == null) {
            synchronized (SingleTon2.class) {
                if (singleTon2 == null) {
                    singleTon2 = new SingleTon2();
                }
            }
        }
        return singleTon2;
    }

}


