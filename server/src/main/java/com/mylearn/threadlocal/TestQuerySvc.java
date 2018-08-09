package com.mylearn.threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-2-19
 * Time: ????9:42
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class TestQuerySvc {

    public static void main(String args[]) {

        QuerySvc querySvc = new QuerySvc();

        for (int i = 0; i < 10; i++) {
            String sql = "select * from table where id = " + i;
            new Worker(querySvc, sql).start();
        }

    }
}
