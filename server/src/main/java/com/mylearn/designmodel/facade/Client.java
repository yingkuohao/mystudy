package com.mylearn.designmodel.facade;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????4:52
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public static void main(String args[]) {
        //1.??????????????????:??????????
        Client client = new Client();
        client.method1();
        //2.????????????????????????????????
        Facade facade = new Facade();
        facade.cook();
    }

    /**
     * ??????????
     */
    public void method1() {
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
