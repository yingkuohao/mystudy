package com.mylearn.j2ee.liumin;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/8/26
 * Time: 下午2:14
 * CopyRight: taobao
 * Descrption:
 */

public enum ConfirmType {
    YES(1, "是"),
    NO(0, "否"),;

    private int type;
    private String desc;

    ConfirmType(int type, String desc) {
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
