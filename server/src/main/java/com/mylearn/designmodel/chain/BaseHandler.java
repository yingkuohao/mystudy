package com.mylearn.designmodel.chain;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-23
 * Time: ????10:12
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseHandler {

    protected BaseHandler nextHandler; //?????????handler

    public abstract void doRequest(Context context);  //????????????????t????????????

    public BaseHandler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
