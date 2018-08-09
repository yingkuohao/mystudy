package com.mylearn.j2ee.guava;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.io.LineReader;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-31
 * Time: 下午2:04
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class GuavaExample {
    public static void main(String args[]) {

        //1. 创建集合的方式
        List<String> list = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        List<Integer> listInt = Lists.newArrayList(1, 2, 3);

        //2. 不可变对象,of 构造
        ImmutableList<Integer> listImmutableInt = ImmutableList.of(1, 2, 3);
        ImmutableMap<String, Integer> mapImmutableInt = ImmutableMap.of("1", 1, "2", 2, "3", 3);
        ImmutableSet<String> imutableSet = ImmutableSet.of("a", "b", "c");
        //2.2 排序的不可变集合
        ImmutableSortedMap<Integer, String> immutableSortedMap = ImmutableSortedMap.of(1, "1", 4, "4", 3, "3", 2, "2");
        System.out.println("immutableSortedMap=" + Joiner.on(",").withKeyValueSeparator("对应").join(immutableSortedMap));
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("4", "3", "2");
        //2.3  双向map
        ImmutableBiMap<Integer, String> immutableBiMap = ImmutableBiMap.of(1, "a", 2, "b", 3, "c");

        // 3.build模式
        ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
        for (int i = 0; i < 10; i++) {
            builder.put(i + "", i);
        }
        ImmutableMap<String, Integer> mapBuild = builder.build();

        //4.  读取流
        String fileName = "D:\\Workspace\\mylearn\\domain\\src\\main\\java\\com\\mylearn\\j2ee\\guava\\GuavaExample.java";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            List<String> lines = CharStreams.readLines(bufferedReader);
//            System.out.println("lines =" + StringUtils.join(lines, ","));

            //还可以分行读入
            LineReader lineReader = new LineReader(bufferedReader);
            for (String line = lineReader.readLine(); line != null; line = lineReader.readLine()) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        //5.joinner
        String[] strs = new String[]{"a", "b", "c", "f", "", "d", null};
        String noNa = Joiner.on(",").useForNull("NA").join(strs);//用“NA”替代null
        System.out.println("no Na=" + noNa);    //no Na=a,b,c,f,,d,NA
        String noNull = Joiner.on(",").skipNulls().join(strs);  //过滤null
        System.out.println("no Null=" + noNull); //no Null=a,b,c,f,,d
//        String str = Joiner.on(",").join(strs);
//        System.out.println("str = " + str); //null Pointer

        //分割map的key和value，把key和value连起来输出，比直接遍历输出Map方便了很多
        ImmutableMap<Integer, String> immutableMap = ImmutableMap.of(1, "zhangsan", 2, "lisi", 3, "wangwu");
        String multimapValue = Joiner.on(",").withKeyValueSeparator("是").join(immutableMap);
        System.out.println("multimap = " + multimapValue); //1是zhangsan,2是lisi,3是wangwu

        //6.Spliter  字符串切分
        String s = "a,b,, ,c,";
//        Iterable<String> strings = Splitter.on(",").split(s);   //会有空指针
        Iterable<String> strings = Splitter.on(",").omitEmptyStrings().trimResults().split(s);//过滤空串
        for (String word : strings) {
            System.out.println("word=" + word);
        }
        //按行拆分，比如进行格式化的时候打印，一行固定打印n个字符
        for (String word : Splitter.fixedLength(2).split(s)) {
            System.out.println("fixedLength,word=" + word);
        }


        //7.基本类型工具类，如Ints是int的工具类，Doubles是double的工具类
        int isBig = Longs.compare(2l, 3l);
        int intCompare = Ints.compare(2, 3);
        int[] ints = {10, 9, 30, 8, 1};
        System.out.println("max=" + Ints.max(ints));
        System.out.println("min=" + Ints.min(ints));
        List<Integer> integerList = Ints.asList(ints);//asList转换为List
        System.out.println("integerList=" + Joiner.on(",").join(integerList));
        System.out.println("joins=" + Ints.join(",", ints));  //join方法转为String
        int[] intsCopy = Ints.toArray(integerList);  //toArray转换为Array


        //判断空
        String nullValue = null;
//        nullValue = Preconditions.checkNotNull(nullValue, "空对象！");
//        System.out.println("nullValue =" + nullValue);

        //8.  List 使用：拆分，翻转，批量转换
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<List<Integer>> partitionLists = Lists.partition(lists, 3); //按照长度为3的次序拆分list
        List<Integer> listsReverse = Lists.reverse(lists);
        List<Double> transFormList = Lists.transform(lists, new Function<Integer, Double>() {
            public Double apply( Integer input) {
                return Math.pow(input, 2);
            }
        });

        System.out.println("partitionLists = " + Joiner.on(",").join(partitionLists));
        System.out.println("listsReverse = " + Joiner.on(",").join(listsReverse));
        System.out.println("transFormList = " + Joiner.on(",").join(transFormList));

        //9.集合的交集、并集、差集
        HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);

        //并集
        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Integer integer : union) {
            System.out.println(integer);
        }
        //差集
        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println("difference:" + Joiner.on(",").join(difference));
        //交集
        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:" + Joiner.on(",").join(intersection));

        //10.Map的用法，
        // 10.1 和并交叉
        Map<String, Integer> mapA = ImmutableMap.of("a", 1, "b", 2, "c", 4);
        Map<String, Integer> mapB = ImmutableMap.of("d", 1, "b", 2, "c", 3);

        MapDifference<String, Integer> differenceMap = Maps.difference(mapA, mapB);
//        for(Map.Entry entry :differenceMap.entriesDiffering().entrySet()) {   //相当于mapA和mapB中key相等，当时value不相等的集合
//        for(Map.Entry entry :differenceMap.entriesInCommon().entrySet()) {   //相当于mapA和mapB的交集，b：2
        for (Map.Entry entry : differenceMap.entriesOnlyOnRight().entrySet()) {  //相当于mapB与mapA的差集，对比key，留mapB的元素：d:1
//        for(Map.Entry entry :differenceMap.entriesOnlyOnLeft().entrySet()) {   //相当于mapA与mapB的差集，对比key，留mapA的元素：a:1
            System.out.println("entry.key=" + entry.getKey());
            System.out.println("entry.value=" + entry.getValue());
        }
        //10.2.过滤,所有value>2的键值对
        Map<String, Integer> filterMap = Maps.filterValues(mapA, new Predicate<Integer>() {
            public boolean apply( Integer input) {
                return input > 2;
            }
        });
        System.out.println("过滤值大于2的map=" + Joiner.on(",").withKeyValueSeparator("是").join(filterMap));

        //10.3 批量转换，
        //定义一个函数，把value值取平方
        Function<Integer, Double> pow = new Function<Integer, Double>() {
            public Double apply( Integer input) {
                return Math.pow(input, 2);
            }
        };
        Map<String, Double> transFormMap = Maps.transformValues(mapB, pow);
        System.out.println("map的value批量处理=" + Joiner.on(",").withKeyValueSeparator("是").join(transFormMap));


        //11. 多值Map：包含重复值的map，取代Map<String,List<String>>
        ListMultimap<Integer, String> multimap = ArrayListMultimap.create();
        multimap.put(1, "a");
        multimap.put(1, "b");
        multimap.put(2, "c");
        for (Map.Entry entry : multimap.entries()) {
            System.out.println("entry.key=" + entry.getKey());
            System.out.println("entry.value=" + entry.getValue());
        }
        System.out.println(multimap.get(1));    //[a,b]
        System.out.println(multimap.keys());    //[1 x 2, 2]
        System.out.println(multimap.keys().size());    //3
        System.out.println(multimap.values());    //[a, b, c]
        System.out.println(multimap.values().size());    //3
        System.out.println(multimap.size());    //3

        //12.table表：   两个坐标的map
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "zhangsan");
        table.put(1, 2, "lisi");
        table.put(2, 2, "wangwu");
        table.put(2, 2, "zhaoliu");
        Map<Integer, String> rowMap = table.row(1); //得到行集合
        int max = Collections.max(rowMap.keySet());
        String aTableValue = table.get(1, 1);
        System.out.println("坐标为1，1的值是：" + aTableValue);
        for (Map.Entry entry : rowMap.entrySet()) {
            System.out.println("entry.key=" + entry.getKey());
            System.out.println("entry.value=" + entry.getValue());
        }
        //13. biMap 双向map ，一一映射，可以通过key找到value，也可以通过value找到key
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "zhangsan");
        biMap.put(2, "lisi");
        biMap.put(3, "wangwu");
        biMap.put(4, "zhaoliu");
        Integer key = biMap.inverse().get("lisi");  //翻转一下
        System.out.println("lisi's key = " + key);

        //14.排序  Ordering
        List<Integer> listTest = Lists.newArrayList(10, 40, 30, 50, 60, 80);
        List sortList = Ordering.natural().sortedCopy(listTest);
        System.out.println("sortList = " + Joiner.on(",").skipNulls().join(sortList));
        List reversList = Ordering.natural().reverse().sortedCopy(listTest);
        System.out.println("reversList = " + Joiner.on(",").skipNulls().join(reversList));
        Integer minValue = Ordering.natural().min(listTest);
        Integer maxValue = Ordering.natural().max(listTest);
        System.out.println("minValue=" + minValue + ",maxValue=" + maxValue);

        Person person1 = new Person("zhang","san",1);
        Person person2 = new Person("li","si",2);
        List<Person> personList = Lists.newArrayList(person1,person2);

   /*     Maps.uniqueIndex();

        List<String> personTransFormList = Lists.transform(personList, new Function<Person,String>() {
                public String apply(@Nullable Person person) {
                    return person.getFirstName()+"="+person.getLastName() ;
                }
            });
        Joiner.on(";").withKeyValueSeparator("+").join(personTransFormList);*/
    }


}

class PersonComparator implements Comparator<Person> {
    //利用ComparisonChain来实现比较，简单方便
    public int compare(Person o1, Person o2) {
        return ComparisonChain.start().compare(o1.firstName, o2.firstName)
                .compare(o1.lastName, o2.lastName)
                .compare(o1.age, o2.age)
                .result();
    }
}


class Person {
    String firstName;
    String lastName;
    int age;

    Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}