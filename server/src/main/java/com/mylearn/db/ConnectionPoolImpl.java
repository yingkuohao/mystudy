package com.mylearn.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/12
 * Time: 下午3:19
 * CopyRight: taobao
 * Descrption:
 */

public class ConnectionPoolImpl implements ConnectionPool {

    // 连接池配置属性
    private DBbean dbBean;
    private boolean isActive = false; // 连接池活动状态
    private int contActive = 0;// 记录创建的总的连接数

    // 空闲连接
    private List<Connection> freeConnection = new Vector<Connection>();
    // 活动连接
    private List<Connection> activeConnection = new Vector<Connection>();
    // 将线程和连接绑定，保证事务能统一执行
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();


    public ConnectionPoolImpl(DBbean dbBean) {
        super();
        this.dbBean = dbBean;
        init();
        cheackPool();
    }

    private void init() {
        try {
            Class.forName(dbBean.getDriverName());
            for (int i = 0; i < dbBean.getInitConnections(); i++) {
                //初始化最小连接数
                Connection conn = newConnection();
                if (conn != null) {
                    freeConnection.add(conn);
                    contActive++;
                }
            }

            isActive = true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Connection newConnection() {
        Connection conn = null;
        if (dbBean != null) {
            try {
                Class.forName(dbBean.getDriverName());
                conn = DriverManager.getConnection(dbBean.getUrl(),
                        dbBean.getUserName(), dbBean.getPassword());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return conn;
    }

    // 获得当前连接
    public Connection getCurrentConnecton() {
        // 默认线程里面取
        Connection conn = threadLocal.get();
        if (!isValid(conn)) {
            conn = getConnection();
        }
        return conn;
    }

    // 判断连接是否可用
    private boolean isValid(Connection conn) {
        try {
            if (conn == null || conn.isClosed()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            if (contActive < this.dbBean.getMaxActiveConnections()) {
                //如果活跃连接数小于最大连接数阈值
                if (freeConnection.size() > 0) {
                    //如果空闲连接大于0,直接返回一个
                    connection = freeConnection.get(0);
                    if (connection != null) {
                        threadLocal.set(connection);
                    }
                    freeConnection.remove(0);
                } else {        //如果空闲连接不够则new一个新连接
                    connection = newConnection();
                }
            } else {
                //如果活跃连接数打满阈值,则等待连接超时;

                wait(this.dbBean.getConnectionTimeOut());

                connection = newConnection();
            }

            if (isValid(connection)) {
                activeConnection.add(connection);
                contActive++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }


    @Override
    public void releaseConn(Connection conn) throws SQLException {
        if (isValid(conn) && !(freeConnection.size() > dbBean.getMaxActiveConnections())) {
            freeConnection.add(conn);
            activeConnection.remove(conn);
            contActive--;
            threadLocal.remove();
            notifyAll();
        }
    }

    @Override
    public void destroy() {
        for (Connection conn : freeConnection) {
            try {
                if (isValid(conn)) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Connection conn : activeConnection) {
            try {
                if (isValid(conn)) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        isActive = false;
        contActive = 0;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void cheackPool() {
        if (dbBean.isCheakPool()) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    // 1.对线程里面的连接状态
                    // 2.连接池最小 最大连接数
                    // 3.其他状态进行检查，因为这里还需要写几个线程管理的类，暂时就不添加了
                    System.out.println("空线池连接数：" + freeConnection.size());
                    System.out.println("活动连接数：：" + activeConnection.size());
                    System.out.println("总的连接数：" + contActive);
                }
            }, dbBean.getLazyCheck(), dbBean.getPeriodCheck());
        }
    }
}
