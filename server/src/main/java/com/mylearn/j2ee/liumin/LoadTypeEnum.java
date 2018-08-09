package com.mylearn.j2ee.liumin;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/8/26
 * Time: 下午2:14
 * CopyRight: taobao
 * Descrption:
 */

public enum LoadTypeEnum {
    ZK(1, "载客"),
    ZH(2, "载货"),
    ALL(99, "ALL");

    private int type;
    private String desc;

    LoadTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
