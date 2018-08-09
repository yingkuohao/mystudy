package com.mylearn.designmodel.builder;

import java.nio.DoubleBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-26
 * Time: 上午10:20
 * CopyRight:360buy
 * Descrption: 建造者模式 ：
 * 不直接生成想要的对象，而是让客户端利用所有必要的参数调用构造器，得到一个builder对象。
 * 然后客户端在builder对象上调用类似于setter的方法，来设置每个相关的可选参数。
 * 最后，客户端调用无参的build方法来生成不可变的对象。这个builder是它构建的类的静态成员类。
 * To change this template use File | Settings | File Templates.
 */
public class Dog {

    private final String name;       //名称
    private final String sex;       //性别
    private final int age;      //年龄
    private final String fur;     //毛发
    private final String breed;  //品种
    private final float weight;  //体重
    private final float height;  //身高


    private Dog(Builder builder) { //私有构造方法，入参为内部类对象
        name = builder.name;
        sex = builder.sex;
        age = builder.age;
        fur = builder.fur;
        breed = builder.breed;
        weight = builder.weight;
        height = builder.height;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", fur='" + fur + '\'' +
                ", breed='" + breed + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }

    public static class Builder {

        private final String name;       //名称
        private String sex;       //性别
        private int age;      //年龄
        private String fur;     //毛发
        private String breed;  //品种
        private float weight;  //体重
        private float height;  //身高

        public Builder(String name) {
            this.name = name;
        }

        /**
         * 同名方法，返回this，即builder对象，方面连续build
         *
         * @param sex
         * @return
         */
        public Builder sex(String sex) {
            this.sex = sex;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder fur(String fur) {
            this.fur = fur;
            return this;
        }

        public Builder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder weight(float weight) {
            this.weight = weight;
            return this;
        }

        public Builder height(float height) {
            this.height = height;
            return this;
        }

        /**
         * build方法，构造Dog对象，也是一种单例模式的使用方式
         *
         * @return
         */
        public Dog build() {
            return new Dog(this);
        }
    }


}
