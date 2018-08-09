package com.mylearn.cache;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-11-5
 * Time: ????5:00
 * CopyRight:360buy
 * Descrption: ?????????
 * To change this template use File | Settings | File Templates.
 */
public interface Computable<K, V> {

    /**
     * ??????
     *
     * @param arg
     * @return
     * @throws InterruptedException
     */
    V compute(K arg) throws InterruptedException;

}
