package com.mylearn.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/3/10
 * Time: обнГ1:41
 * CopyRight: taobao
 * Descrption:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private int age;
    private int sex;
}
