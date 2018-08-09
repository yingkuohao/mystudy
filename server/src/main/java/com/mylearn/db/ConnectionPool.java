package com.mylearn.db;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/12
 * Time: ����3:18
 * CopyRight: taobao
 * Descrption:
 */

public interface ConnectionPool {
    // �������
     public Connection getConnection();
     // ��õ�ǰ����
     public Connection getCurrentConnecton();
     // ��������
     public void releaseConn(Connection conn) throws SQLException;
     // �������
     public void destroy();
     // ���ӳ��ǻ״̬
     public boolean isActive();
     // ��ʱ����������ӳ�
     public void cheackPool();
}
