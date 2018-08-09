package com.mylearn.designmodel.chain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-23
 * Time: ????10:46
 * CopyRight:360buy
 * Descrption: ???????????????????map??????????
 * To change this template use File | Settings | File Templates.
 */
public class Context {

    private Map<String, String> map = new HashMap<String, String>();

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
