package com.mylearn.j2ee.guava;

import com.google.common.base.CaseFormat;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/9
 * Time: 9:45
 * CopyRight: taobao
 * Descrption:一种实用工具类，提供不同的ASCII 字符格式之间的转换
 */

public class CaseFormatTest {

    public static void main(String args[]) {
        String target = "fang-Building-log";
        //中划线的字符串转换成驼峰，首字母小写
        System.out.println("LOWER_HYPHEN to lower-camel:" + CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, target));      //fangBuildingLog
        //下划线的字符串转换成  驼峰，首字母小写
        System.out.println("LOWER_UNDERSCORE to lower-camel:" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "fang_building_log")); //fangBuildingLog
        // 下划线的字符串转换成  驼峰，首字母大写
        System.out.println("LOWER_UNDERSCORE to UPPER_CAMEL:" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "fang_building_log"));  // FangBuildingLog
        System.out.println("LOWER_UNDERSCORE to UPPER_CAMEL:" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "fangBuildingLog"));  // FangBuildingLog

    }

}
