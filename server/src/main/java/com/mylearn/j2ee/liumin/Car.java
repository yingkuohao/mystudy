package com.mylearn.j2ee.liumin;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/8/26
 * Time: 下午2:11
 * CopyRight: taobao
 * Descrption:   定义一个汽车对象
 */

public class Car {

    private int num;
    private String name;
    private int price; //价格量
    private int type;//1:载客,2:载货 ,3:ALL

    public Car(int num, String name, int price, int type) {
        this.num = num;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Car() {
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
