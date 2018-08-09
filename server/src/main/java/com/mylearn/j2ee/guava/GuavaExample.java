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
 * Time: ����2:04
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class GuavaExample {
    public static void main(String args[]) {

        //1. �������ϵķ�ʽ
        List<String> list = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        List<Integer> listInt = Lists.newArrayList(1, 2, 3);

        //2. ���ɱ����,of ����
        ImmutableList<Integer> listImmutableInt = ImmutableList.of(1, 2, 3);
        ImmutableMap<String, Integer> mapImmutableInt = ImmutableMap.of("1", 1, "2", 2, "3", 3);
        ImmutableSet<String> imutableSet = ImmutableSet.of("a", "b", "c");
        //2.2 ����Ĳ��ɱ伯��
        ImmutableSortedMap<Integer, String> immutableSortedMap = ImmutableSortedMap.of(1, "1", 4, "4", 3, "3", 2, "2");
        System.out.println("immutableSortedMap=" + Joiner.on(",").withKeyValueSeparator("��Ӧ").join(immutableSortedMap));
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("4", "3", "2");
        //2.3  ˫��map
        ImmutableBiMap<Integer, String> immutableBiMap = ImmutableBiMap.of(1, "a", 2, "b", 3, "c");

        // 3.buildģʽ
        ImmutableMap.Builder<String, Integer> builder = ImmutableMap.builder();
        for (int i = 0; i < 10; i++) {
            builder.put(i + "", i);
        }
        ImmutableMap<String, Integer> mapBuild = builder.build();

        //4.  ��ȡ��
        String fileName = "D:\\Workspace\\mylearn\\domain\\src\\main\\java\\com\\mylearn\\j2ee\\guava\\GuavaExample.java";
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            List<String> lines = CharStreams.readLines(bufferedReader);
//            System.out.println("lines =" + StringUtils.join(lines, ","));

            //�����Է��ж���
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
        String noNa = Joiner.on(",").useForNull("NA").join(strs);//�á�NA�����null
        System.out.println("no Na=" + noNa);    //no Na=a,b,c,f,,d,NA
        String noNull = Joiner.on(",").skipNulls().join(strs);  //����null
        System.out.println("no Null=" + noNull); //no Null=a,b,c,f,,d
//        String str = Joiner.on(",").join(strs);
//        System.out.println("str = " + str); //null Pointer

        //�ָ�map��key��value����key��value�������������ֱ�ӱ������Map�����˺ܶ�
        ImmutableMap<Integer, String> immutableMap = ImmutableMap.of(1, "zhangsan", 2, "lisi", 3, "wangwu");
        String multimapValue = Joiner.on(",").withKeyValueSeparator("��").join(immutableMap);
        System.out.println("multimap = " + multimapValue); //1��zhangsan,2��lisi,3��wangwu

        //6.Spliter  �ַ����з�
        String s = "a,b,, ,c,";
//        Iterable<String> strings = Splitter.on(",").split(s);   //���п�ָ��
        Iterable<String> strings = Splitter.on(",").omitEmptyStrings().trimResults().split(s);//���˿մ�
        for (String word : strings) {
            System.out.println("word=" + word);
        }
        //���в�֣�������и�ʽ����ʱ���ӡ��һ�й̶���ӡn���ַ�
        for (String word : Splitter.fixedLength(2).split(s)) {
            System.out.println("fixedLength,word=" + word);
        }


        //7.�������͹����࣬��Ints��int�Ĺ����࣬Doubles��double�Ĺ�����
        int isBig = Longs.compare(2l, 3l);
        int intCompare = Ints.compare(2, 3);
        int[] ints = {10, 9, 30, 8, 1};
        System.out.println("max=" + Ints.max(ints));
        System.out.println("min=" + Ints.min(ints));
        List<Integer> integerList = Ints.asList(ints);//asListת��ΪList
        System.out.println("integerList=" + Joiner.on(",").join(integerList));
        System.out.println("joins=" + Ints.join(",", ints));  //join����תΪString
        int[] intsCopy = Ints.toArray(integerList);  //toArrayת��ΪArray


        //�жϿ�
        String nullValue = null;
//        nullValue = Preconditions.checkNotNull(nullValue, "�ն���");
//        System.out.println("nullValue =" + nullValue);

        //8.  List ʹ�ã���֣���ת������ת��
        List<Integer> lists = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<List<Integer>> partitionLists = Lists.partition(lists, 3); //���ճ���Ϊ3�Ĵ�����list
        List<Integer> listsReverse = Lists.reverse(lists);
        List<Double> transFormList = Lists.transform(lists, new Function<Integer, Double>() {
            public Double apply( Integer input) {
                return Math.pow(input, 2);
            }
        });

        System.out.println("partitionLists = " + Joiner.on(",").join(partitionLists));
        System.out.println("listsReverse = " + Joiner.on(",").join(listsReverse));
        System.out.println("transFormList = " + Joiner.on(",").join(transFormList));

        //9.���ϵĽ������������
        HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);

        //����
        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Integer integer : union) {
            System.out.println(integer);
        }
        //�
        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println("difference:" + Joiner.on(",").join(difference));
        //����
        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:" + Joiner.on(",").join(intersection));

        //10.Map���÷���
        // 10.1 �Ͳ�����
        Map<String, Integer> mapA = ImmutableMap.of("a", 1, "b", 2, "c", 4);
        Map<String, Integer> mapB = ImmutableMap.of("d", 1, "b", 2, "c", 3);

        MapDifference<String, Integer> differenceMap = Maps.difference(mapA, mapB);
//        for(Map.Entry entry :differenceMap.entriesDiffering().entrySet()) {   //�൱��mapA��mapB��key��ȣ���ʱvalue����ȵļ���
//        for(Map.Entry entry :differenceMap.entriesInCommon().entrySet()) {   //�൱��mapA��mapB�Ľ�����b��2
        for (Map.Entry entry : differenceMap.entriesOnlyOnRight().entrySet()) {  //�൱��mapB��mapA�Ĳ���Ա�key����mapB��Ԫ�أ�d:1
//        for(Map.Entry entry :differenceMap.entriesOnlyOnLeft().entrySet()) {   //�൱��mapA��mapB�Ĳ���Ա�key����mapA��Ԫ�أ�a:1
            System.out.println("entry.key=" + entry.getKey());
            System.out.println("entry.value=" + entry.getValue());
        }
        //10.2.����,����value>2�ļ�ֵ��
        Map<String, Integer> filterMap = Maps.filterValues(mapA, new Predicate<Integer>() {
            public boolean apply( Integer input) {
                return input > 2;
            }
        });
        System.out.println("����ֵ����2��map=" + Joiner.on(",").withKeyValueSeparator("��").join(filterMap));

        //10.3 ����ת����
        //����һ����������valueֵȡƽ��
        Function<Integer, Double> pow = new Function<Integer, Double>() {
            public Double apply( Integer input) {
                return Math.pow(input, 2);
            }
        };
        Map<String, Double> transFormMap = Maps.transformValues(mapB, pow);
        System.out.println("map��value��������=" + Joiner.on(",").withKeyValueSeparator("��").join(transFormMap));


        //11. ��ֵMap�������ظ�ֵ��map��ȡ��Map<String,List<String>>
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

        //12.table��   ���������map
        Table<Integer, Integer, String> table = HashBasedTable.create();
        table.put(1, 1, "zhangsan");
        table.put(1, 2, "lisi");
        table.put(2, 2, "wangwu");
        table.put(2, 2, "zhaoliu");
        Map<Integer, String> rowMap = table.row(1); //�õ��м���
        int max = Collections.max(rowMap.keySet());
        String aTableValue = table.get(1, 1);
        System.out.println("����Ϊ1��1��ֵ�ǣ�" + aTableValue);
        for (Map.Entry entry : rowMap.entrySet()) {
            System.out.println("entry.key=" + entry.getKey());
            System.out.println("entry.value=" + entry.getValue());
        }
        //13. biMap ˫��map ��һһӳ�䣬����ͨ��key�ҵ�value��Ҳ����ͨ��value�ҵ�key
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "zhangsan");
        biMap.put(2, "lisi");
        biMap.put(3, "wangwu");
        biMap.put(4, "zhaoliu");
        Integer key = biMap.inverse().get("lisi");  //��תһ��
        System.out.println("lisi's key = " + key);

        //14.����  Ordering
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
    //����ComparisonChain��ʵ�ֱȽϣ��򵥷���
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