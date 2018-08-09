package com.mylearn.taobao.metadata.predeal;

import com.mylearn.taobao.metadata.BaseCommand;
import com.mylearn.taobao.metadata.Context;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/8
 * Time: 16:49
 * CopyRight: taobao
 * Descrption:
 */

public class PreDealCommand extends BaseCommand {
    @Override
    protected boolean process(Context context) {
        // //yingkhtodo:desc:发布事件，触发处理类handler
//        notify();
        System.out.println("PreDealCommand do process");
        return this.rule.valid(context);
    }
}
