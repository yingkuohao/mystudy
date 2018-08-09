package com.mylearn.bdd.domain;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/5/22
 * Time: 下午2:01
 * CopyRight: taobao
 * Descrption:
 */

public class Student {
    private String name;
    private int age;
    private String hobby;
    private String fatherName;
    private String motherName;
    private String brotherName;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public void setBrotherName(String brotherName) {
        this.brotherName = brotherName;
    }

    public String getName() {
        return name;
    }
}
