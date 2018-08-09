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

public class RentalAuthRule implements Rule {
    @Override
    public boolean valid(Context context) {
        System.out.println("RentalAuthRule");
        return true;
    }
}
