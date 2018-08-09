package com.mylearn.j2ee.guava;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.apache.commons.collections.ComparatorUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/2
 * Time: 16:11
 * CopyRight: taobao
 * Descrption:
 * 当两个对象比较时，Java提供了compare和compareTo，我们需要实现一个比较器（Comparator）或
 * 直接实现Comparable接口，不过当对象属性过多的时候，我们需要写大量的if-else代码，代码不够
 * 优雅，Guava为我们简化了这一点，我们可以使用ComparisonChain类来优雅的实现对象之间的比较。
 * 查看ComparisonChain源码，可以发现，它是一个抽象类，主要提供了三个抽象方法，start()用于返回内部的一个ComparisonChain
 * 实现；重载了很多compare()方法，用于接收各种类型的参数，compare()方法返回的仍然是ComparisonChain对象；
 * result()方法返回用于比较后的结果。
 *
 * 这种链式的编码形式正是Builder建造者模式的应用。
 */

public class ComparisonChainTest {
    public static void main(String args[]) {
        //Girl实现了comparable接口，自定义了compareTo方法
        Girl girl1 = new Girl(10, "lily", "beautiful");
        Girl girl2 = new Girl(10, "lucy", "beautiful");
        Girl girl3 = new Girl(10, "lily", "beautiful");
        System.out.println("is same:" + girl1.compareTo(girl2));
        System.out.println("is same:" + girl1.compareTo(girl3));

        //Boy没有实现Comparable接口，直接通过自定义Comparator的方法来做比较
        Boy boy1 = new Boy(21, "lilei", "perfect1");
        Boy boy2 = new Boy(21, "Jim", "hansome");
        Boy boy3 = new Boy(21, "lilei", "hansome12");
        //按照age，name，face的自然顺序进行比较
        Comparator<Boy> boyComparator = new Comparator<Boy>() {
            @Override
            public int compare(Boy o1, Boy o2) {
                return ComparisonChain.start().
                        compare(o1.age, o2.age).
                        compare(o1.name, o2.name).
                        compare(o1.face, o2.face).
                        result();
            }
        };
        List<Boy> boyList = Lists.newArrayList(boy1, boy2, boy3);
        Collections.sort(boyList, boyComparator);
        System.out.println(boyList.toString());

        //按照长度进行比较
        final Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        //按照age，name，然后按照face的长度进行比较。这里用Ordering来包装了一下比较器
        Comparator<Boy> boyComparator2 = new Comparator<Boy>() {
            @Override
            public int compare(Boy o1, Boy o2) {
                return ComparisonChain.start().
                        compare(o1.age, o2.age).
                        compare(o1.name, o2.name).
                        compare(o1.face, o2.face, Ordering.from(lengthComparator)).
                        result();
            }
        };

        Collections.sort(boyList, boyComparator2);
        System.out.println(boyList.toString());

    }

    static class Girl implements Comparable<Girl> {
        private int age;
        private String name;
        private String face;

        Girl(int age, String name, String face) {
            this.age = age;
            this.name = name;
            this.face = face;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        @Override
        public int compareTo(Girl girl) {
            return ComparisonChain.start().
                    compare(this.name, girl.name).
                    compare(this.age, girl.age).
                    compare(this.face, girl.face).
                    result();
        }
    }

    static class Boy {
        private int age;
        private String name;
        private String face;

        Boy(int age, String name, String face) {
            this.age = age;
            this.name = name;
            this.face = face;
        }

        @Override
        public String toString() {
            return "Boy{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", face='" + face + '\'' +
                    '}';
        }
    }

}
