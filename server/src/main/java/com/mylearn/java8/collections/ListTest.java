package com.mylearn.java8.collections;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/11/13
 * Time: 上午10:16
 * CopyRight: taobao
 * Descrption:
 */

public class ListTest {
    public static void main(String[] args) {
        ListTest listTest = new ListTest();
        listTest.testSublist();
 /*
        List<Student> students = listTest.testCompare();
        List<Integer> finalL1Cache = Lists.newArrayList(40, 50);
        System.out.println("before compare:" + students.toString());
        //增加一个比较器,如果students中包含有cache中的id,则把这几个student对象前置,排序靠前
        List<Student> studentsFinal = students.stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                boolean bool1 = finalL1Cache.contains(o1.getAge());
                boolean bool2 = finalL1Cache.contains(o2.getAge());
                System.out.println("o1=" + o1.toString());
                System.out.println("o2=" + o2.toString());
                System.out.println("bool1=" + bool1 + ",bool2=" + bool2);
                return bool1 ? -1 : bool2 ? 1 : 0;
            }
        }).distinct().collect(Collectors.toList());

        System.out.println("after compare:" + studentsFinal.toString());*/

        List<Student> students = listTest.testCompare();

        Map<Integer, String> map = new HashMap<Integer, String>();
        map = students.stream().collect(Collectors.toMap(Student::getAge, Student::getName));
    }

    private void testSublist() {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> list2 = list1.subList(0, 5);
        list1.subList(0, 5).clear();
        List<Integer> tmp = new ArrayList<>();
        tmp.addAll(list2);
        List<Integer> tmp2 = new ArrayList<>();
        tmp2.addAll(list1);
    }

    private List<Student> testCompare() {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("zhangsan", 20));
        list.add(new Student("zhangsan1", 30));
        list.add(new Student("zhangsan2", 40));
        list.add(new Student("zhangsan3", 50));
        list.add(new Student("zhangsa4", 60));
        list.add(new Student("zhangsan5", 70));
        return list;

    }

    class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
        }
    }
}
