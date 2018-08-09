package com.mylearn.j2ee.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/28
 * Time: 17:19
 * CopyRight: taobao
 * Descrption:
 */

public class FunctionTest {
    public static void main(String args[]) {
        //1.map transformValues
        Map<String, Integer> map = ImmutableMap.of("a", 4, "b", 9);
        Function<Integer, Double> sqrt = new Function<Integer, Double>() {
            @Override
            public Double apply(Integer input) {
                return Math.sqrt(input);
            }
        };
        Map<String, Double> transFormMap = Maps.transformValues(map, sqrt);
        System.out.println("transFormMap" + transFormMap);
        // transformValues
        Map<String, Boolean> options = ImmutableMap.of("verbose", true, "sort", false);
        Maps.EntryTransformer<String, Boolean, String> flagPrefixer = new Maps.EntryTransformer<String, Boolean, String>() {
            @Override
            public String transformEntry(String key, Boolean value) {
                return value ? key : "no" + key;
            }
        };
        Map<String, String> transFormed = Maps.transformEntries(options, flagPrefixer);
        System.out.println("transFormed:" + transFormed);

        //3. list .transform
        Function<String, String> listFunction = new Function<String, String>() {
            @Override
            public String apply(String input) {
                return input.toUpperCase();
            }
        };
        List<String> list = ImmutableList.of("ali", "taobao", "tianmao", "juhuasuan");
        List<String> transFormList = Lists.transform(list, listFunction);
        System.out.println("transFormList=" + transFormList);

    }
}
