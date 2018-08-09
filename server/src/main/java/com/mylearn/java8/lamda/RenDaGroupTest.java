package com.mylearn.java8.lamda;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/10/11
 * Time: 下午1:33
 * CopyRight: taobao
 * Descrption:          人大分组test
 */

public class RenDaGroupTest {

    public static List<Integer> initList() {

        List<Integer> list = Lists.newArrayList(
                2016102601,
                2016102604,
                2016102605,
                2016102610,
                2016102611,
                2016102612,
                2016102613,
                2016102614,
                2016102619,
                2016102620,
                2016102622,
                2016102623,
                2016102624,
                2016102635,
                2016102637,
                2016102641,
                2016102643,
                2016102645,
                2016102648,
                2016102655,
                2016102656,
                2016102657,
                2016102658,
                2016102659,
                2016102662,
                2016102669,
                2016102671,
                2016102674,
                2016102676,
                2016102678,
                2016102679,
                2016102681,
                2016102682,
                2016102683,
                2016102684,
                2016102686,
                2016102689,
                2016102690,
                2016102691,
                2016102698,
                2016102699,
                2016102700,
                2016102701,
                2016102703,
                2016102704,
                2016102705,
                2016102708,
                2016102709,
                2016102710,
                2016102712,
                2016102720,
                2016102728,
                2016102730,
                2016102732,
                2016102734,
                2016102738,
                2016102743,
                2016102744,
                2016102745,
                2016102746,
                2016102751,
                2016102753,
                2016102756,
                2016102757,
                2016102769,
                2016102776,
                2016102781,
                2016102783,
                2016102792,
                2016102838);
        return list;
    }

    public static void main(String[] args) {
//        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list = initList();
        Collections.shuffle(list);
        Map<Integer, List<Integer>> firstCityMap = groupListByQuantity(list, 7);
        Map<Integer, String> userMap = initMap();
        System.out.println(firstCityMap);
        for (Map.Entry<Integer, List<Integer>> entry : firstCityMap.entrySet()) {
            List<Integer> xuehaoList = entry.getValue();
            Function<Integer, String> function = new Function<Integer, String>() {
                @Override
                public String apply(Integer integer) {
                    return integer + "," + userMap.get(integer);
                }
            };
            List<String> resultList = Lists.transform(xuehaoList, function);
            System.out.println("第" + entry.getKey() + "组:" + resultList.toString());
        }

    }

    public static Map<Integer, List<Integer>> groupListByQuantity(List list, int quantity) {
        Map<Integer, List<Integer>> firstCityMap = new HashMap<Integer, List<Integer>>();
        if (list == null || list.size() == 0) {
            return null;
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List wrapList = new ArrayList();
        int count = 0;
        int i = 0;
        while (count < list.size()) {
            i++;
            firstCityMap.put(i, (list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity)));
            count += quantity;
        }

        return firstCityMap;
    }


    public static Map<Integer, String> initMap() {
        Map<Integer, String> map = new HashMap<Integer, String>();

        map.put(2016102601, "李雅琦 ");
        map.put(2016102604, "王腾  ");
        map.put(2016102605, "郭瑞  ");
        map.put(2016102610, "张曼  ");
        map.put(2016102611, "王昊天 ");
        map.put(2016102612, "贾雯娟 ");
        map.put(2016102613, "毕梦扬 ");
        map.put(2016102614, "贺嘉杰 ");
        map.put(2016102619, "张冉  ");
        map.put(2016102620, "马丁  ");
        map.put(2016102622, "石若川 ");
        map.put(2016102623, "王姗  ");
        map.put(2016102624, "吴瑞  ");
        map.put(2016102635, "杨帆  ");
        map.put(2016102637, "张拓  ");
        map.put(2016102641, "王陈实 ");
        map.put(2016102643, "孙越  ");
        map.put(2016102645, "贺明珠 ");
        map.put(2016102648, "吴迪  ");
        map.put(2016102655, "吴楠  ");
        map.put(2016102656, "王丽  ");
        map.put(2016102657, "李然  ");
        map.put(2016102658, "张晓凡 ");
        map.put(2016102659, "张莹  ");
        map.put(2016102662, "哈馨  ");
        map.put(2016102669, "李晓亮 ");
        map.put(2016102671, "杜佳宾 ");
        map.put(2016102674, "崔相华 ");
        map.put(2016102676, "刘姣  ");
        map.put(2016102678, "李立平 ");
        map.put(2016102679, "薛景华 ");
        map.put(2016102681, "马艳玲 ");
        map.put(2016102682, "郭湛崎 ");
        map.put(2016102683, "孙妍  ");
        map.put(2016102684, "王伟  ");
        map.put(2016102686, "杨超  ");
        map.put(2016102689, "武婧  ");
        map.put(2016102690, "池亚楠 ");
        map.put(2016102691, "赵斐  ");
        map.put(2016102698, "刘冰  ");
        map.put(2016102699, "陈婷婷 ");
        map.put(2016102700, "臧林林 ");
        map.put(2016102701, "王玉琢 ");
        map.put(2016102703, "王珊  ");
        map.put(2016102704, "应阔浩 ");
        map.put(2016102705, "白玉龙 ");
        map.put(2016102708, "李博  ");
        map.put(2016102709, "魏欣  ");
        map.put(2016102710, "王海东 ");
        map.put(2016102712, "许海宁 ");
        map.put(2016102720, "刘坤  ");
        map.put(2016102728, "张蕾  ");
        map.put(2016102730, "王莉华 ");
        map.put(2016102732, "孙维强 ");
        map.put(2016102734, "刘羽然 ");
        map.put(2016102738, "郭雁芳 ");
        map.put(2016102743, "杨超  ");
        map.put(2016102744, "冀晓杰 ");
        map.put(2016102745, "王颖  ");
        map.put(2016102746, "田力钧 ");
        map.put(2016102751, "范涛  ");
        map.put(2016102753, "任英杰 ");
        map.put(2016102756, "李磊  ");
        map.put(2016102757, "马克  ");
        map.put(2016102769, "陶国强 ");
        map.put(2016102776, "李润东 ");
        map.put(2016102781, "贺宾宇 ");
        map.put(2016102783, "于洋  ");
        map.put(2016102792, "靳豁然 ");
        map.put(2016102838, "胡敏  ");
        return map;
    }

}
