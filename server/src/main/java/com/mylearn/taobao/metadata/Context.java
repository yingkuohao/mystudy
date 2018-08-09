package com.mylearn.taobao.metadata;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/8
 * Time: 17:16
 * CopyRight: taobao
 * Descrption:
 */

public   class Context<T> extends ConcurrentHashMap {

    private T obj; //目标对象

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
