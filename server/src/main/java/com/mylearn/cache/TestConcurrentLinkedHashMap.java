package com.mylearn.cache;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import com.googlecode.concurrentlinkedhashmap.Weighers;
import com.mylearn.util.json.JsonUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/8/23
 * Time: 下午5:59
 * CopyRight: taobao
 * Descrption:        ConcurrentLinkedHashMap LRU算法,可以用作缓存
 * <p/>
 * 这个map很坑,虽然是linkedmap但是确是无序的,因为底层用的是ConcurrentHashMap的putIfAbsent方法, 这个put实际上还是hash.
 * 所以,是乱序,只不过他保证了LRU,通过一个队列来维护每个key的操作次数.如果操作次数 .
 * <p/>
 * 同时在put时,把key 放入了一个队列里:evictionDeque
 * 每次put完,生成一个addTask,这个TAsk去校验整体的weight是否大于capacity,如果大于,则从 evictionDeque中pool第一个元素,这里注意,并不是谁先put,谁就是第一个元素.
 * 当我们进行get操作时,会调用一个moveToBack的方法, 会把当前get的元素放到 evictionDeque的队尾.这样当前get的元素就成了热元素,不会被优先pool出去.
 */

public class TestConcurrentLinkedHashMap {

    public static void main(String[] args) {
//        test6();
//        test2();
//        test001();
//        test003();
//        test004();
//        test005();
        testCycle();
    }

    private static void test2() {
        ConcurrentLinkedHashMap<Integer, Integer> map = new ConcurrentLinkedHashMap.Builder<Integer, Integer>().maximumWeightedCapacity(2).weigher(Weighers.singleton()).build();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        System.out.println("1:" + map.get(1));     //null 已失效
        System.out.println("1:" + map.get(2));
        System.out.println("mapGet=" + JsonUtil.map2Json(map));     //3,1,2
    }


    private static void test001() {
        EvictionListener<String, String> listener = new EvictionListener<String, String>() {

            @Override
            public void onEviction(String key, String value) {     //失效监听
                System.out.println("Evicted key=" + key + ", value=" + value);
            }
        };
        ConcurrentMap<String, String> cache = new ConcurrentLinkedHashMap.Builder<String, String>()
                .maximumWeightedCapacity(10).listener(listener).build();

        for (int i = 0; i < 150; i++) {
            int j = 1024;
            j = j + i;
            cache.put(String.valueOf(j), "nihao" + i);
        }

        for (Map.Entry<String, String> entry : cache.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + "====" + value);
        }
        System.out.println(cache.get("1025"));
        cache.remove("1026");

    }

    private static void test003() {

        ConcurrentLinkedHashMap<Integer, Integer> map = new ConcurrentLinkedHashMap.Builder<Integer, Integer>().maximumWeightedCapacity(10).weigher(Weighers.singleton()).build();

        for (int i = 0; i < 10; i++) {
            map.put(i, i);
        }
        //        map.put(2, 2);
        //        map.put(3, 3);
        System.out.println("map=" + map);
        Integer pre = map.entrySet().iterator().next().getValue();
        System.out.println("pre=" + pre);
//        map.get(1);
//        System.out.println("map=" + map);
        map.put(5, 11);
        System.out.println("map=" + map);

        map.put(11, 11);
        System.out.println("map=" + map);
//        Integer pre1 = map.entrySet().iterator().next().getValue();
//        System.out.println("pre1=" + pre1);
//        Integer pre2 = map.entrySet().iterator().next().getValue();
//        System.out.println("pre2=" + pre2);

        map.get(1);            //当用get取得时候,weight会增加, 当用iterator取得时候不变
        System.out.println("map=" + map);
        map.put(12, 12);
        System.out.println("map=" + map);
    }

    private static void test004() {

        ConcurrentLinkedHashMap<String, Integer> map = new ConcurrentLinkedHashMap.Builder<String, Integer>().maximumWeightedCapacity(10).weigher(Weighers.singleton()).build();


        String uri = "/serviceA/test1";
        String ip = "127.0.0.";
        for (int i = 0; i < 10; i++) {
            String key = uri + "-" + ip + "-";
            map.put(key + i + "", i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("map=" + map);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }
        System.out.println("map=" + map.ascendingMap());

    }

    private static void test005() {

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();


        String uri = "/serviceA/test1";
        String ip = "127.0.0.";
        for (int i = 0; i < 10; i++) {
            String key = uri + "-" + ip + "-";
            map.put(key + i + "", i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("map=" + map);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key=" + entry.getKey() + ",value=" + entry.getValue());
        }

    }

    private static void test6() {
        ConcurrentLinkedHashMap<String, Integer> map = new ConcurrentLinkedHashMap.Builder<String, Integer>().maximumWeightedCapacity(2).weigher(Weighers.singleton()).build();
        map.put("b", 1);
        map.put("c", 2);
        map.get("b");
        System.out.println("mapGet=" + JsonUtil.map2Json(map));     //c,b
        map.put("a", 3);
        System.out.println("mapGet=" + JsonUtil.map2Json(map));     //b,a
        map.put("d", 4);
        System.out.println("mapGet=" + JsonUtil.map2Json(map));     //a,d
        map.put("e", 5);
        System.out.println("mapGet=" + JsonUtil.map2Json(map));     //d,e
    }

    private static void testCycle() {
        ConcurrentLinkedHashMap<String, Integer> map = new ConcurrentLinkedHashMap.Builder<String, Integer>().maximumWeightedCapacity(5).weigher(Weighers.singleton()).build();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        for (; ; ) {
            String key = map.keySet().iterator().next();
            Integer value = map.get(key);
            System.out.println("key=" + key + ",value=" + value);
        }


    }

}
