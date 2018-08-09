package com.mylearn.designmodel.state;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-9-29
 * Time: ????3:13
 * CopyRight:360buy
 * Descrption:
 * ????????????baseState????state????????
 * To change this template use File | Settings | File Templates.
 */
public class Context {

    private BaseState baseState;   //?????????

    private Integer hour;

    /**
     * ?????????????????????????????4????
     */
    public void doRequest() {
        this.baseState.handle(this);
    }

    public BaseState getBaseState() {
        return baseState;
    }

    public void setBaseState(BaseState baseState) {
        this.baseState = baseState;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }
}
