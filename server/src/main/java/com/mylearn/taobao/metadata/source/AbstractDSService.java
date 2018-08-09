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
 * Descrption:定义抽象逻辑，模板方法：
 * 1.拿到数据
 * 2. 插入影子表
 *
 * 数据源要可配置，通过map来管理
 */

public class AbstractDSService {


    Map<Integer, DataSourceService> dataSourceMap = new HashMap<Integer, DataSourceService>();

    //根据数据来源选择不同的执行器来处理
    public void preferSource(Integer dataSource) {
        //yingkhtodo:desc:是否可以走meta？
        if (dataSource != null && dataSourceMap.containsKey(dataSource)) {
            DataSourceService dataSourceService = dataSourceMap.get(dataSource);
            //yingkhtodo:desc:批量，多条
            //1.获取数据
            JSONObject jsonObject= dataSourceService.getData();
            //2.插入影子表
            dataSourceService.insertToSnapTable(jsonObject);
        }
    }


}
