package com.mylearn.designmodel.chain;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-23
 * Time: ????10:55
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class LeaderHandler extends BaseHandler {
    @Override
    public void doRequest(Context context) {
       dosth(context);
       if(this.getNextHandler()!=null) {
           this.getNextHandler().doRequest(context);
       }
    }

    private void dosth(Context context) {
        System.out.println("leader??????");
        context.getMap().put("leader","leader??????");
    }
}
