package com.mylearn.taobao.metadata.source;


import net.sf.json.JSONObject;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/23
 * Time: 17:15
 * CopyRight: taobao
 * Descrption:
 */

public interface DataSourceService {


    /**
     *
     * @return
     */
    public JSONObject getData();

    /**
     * 插入数据到影子表
     *
     * @param jsonObject
     * @return
     */
    public boolean insertToSnapTable(JSONObject jsonObject);


}
