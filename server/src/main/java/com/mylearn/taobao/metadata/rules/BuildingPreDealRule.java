package com.mylearn.taobao.metadata.rules;

import com.mylearn.taobao.metadata.Context;
import com.mylearn.taobao.metadata.Rule;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/8
 * Time: 17:20
 * CopyRight: taobao
 * Descrption:
 */

public class BuildingPreDealRule implements Rule {
    @Override
    public boolean valid(Context context) {
        System.out.println("BuildingPreDealRule");
        //publish a event.触发handler
        context.put("BuildingPreDealRule","true");
        //如果handler全返回成功，则返回true，
        return true;
    }
}
