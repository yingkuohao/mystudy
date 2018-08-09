package com.mylearn.designmodel.wrapper;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-26
 * Time: ????2:14
 * CopyRight:360buy
 * Descrption: ????????
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        Component horse = new Horse(); //??????????????
        Decorator decorator =new HorseWithMoney();
        decorator.setComponent(horse);//?????????????decorator??
        Decorator decorator1 =new HorseWithGirlFriend();
        decorator1.setComponent(decorator);   //????decorator???????Component????????????????????????????decorator????
        Decorator decorator2 = new HorseWithEveryThing();
        decorator2.setComponent(decorator1);

        decorator.show();
        decorator1.show();
        decorator2.show();
    }
}
