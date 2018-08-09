package com.mylearn.spring.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: обнГ5:39
 * CopyRight: taobao
 * Descrption:
 */
@Configuration
@Import(DemoService.class)
public class DemoConfig {
}
