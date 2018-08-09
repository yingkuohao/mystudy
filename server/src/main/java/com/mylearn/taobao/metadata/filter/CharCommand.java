package com.mylearn.taobao.metadata.filter;

import com.mylearn.taobao.metadata.BaseCommand;
import com.mylearn.taobao.metadata.Context;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/24
 * Time: 14:07
 * CopyRight: taobao
 * Descrption:ÂÒÂë¹ıÂËÆ÷
 */

public class CharCommand extends BaseCommand  {

    @Override
    protected boolean process(Context context) {
        System.out.println("CharCommand do process");
        this.rule.valid(context);
        return false;
    }


}
