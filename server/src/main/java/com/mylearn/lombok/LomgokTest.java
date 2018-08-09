package com.mylearn.lombok;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/3/10
 * Time: обнГ1:42
 * CopyRight: taobao
 * Descrption:
 */

public class LomgokTest {
    public static void main(String[] args) {
        User user = new User("test", 1, 2);
        user.setAge(20);
        System.out.println("user.toString=" + user.toString());
    }
}
