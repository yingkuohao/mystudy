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
 * Time: ����3:19
 * CopyRight: taobao
 * Descrption:
 */

public class ConnectionPoolImpl implements ConnectionPool {

    // ���ӳ���������
    private DBbean dbBean;
    private boolean isActive = false; // ���ӳػ״̬
    private int contActive = 0;// ��¼�������ܵ�������

    // ��������
    private List<Connection> freeConnection = new Vector<Connection>();
    // �����
    private List<Connection> activeConnection = new Vector<Connection>();
    // ���̺߳����Ӱ󶨣���֤������ͳһִ��
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
                //��ʼ����С������
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

    // ��õ�ǰ����
    public Connection getCurrentConnecton() {
        // Ĭ���߳�����ȡ
        Connection conn = threadLocal.get();
        if (!isValid(conn)) {
            conn = getConnection();
        }
        return conn;
    }

    // �ж������Ƿ����
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
                //�����Ծ������С�������������ֵ
                if (freeConnection.size() > 0) {
                    //����������Ӵ���0,ֱ�ӷ���һ��
                    connection = freeConnection.get(0);
                    if (connection != null) {
                        threadLocal.set(connection);
                    }
                    freeConnection.remove(0);
                } else {        //����������Ӳ�����newһ��������
                    connection = newConnection();
                }
            } else {
                //�����Ծ������������ֵ,��ȴ����ӳ�ʱ;

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
                    // 1.���߳����������״̬
                    // 2.���ӳ���С ���������
                    // 3.����״̬���м�飬��Ϊ���ﻹ��Ҫд�����̹߳�����࣬��ʱ�Ͳ������
                    System.out.println("���߳���������" + freeConnection.size());
                    System.out.println("�����������" + activeConnection.size());
                    System.out.println("�ܵ���������" + contActive);
                }
            }, dbBean.getLazyCheck(), dbBean.getPeriodCheck());
        }
    }
}
