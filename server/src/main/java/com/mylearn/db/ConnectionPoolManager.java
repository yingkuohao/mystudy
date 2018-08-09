package com.mylearn.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/12
 * Time: ����3:40
 * CopyRight: taobao
 * Descrption:
 */

public class ConnectionPoolManager {
   
    // ���ӳش��  
    public Hashtable<String,ConnectionPool> pools = new Hashtable<String, ConnectionPool>();  
      
    // ��ʼ��  
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

          
    // �������,�������ӳ����� �������  
    public Connection getConnection(String poolName){  
        Connection conn = null;  
        if(pools.size()>0 && pools.containsKey(poolName)){  
            conn = getPool(poolName).getConnection();  
        }else{  
            System.out.println("Error:Can't find this connecion pool ->"+poolName);  
        }  
        return conn;  
    }  
      
    // �رգ���������  
    public void close(String poolName,Connection conn){  
            ConnectionPool pool = getPool(poolName);  
            try {  
                if(pool != null){  
                    pool.releaseConn(conn);  
                }  
            } catch (SQLException e) {  
                System.out.println("���ӳ��Ѿ�����");  
                e.printStackTrace();  
            }  
    }  
      
    // ������ӳ�  
    public void destroy(String poolName){  
        ConnectionPool pool = getPool(poolName);  
        if(pool != null){  
            pool.destroy();  
        }  
    }  
      
    // ������ӳ�  
    public ConnectionPool getPool(String poolName){  
        ConnectionPool pool = null;  
        if(pools.size() > 0){  
             pool = pools.get(poolName);  
        }  
        return pool;  
    }  

    // ����ʵ��  
    public static ConnectionPoolManager getInstance(){  
        return Singtonle.instance;  
    }  
    private static class Singtonle {  
        private static ConnectionPoolManager instance =  new ConnectionPoolManager();  
    } 
}
