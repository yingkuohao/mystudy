package com.mylearn.designmodel.proxy;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-30
 * Time: ????10:49
 * CopyRight:360buy
 * Descrption: ???????
 * ???1.?????????????????????????????????????????????????????????????????????????????????
 * 2. ??????????????????????????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public interface Subject {


    /**
     * ??
     *
     * @param rating
     */
    public void setHotOrNotRaing(int rating);

    /**
     * ???????
     *
     * @return
     */
    public int getHotOrNotRating();

    public String getName();

    public void setName(String name);

    public int getRating();

    public void setRating(int rating);

    public String getIntrestes();

    public void setIntrestes(String intrestes);

    public int getRatingCount();

    public void setRatingCount(int ratingCount);
}