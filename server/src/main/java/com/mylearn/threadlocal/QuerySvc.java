package com.mylearn.threadlocal;

/**
 * Created by IntelliJ IDEA.
 * User: yingkh
 * Date: 13-2-18
 * Time: ????5:43
 * CopyRight:360buy
 * Descrption:  ?????????????????????ThreadLocal???????QuerySvc?��???????????sql??ThreadLocal????
 * ????????? QuerySvc?????????????????????????????��????sql??????????????execute?????????sql?????
 * ???????set??????��?????????????????web????��?????????��??????????????servlet????????
 * ???servlet???????????????????????????????????????????????????????????
 * To change this template use File | Settings | File Templates.
 */
public class QuerySvc {

    private String sql;

    private static ThreadLocal sqlHodler = new ThreadLocal();

    public void execute() {

        System.out.println("Thread " + Thread.currentThread().getId() + "sql is " + sql);
        System.out.println("Thread " + Thread.currentThread().getId() + "Thead Local variable Sql is " + sqlHodler.get());

    }

    public String getSql() {

        return sql;
    }


    public void setSql(String sql) {
        this.sql = sql;
        sqlHodler.set(sql);
    }


//    ????????QuerySvc????????????????4????QuerySvc??set??execute????????????????sql???????????��????????sql?????????????execute?????set?????????,
//    ?? web????��???????????????????????????????????????????????????????????ThreadLocal?��???????set?????????????????????ThreadLocal?????????????
//    ?????????????????????????????????????????????��???????????????????ThreadLocal4????????

}
