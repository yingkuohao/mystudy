package com.mylearn.designmodel.adapter.example;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-2-10
 * Time: ????3:20
 * CopyRight:360buy
 * Descrption: ????????????sku???????????????????????(sku???????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class CommentService {

    /**
     * ???sku??????????
     *
     * @param sku
     * @return
     */
    public Comment getCommentBySku(String sku) {
        Comment comment = new Comment();
        comment.setSku(sku);
        comment.setStar(String.valueOf(Math.random()));
        comment.setComment("?????????????");
        comment.setGood("???<?n??");
        comment.setWeak("?????¦Ä???????");
        return comment;
    }


    /**
     * ???????
     */
    class Comment {
        private String sku;
        private String star;
        private String comment;
        private String good;
        private String weak;

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getGood() {
            return good;
        }

        public void setGood(String good) {
            this.good = good;
        }

        public String getWeak() {
            return weak;
        }

        public void setWeak(String weak) {
            this.weak = weak;
        }
    }
}
