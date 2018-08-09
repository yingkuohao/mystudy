package com.mylearn.j2ee.classloader.init;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-24
 * Time: ????11:08
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class SingleTon {

    private static SingleTon singleTon = new SingleTon(); //1.????  count1=1,count2=0

    public static int count1;
    public static int count2 = 0;

//    private static SingleTon singleTon = new SingleTon();  //2. ????  count1=1,count2=1

    private SingleTon() {
        count1++;
        count2++;
    }

    public static SingleTon getInstance() {
        return singleTon;
    }

}

 class Test{
    public static void main(String args[]) {

        SingleTon singleTon =SingleTon.getInstance();
        System.out.println((singleTon.count1));
        System.out.println((singleTon.count2));
    }
}
