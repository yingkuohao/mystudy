package com.mylearn.util.json;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-11
 * Time: ????11:01
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class User {
           private String name;
           private String sex;
           private String age;

           User(String name, String sex, String age) {
               this.name = name;
               this.sex = sex;
               this.age = age;
           }

           User() {
           }

           public String getName() {
               return name;
           }

           public void setName(String name) {
               this.name = name;
           }

           public String getSex() {
               return sex;
           }

           public void setSex(String sex) {
               this.sex = sex;
           }

           public String getAge() {
               return age;
           }

           public void setAge(String age) {
               this.age = age;
           }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
