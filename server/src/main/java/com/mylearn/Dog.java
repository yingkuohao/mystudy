package com.mylearn;

/**
 * Created by kuohao.ykh on 2014/8/23.
 */
public class Dog {

    private String feather;
    private String height;
    private String color;
    private String name;

    public Dog(String feather, String height, String color) {
        this.feather = feather;
        this.height = height;
        this.color = color;
    }

    public Dog() {
    }

    public void woof() {
        System.out.println("????");
    }

    public String catchBall(String ballName) {
        int i = (int) Math.random();
        if(i%2==0) {
            System.out.println(this.name+"catch a ball,it's name is "+ ballName);
            return "ok";
        }else {
            System.out.println("error!~");
            return "error";
        }
    }

    public String getFeather() {
        return feather;
    }

    public void setFeather(String feather) {
        this.feather = feather;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
