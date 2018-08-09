package com.mylearn.http;

import org.springframework.util.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/8/17
 * Time: 下午3:54
 * CopyRight: taobao
 * Descrption:   环境状态类型枚举:
 */

public enum EnvEnum {
    dev(1, "dev"),
    daily(2, "daily"),
    preprare(3, "preprare"),
    product(4, "product");

    private int code;
    private String name;

    EnvEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EnvEnum getEnumById(Integer id) {
        if (id == null) {
            return null;
        }
        for (EnvEnum containerTypeEnum : EnvEnum.values()) {
            if (id.equals(containerTypeEnum.getCode())) {
                return containerTypeEnum;
            }
        }
        return null;
    }

    public static boolean isOffline(String envName) {
        if (StringUtils.isEmpty(envName)) {
            return false;
        }

        for (EnvEnum containerTypeEnum : EnvEnum.values()) {
            if (dev.getName().equals(envName) || daily.getName().equals(envName)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isProduct(String envName) {
        if (StringUtils.isEmpty(envName)) {
            return false;
        }

        for (EnvEnum containerTypeEnum : EnvEnum.values()) {
            if (product.getName().equals(envName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDev(String envName) {
        if (StringUtils.isEmpty(envName)) {
            return false;
        }

        for (EnvEnum containerTypeEnum : EnvEnum.values()) {
            if (dev.getName().equals(envName)) {
                return true;
            }
        }
        return false;
    }
}
