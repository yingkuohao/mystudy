package com.mylearn.threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-2-19
 * Time: ????9:41
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */


public class Worker extends Thread {

    private QuerySvc querySvc;

    private String sql;

    public Worker(QuerySvc querySvc, String sql) {

        this.querySvc = querySvc;
        this.sql = sql;

    }

    public void run() {

        querySvc.setSql(sql);
        querySvc.execute();

    }

}
