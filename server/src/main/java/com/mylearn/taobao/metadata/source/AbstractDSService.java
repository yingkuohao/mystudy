package com.mylearn.taobao.metadata.source;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/23
 * Time: 17:16
 * CopyRight: taobao
 * Descrption:��������߼���ģ�巽����
 * 1.�õ�����
 * 2. ����Ӱ�ӱ�
 *
 * ����ԴҪ�����ã�ͨ��map������
 */

public class AbstractDSService {


    Map<Integer, DataSourceService> dataSourceMap = new HashMap<Integer, DataSourceService>();

    //����������Դѡ��ͬ��ִ����������
    public void preferSource(Integer dataSource) {
        //yingkhtodo:desc:�Ƿ������meta��
        if (dataSource != null && dataSourceMap.containsKey(dataSource)) {
            DataSourceService dataSourceService = dataSourceMap.get(dataSource);
            //yingkhtodo:desc:����������
            //1.��ȡ����
            JSONObject jsonObject= dataSourceService.getData();
            //2.����Ӱ�ӱ�
            dataSourceService.insertToSnapTable(jsonObject);
        }
    }


}
