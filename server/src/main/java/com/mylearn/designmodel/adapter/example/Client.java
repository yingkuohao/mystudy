package com.mylearn.designmodel.adapter.example;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????3:28
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {
    public static void main(String args[]) {
        String sku = "1";
        ProductService productService = new ProductService();
        CommentService commentService = new CommentService();
        Target target = new AdapterService(productService, commentService);
        ProductInfo productInfo = target.getProductInfoBySku(sku);
        System.out.println(productInfo.toString());

    }
}
