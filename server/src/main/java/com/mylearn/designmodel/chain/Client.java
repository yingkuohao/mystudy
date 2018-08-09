package com.mylearn.designmodel.chain;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 14-1-23
 * Time: ????3:44
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class Client {

    public static void main(String args[]) {
        BaseHandler leaderHandler =new LeaderHandler();
        BaseHandler managerHandler =new ManagerHandler();
        BaseHandler directorHandler =new DirectorHandler();

        leaderHandler.setNextHandler(managerHandler);
        managerHandler.setNextHandler(directorHandler);

        Context context=new Context();
        leaderHandler.doRequest(context);
        System.out.println(context.getMap().toString());
    }

}
