package com.ying.spring.aop.cglib;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/9
 * Time: 下午3:48
 * CopyRight: taobao
 * Descrption:
 */

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestAnnot {
    boolean required() default true;
}
