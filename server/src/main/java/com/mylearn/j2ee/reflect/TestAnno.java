package com.mylearn.j2ee.reflect;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/5/11
 * Time: 17:44
 * CopyRight: taobao
 * Descrption:
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnno {
    String appName() default "";       //app����
    int level() default 1;       //app�ȼ�������common��Ϊ0��ҵ���Ϊ1��ҵ�������
}
