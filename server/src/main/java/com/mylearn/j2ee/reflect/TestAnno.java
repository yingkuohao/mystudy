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
    String appName() default "";       //app描述
    int level() default 1;       //app等级，比如common的为0，业务的为1，业务的优先
}
