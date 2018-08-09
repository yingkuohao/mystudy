package com.mylearn.taobao.metadata.filter;

import com.mylearn.taobao.metadata.BaseCommand;
import com.mylearn.taobao.metadata.Context;
import com.mylearn.taobao.metadata.Rule;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/24
 * Time: 14:07
 * CopyRight: taobao
 * Descrption:È¨ÏÞ¹ýÂËÆ÷
 */

public class AuthCommand extends BaseCommand  {

    @Override
    protected boolean process(Context context) {
        System.out.println("AuthCommand do process");
        return this.rule.valid(context);
    }


}
