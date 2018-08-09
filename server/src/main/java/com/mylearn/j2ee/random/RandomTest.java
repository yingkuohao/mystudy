package com.mylearn.j2ee.random;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-24
 * Time: ????10:48
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class RandomTest {
    public static void main(String args[]) {
        SecureRandom random = new SecureRandom();
         byte bytes[] = new byte[20];
         random.nextBytes(bytes);
        int i = random.nextInt(20);
        int j =random.nextInt();
        System.out.println("i="+i);
        System.out.println("j="+j);

        String s =random.getAlgorithm();
        System.out.println("s="+s+bytes.toString());
        try {
            System.out.println(new String(bytes,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
