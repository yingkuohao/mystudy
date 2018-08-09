package com.mylearn.db;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/12
 * Time: ����3:11
 * CopyRight: taobao
 * Descrption:
 */

public class DBbean {
    private String driverName;
    private String url;
    private String userName;
    private String password;
    private String poolName;
    private int    minConnections = 1; //���г�,��С������
    private int    maxConnections = 10; //���г�,���������

    private int initConnections = 5; //��ʼ��������

    private long connTimieOut = 1000; //�ظ�������ӵ�Ƶ��

    private int maxActiveConnections = 100; //��������������

    private long connectionTimeOut = 1000 * 60 *20; //���ӳ�ʱʱ��,Ĭ��20����

    private boolean isCurrentConnection = true; //�Ƿ��õ�ǰ����,Ĭ��true

    private boolean isCheakPool = true; // �Ƿ�ʱ������ӳ�
    private long lazyCheck = 1000*60*60;// �ӳٶ���ʱ���ʼ ���
    private long periodCheck = 1000*60*60;// ���Ƶ��


    public DBbean(String driverName, String url, String userName,
            String password, String poolName) {
        super();
        this.driverName = driverName;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.poolName = poolName;
    }
    public DBbean() {
    }


    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

    public int getMinConnections() {
        return minConnections;
    }

    public void setMinConnections(int minConnections) {
        this.minConnections = minConnections;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getInitConnections() {
        return initConnections;
    }

    public void setInitConnections(int initConnections) {
        this.initConnections = initConnections;
    }

    public long getConnTimieOut() {
        return connTimieOut;
    }

    public void setConnTimieOut(long connTimieOut) {
        this.connTimieOut = connTimieOut;
    }

    public int getMaxActiveConnections() {
        return maxActiveConnections;
    }

    public void setMaxActiveConnections(int maxActiveConnections) {
        this.maxActiveConnections = maxActiveConnections;
    }

    public long getConnectionTimeOut() {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(long connectionTimeOut) {
        this.connectionTimeOut = connectionTimeOut;
    }

    public boolean isCurrentConnection() {
        return isCurrentConnection;
    }

    public void setCurrentConnection(boolean currentConnection) {
        isCurrentConnection = currentConnection;
    }

    public boolean isCheakPool() {
        return isCheakPool;
    }

    public void setCheakPool(boolean cheakPool) {
        isCheakPool = cheakPool;
    }

    public long getLazyCheck() {
        return lazyCheck;
    }

    public void setLazyCheck(long lazyCheck) {
        this.lazyCheck = lazyCheck;
    }

    public long getPeriodCheck() {
        return periodCheck;
    }

    public void setPeriodCheck(long periodCheck) {
        this.periodCheck = periodCheck;
    }
}
