package com.mylearn.repository;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/23
 * Time: ����11:21
 * CopyRight: taobao
 * Descrption:
 */

public class User {

    private Integer id;
    private String name;
    private Integer age;
    private String testCol;
    /**
   	 * 创建时间
   	 */
   	private Date gmtCreate;

   	/**
   	 * 修改时间
   	 */
   	private Date gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTestCol() {
        return testCol;
    }

    public void setTestCol(String testCol) {
        this.testCol = testCol;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
