package com.mylearn.algorithm.linkloop;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-4-28
 * Time: ????10:41
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class HashFunc {

    //????hashcode
    private static HashMap<Integer, Object> hashMap = new HashMap<Integer, Object>();

    private int hash(Object obj) {
        return  obj.hashCode();
    }

    public boolean contains(Object o) {
        int code = hash(o);
        return hashMap.containsKey(code);
    }


    public boolean add(Object obj) {
        Integer code = hash(obj);
        if (hashMap.containsKey(code)) {
            return false;
        } else {
            hashMap.put(code, obj);
        }
        return true;
    }


}
