package com.mylearn.cache;

import com.mylearn.util.json.JsonUtil;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/8/24
 * Time: 上午9:46
 * CopyRight: taobao
 * Descrption:
 * LinkedHashMap 几乎和HashMap一样,不同的是它定义了一个Entry<K,V> header, 这个header不是放在Table里,它是额外独立出来的.
 * LinkedHashMap通过继承HashMap中的Entry<K,V> 并添加两个属性Entry<K,V> before,after 和header结合起来组成一个双向链表,
 * 来实现按插入顺序或访问顺序排序.
 * LinkedHashMap定义了排序模式accessOrder,该属性为true时用于访问顺序,为false时,用于插入顺序.不指定时为插入顺序.
 * linkedHashMap重写了父类HashMap的get方法,实际子啊调用父类getEntry方法取得查找元素后,再判断当排序模式accessOrder为true时,记录访问顺序,
 * 将最新访问的元素添加到双向链表的表头,并从原来的位置删除.由于链表的增加\删除都是常量级,故不会带来性能的损失.
 */

public class TestLinkedHashMap {
    public static void main(String[] args) {
//        test01();
//        test02();
//        test03();

        testCycle();
    }

    private static void test01() {
        //当默认的顺序构造map时,按插入顺序固定
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        System.out.println("map=" + JsonUtil.map2Json(map));  //1,2,3
        map.put(4, 4);
        System.out.println("map=" + JsonUtil.map2Json(map));   //1,2,3,4
        map.put(1, 7);
        System.out.println("map=" + JsonUtil.map2Json(map));       //   key1已经存在不会影响顺序
        map.put(2, 8);
        System.out.println("map=" + JsonUtil.map2Json(map));   //   key2已经存在不会影响顺序
        map.get(1);
        System.out.println("map=" + JsonUtil.map2Json(map));   //   get不会影响顺序

        //new一个读map, 通过传入第三个参数为true,设定为顺序读模式 ,当为读模式时,按照最后插入或读的顺序,LRU
        LinkedHashMap<Integer, Integer> mapGet = new LinkedHashMap<Integer, Integer>(16, 0.75f, true);
        mapGet.put(1, 1);
        mapGet.put(2, 2);
        mapGet.put(3, 3);
        System.out.println("mapGet=" + JsonUtil.map2Json(mapGet));         //1,2,3
        mapGet.put(1, 7);
        System.out.println("mapGet=" + JsonUtil.map2Json(mapGet));       //  2,3,1
        mapGet.put(2, 8);
        System.out.println("mapGet=" + JsonUtil.map2Json(mapGet));   //3,1,2

        mapGet.get(1);
        mapGet.get(1);
        System.out.println("mapGet=" + JsonUtil.map2Json(mapGet));     //3,2,1
        mapGet.get(2);
        System.out.println("mapGet=" + JsonUtil.map2Json(mapGet));     //3,1,2


        for (Map.Entry<Integer, Integer> entry : mapGet.entrySet()) {
            System.out.println("key=" + entry.getKey() + ",");
        }
    }

    private static void test02() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<String, Integer>();
        map.put("c", 1);
        map.put("b", 2);
        map.put("a", 3);
        System.out.println("map=" + JsonUtil.map2Json(map));  //1,2,3
    }

    private static void test03() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();


        map.put("aa", 5);
        map.put("c", 1);
        map.put("b", 2);
        map.put("d", 4);
        map.put("a", 3);
        System.out.println("map=" + JsonUtil.map2Json(map));  //1,2,3
    }

    private static void testCycle() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        map.put("a", 1);
          map.put("b", 2);
          map.put("c", 3);
          map.put("d", 4);
          map.put("e", 5);
            Iterator<String> iterator = map.keySet().iterator();
        for (; ; ) {
            String key = iterator.next();
            Integer value = map.get(key);
            System.out.println("key=" + key + ",value=" + value);
        }

    }
}
