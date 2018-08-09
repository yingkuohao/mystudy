package com.mylearn.designmodel.builder;

import java.nio.DoubleBuffer;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-26
 * Time: ����10:20
 * CopyRight:360buy
 * Descrption: ������ģʽ ��
 * ��ֱ��������Ҫ�Ķ��󣬶����ÿͻ����������б�Ҫ�Ĳ������ù��������õ�һ��builder����
 * Ȼ��ͻ�����builder�����ϵ���������setter�ķ�����������ÿ����صĿ�ѡ������
 * ��󣬿ͻ��˵����޲ε�build���������ɲ��ɱ�Ķ������builder������������ľ�̬��Ա�ࡣ
 * To change this template use File | Settings | File Templates.
 */
public class Dog {

    private final String name;       //����
    private final String sex;       //�Ա�
    private final int age;      //����
    private final String fur;     //ë��
    private final String breed;  //Ʒ��
    private final float weight;  //����
    private final float height;  //���


    private Dog(Builder builder) { //˽�й��췽�������Ϊ�ڲ������
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

        private final String name;       //����
        private String sex;       //�Ա�
        private int age;      //����
        private String fur;     //ë��
        private String breed;  //Ʒ��
        private float weight;  //����
        private float height;  //���

        public Builder(String name) {
            this.name = name;
        }

        /**
         * ͬ������������this����builder���󣬷�������build
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
         * build����������Dog����Ҳ��һ�ֵ���ģʽ��ʹ�÷�ʽ
         *
         * @return
         */
        public Dog build() {
            return new Dog(this);
        }
    }


}
