package com.mylearn.designmodel.proxy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-30
 * Time: ????4:20
 * CopyRight:360buy
 * Descrption:   ????????????
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public static void main(String args[]) {
        Subject subject = new SubjectImpl();
        Subject subjectOwner = SubjectProxy.getOwnerProxy(subject);
        subjectOwner.setName("zhangsan");
        subjectOwner.setIntrestes("game");
        int rate = subjectOwner.getHotOrNotRating();
        System.out.println("rate = " + rate);
        try {
            subjectOwner.setHotOrNotRaing(10);
        } catch (Exception e) {
            System.out.println("can't set rating from owner proxy!");
        }


        Subject subjectNotOnwer = SubjectProxy.getNotOwnerProxy(subject);
        try {
            subjectNotOnwer.setName("lisi");
        } catch (Exception e) {
            System.out.println("can't set name from notowner proxy!");
        }
        subjectNotOnwer.setHotOrNotRaing(10);
        System.out.println("rate = " + subjectNotOnwer.getHotOrNotRating());
    }

}
