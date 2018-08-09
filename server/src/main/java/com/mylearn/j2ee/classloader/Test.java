package com.mylearn.j2ee.classloader;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-23
 * Time: ????6:35
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Test {
        public static void main(String args[]) {
            System.out.println(Child.a);
            Child.doSth();

            Parent  parent =new Child();
            System.out.println(parent.b);   //????????
            parent.doOtherThing();            //???????????

        }

}

class Parent {
    static int a=3;
    int b=5;
     static {
         System.out.println("parent");
     }
    static void doSth() {
        System.out.println("parent dosth");
    }
    void doOtherThing() {
        System.out.println("parent doOtherThing");

    }
  }
class Child extends Parent {
    static int a=4;
    int b=6;
     static {
         System.out.println("child");
     }
    static void doSth() {
          System.out.println("child dosth");
      }

    void doOtherThing() {
        System.out.println("child doOtherThing");

    }
  }
