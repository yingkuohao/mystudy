package com.mylearn.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/12
 * Time: 下午3:40
 * CopyRight: taobao
 * Descrption:
 */

public class ConnectionPoolManager {
   
    // 连接池存放  
    public Hashtable<String,ConnectionPool> pools = new Hashtable<String, ConnectionPool>();  
      
    // 初始化  
    private ConnectionPoolManager(){  
        init();  
    }

    private void init() {
        for(int i =0;i<DBInitInfo.beans.size();i++){
                    DBbean bean = DBInitInfo.beans.get(i);
                    ConnectionPool pool = new ConnectionPoolImpl(bean);
                    if(pool != null){
                        pools.put(bean.getPoolName(), pool);
                        System.out.println("Info:Init connection successed ->" +bean.getPoolName());
                    }
                }
    }

          
    // 获得连接,根据连接池名字 获得连接  
    public Connection getConnection(String poolName){  
        Connection conn = null;  
        if(pools.size()>0 && pools.containsKey(poolName)){  
            conn = getPool(poolName).getConnection();  
        }else{  
            System.out.println("Error:Can't find this connecion pool ->"+poolName);  
        }  
        return conn;  
    }  
      
    // 关闭，回收连接  
    public void close(String poolName,Connection conn){  
            ConnectionPool pool = getPool(poolName);  
            try {  
                if(pool != null){  
                    pool.releaseConn(conn);  
                }  
            } catch (SQLException e) {  
                System.out.println("连接池已经销毁");  
                e.printStackTrace();  
            }  
    }  
      
    // 清空连接池  
    public void destroy(String poolName){  
        ConnectionPool pool = getPool(poolName);  
        if(pool != null){  
            pool.destroy();  
        }  
    }  
      
    // 获得连接池  
    public ConnectionPool getPool(String poolName){  
        ConnectionPool pool = null;  
        if(pools.size() > 0){  
             pool = pools.get(poolName);  
        }  
        return pool;  
    }  

    // 单例实现  
    public static ConnectionPoolManager getInstance(){  
        return Singtonle.instance;  
    }  
    private static class Singtonle {  
        private static ConnectionPoolManager instance =  new ConnectionPoolManager();  
    } 
}
