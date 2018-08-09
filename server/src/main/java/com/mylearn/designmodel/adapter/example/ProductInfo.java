package com.mylearn.designmodel.adapter.example;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????3:24
 * CopyRight:360buy
 * Descrption: ?????????
 * To change this template use File | Settings | File Templates.
 */
public class ProductInfo {
    private String sku;
    private String name;
    private String star;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", star='" + star + '\'' +
                '}';
    }
}
