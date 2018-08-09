package com.mylearn.designmodel.facade;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????5:12
 * CopyRight:360buy
 * Descrption: ??????????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class Facade {

    public void cook() {
        Gas gas = new Gas();
        Oil oil = new Oil();
        Vegetables vegetables = new Vegetables();
        Pot pot = new Pot();

        gas.open();
        oil.put();
        vegetables.put();
        pot.cook();
        pot.finished();
        gas.close();
    }
}
