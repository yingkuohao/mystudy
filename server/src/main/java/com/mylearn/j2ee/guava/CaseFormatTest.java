package com.mylearn.j2ee.guava;

import com.google.common.base.CaseFormat;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/9
 * Time: 9:45
 * CopyRight: taobao
 * Descrption:һ��ʵ�ù����࣬�ṩ��ͬ��ASCII �ַ���ʽ֮���ת��
 */

public class CaseFormatTest {

    public static void main(String args[]) {
        String target = "fang-Building-log";
        //�л��ߵ��ַ���ת�����շ壬����ĸСд
        System.out.println("LOWER_HYPHEN to lower-camel:" + CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, target));      //fangBuildingLog
        //�»��ߵ��ַ���ת����  �շ壬����ĸСд
        System.out.println("LOWER_UNDERSCORE to lower-camel:" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "fang_building_log")); //fangBuildingLog
        // �»��ߵ��ַ���ת����  �շ壬����ĸ��д
        System.out.println("LOWER_UNDERSCORE to UPPER_CAMEL:" + CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "fang_building_log"));  // FangBuildingLog
        System.out.println("LOWER_UNDERSCORE to UPPER_CAMEL:" + CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "fangBuildingLog"));  // FangBuildingLog

    }

}
