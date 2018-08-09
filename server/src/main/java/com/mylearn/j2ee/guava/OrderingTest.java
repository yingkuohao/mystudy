package com.mylearn.j2ee.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/2
 * Time: 15:17
 * CopyRight: taobao
 * Descrption:
 * Ordering��Guava�������Ƚ�����ʵ�֣������������������ӵıȽ���������ɼ�������Ĺ��ܡ�
 * ��ʵ������˵Orderingʵ������һ�������Comparatorʵ����Ordering�Ѻܶ����Comparator�ľ�̬����
 * ��װΪ�Լ���ʵ�������������ṩ����ʽ���÷����������ƺ���ǿ���еıȽ�����
 */

public class OrderingTest {
    public static void main(String args[]) {

        List<String> list = Lists.newArrayList("jerry", "tom", "eva", "john", "lily");
        Ordering<String> naturalOrdering = Ordering.natural();     //��Ȼ����
        Ordering<Object> stringOrdering = Ordering.usingToString(); //string������
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();  //��һ���У����˳��

        System.out.println("naturalOrdering=" + naturalOrdering.sortedCopy(list));
        System.out.println("stringOrdering=" + stringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering=" + arbitraryOrdering.sortedCopy(list));

        List<Integer> numList = Lists.newArrayList(2, 4, 6, 8, 10, 5, 3, 1, 7, 9);
        Ordering<Integer> numOrder = Ordering.natural();
        System.out.println("is numList ordered=" + numOrder.isOrdered(numList));   //�ж��Ƿ�����
        System.out.println("is numList ordered=" + numOrder.isStrictlyOrdered(numList)); //�ж��Ƿ��ϸ�����

        System.out.println("is numList sortedCopy ordered=" + numOrder.isOrdered(numOrder.sortedCopy(numList)));

        System.out.println(" numList leastOf 2 =" + numOrder.leastOf(numList, 2)); //ȡ��С������
        System.out.println(" numList greatestOf 2 =" + numOrder.greatestOf(numList, 2)); //ȡ��������


        Foo foo = new Foo(1, "zhangsan");
        Foo foo2 = new Foo(2, "lisi");
        List<Foo> lst = new ArrayList<Foo>();
        lst.add(foo2);
        lst.add(foo);
        //��ʽ���ã��Ӻ���ǰ���������������ȵ���apply������ȡsort��ֵ������sortΪnull��Ԫ�طŵ���ǰ�棬Ȼ���ʣ�µ�Ԫ�ذ�sort����
        //��Ȼ����
        Ordering<Foo> fooOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, Comparable>() {
            @Override
            public Comparable apply(Foo foo) {
                return foo.getSort();
            }
        });
        System.out.println(" lst reuslt =" + fooOrdering.sortedCopy(lst));


    }

    static class Foo {
        int sort;
        String name;

        Foo(int sort, String name) {
            this.sort = sort;
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "sort=" + sort +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
