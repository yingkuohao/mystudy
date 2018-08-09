package com.mylearn.designmodel.adapter.example;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????3:17
 * CopyRight:360buy
 * Descrption: ????????????????????§»???
 * To change this template use File | Settings | File Templates.
 */
public class ProductService {


    /**
     * ???sku???????????
     *
     * @param sku
     * @return
     */
    public Product getProductById(String sku) {
        Product product = new Product();
        product.setSku(sku);
        product.setName(sku + "name");
        product.setPrice(String.valueOf(Math.random()));
        product.setColor("yellow");
        return product;
    }

    /**
     * ???????????????????????????????????
     */
    class Product {
        private String sku;
        private String name;
        private String price;
        private String color;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

}
