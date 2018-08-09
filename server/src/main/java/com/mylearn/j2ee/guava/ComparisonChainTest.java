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
 * ����������Ƚ�ʱ��Java�ṩ��compare��compareTo��������Ҫʵ��һ���Ƚ�����Comparator����
 * ֱ��ʵ��Comparable�ӿڣ��������������Թ����ʱ��������Ҫд������if-else���룬���벻��
 * ���ţ�GuavaΪ���Ǽ�����һ�㣬���ǿ���ʹ��ComparisonChain�������ŵ�ʵ�ֶ���֮��ıȽϡ�
 * �鿴ComparisonChainԴ�룬���Է��֣�����һ�������࣬��Ҫ�ṩ���������󷽷���start()���ڷ����ڲ���һ��ComparisonChain
 * ʵ�֣������˺ܶ�compare()���������ڽ��ո������͵Ĳ�����compare()�������ص���Ȼ��ComparisonChain����
 * result()�����������ڱȽϺ�Ľ����
 *
 * ������ʽ�ı�����ʽ����Builder������ģʽ��Ӧ�á�
 */

public class ComparisonChainTest {
    public static void main(String args[]) {
        //Girlʵ����comparable�ӿڣ��Զ�����compareTo����
        Girl girl1 = new Girl(10, "lily", "beautiful");
        Girl girl2 = new Girl(10, "lucy", "beautiful");
        Girl girl3 = new Girl(10, "lily", "beautiful");
        System.out.println("is same:" + girl1.compareTo(girl2));
        System.out.println("is same:" + girl1.compareTo(girl3));

        //Boyû��ʵ��Comparable�ӿڣ�ֱ��ͨ���Զ���Comparator�ķ��������Ƚ�
        Boy boy1 = new Boy(21, "lilei", "perfect1");
        Boy boy2 = new Boy(21, "Jim", "hansome");
        Boy boy3 = new Boy(21, "lilei", "hansome12");
        //����age��name��face����Ȼ˳����бȽ�
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

        //���ճ��Ƚ��бȽ�
        final Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        };

        //����age��name��Ȼ����face�ĳ��Ƚ��бȽϡ�������Ordering����װ��һ�±Ƚ���
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
