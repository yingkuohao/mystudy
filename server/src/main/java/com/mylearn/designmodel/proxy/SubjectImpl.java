package com.mylearn.designmodel.proxy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-30
 * Time: ????10:49
 * CopyRight:360buy
 * Descrption: ???????
 * ???1.?????????????????????????????????????????????????????????????????????????????????
 *  2. ??????????????????????????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class SubjectImpl implements Subject {

    private String name;             //???
    private String intrestes; //???
    private int rating;
    private int ratingCount;


    /**
     * ??
     * @param rating
     */
    public void setHotOrNotRaing(int rating) {
        this.rating += rating;
        ratingCount++;
    }

    /**
     * ???????
     * @return
     */
    public int getHotOrNotRating() {
        if (ratingCount == 0) return 0;
        return (rating / ratingCount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getIntrestes() {
        return intrestes;
    }

    public void setIntrestes(String intrestes) {
        this.intrestes = intrestes;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
