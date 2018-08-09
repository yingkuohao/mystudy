package com.mylearn.oom;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-3-15
 * Time: ????2:08
 * CopyRight:360buy
 * Descrption:
 * -Xss128k
 *
 * To change this template use File | Settings | File Templates.
 */
public class JavaVMStackSOF {

    private int statckLength = 1;

    public void stackLeak() {
        statckLength++;
        stackLeak();
    }

    public static void main(String args[]) throws  Throwable{
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length:" + javaVMStackSOF.statckLength);
            throw e;
        }
    }
}
