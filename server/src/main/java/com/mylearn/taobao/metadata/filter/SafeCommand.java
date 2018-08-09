package com.mylearn.taobao.metadata.filter;

import com.mylearn.taobao.metadata.BaseCommand;
import com.mylearn.taobao.metadata.Context;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/24
 * Time: 14:07
 * CopyRight: taobao
 * Descrption:°²È«¹ýÂËÆ÷
 */

public class SafeCommand extends BaseCommand  {

    @Override
    protected boolean process(Context context) {
        System.out.println("SafeCommand do process");
        return  this.rule.valid(context);
    }


}
