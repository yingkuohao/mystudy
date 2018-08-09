package com.mylearn.designmodel.adapter.example;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????3:25
 * CopyRight:360buy
 * Descrption:  ??????????? productService??   commentService
 * To change this template use File | Settings | File Templates.
 */
public class AdapterService implements Target {

    private ProductService productService;
    private CommentService commentService;

    /**
     * ??????????????????????spring???4
     * @param productService
     * @param commentService
     */
    public AdapterService(ProductService productService, CommentService commentService) {
        this.productService = productService;
        this.commentService = commentService;
    }

    public ProductInfo getProductInfoBySku(String sku) {
        //1. ???sku???product???
        ProductService.Product product = productService.getProductById(sku);
        //2. ???sku???comment???
        CommentService.Comment comment = commentService.getCommentBySku(sku);
        //3. ????????????????
        ProductInfo productInfo = new ProductInfo();
        productInfo.setSku(sku);
        productInfo.setName(product.getName());
        productInfo.setStar(comment.getStar());
        return productInfo;  //To change body of implemented methods use File | Settings | File Templates.
    }



    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
