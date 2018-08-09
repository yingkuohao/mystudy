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
 * Ordering是Guava流畅风格比较器的实现，他可以用来构建复杂的比较器，以完成集合排序的功能。
 * 从实际上来说Ordering实例就是一个特殊的Comparator实例。Ordering把很多基于Comparator的静态方法
 * 包装为自己的实例方法，并且提供了链式调用方法，来定制和增强现有的比较器。
 */

public class OrderingTest {
    public static void main(String args[]) {

        List<String> list = Lists.newArrayList("jerry", "tom", "eva", "john", "lily");
        Ordering<String> naturalOrdering = Ordering.natural();     //自然序列
        Ordering<Object> stringOrdering = Ordering.usingToString(); //string的序列
        Ordering<Object> arbitraryOrdering = Ordering.arbitrary();  //任一序列，随机顺序

        System.out.println("naturalOrdering=" + naturalOrdering.sortedCopy(list));
        System.out.println("stringOrdering=" + stringOrdering.sortedCopy(list));
        System.out.println("arbitraryOrdering=" + arbitraryOrdering.sortedCopy(list));

        List<Integer> numList = Lists.newArrayList(2, 4, 6, 8, 10, 5, 3, 1, 7, 9);
        Ordering<Integer> numOrder = Ordering.natural();
        System.out.println("is numList ordered=" + numOrder.isOrdered(numList));   //判断是否有序
        System.out.println("is numList ordered=" + numOrder.isStrictlyOrdered(numList)); //判断是否严格有序

        System.out.println("is numList sortedCopy ordered=" + numOrder.isOrdered(numOrder.sortedCopy(numList)));

        System.out.println(" numList leastOf 2 =" + numOrder.leastOf(numList, 2)); //取最小的两个
        System.out.println(" numList greatestOf 2 =" + numOrder.greatestOf(numList, 2)); //取最大的两个


        Foo foo = new Foo(1, "zhangsan");
        Foo foo2 = new Foo(2, "lisi");
        List<Foo> lst = new ArrayList<Foo>();
        lst.add(foo2);
        lst.add(foo);
        //链式调用，从后往前读，排序器会首先调用apply方法获取sort的值，并把sort为null的元素放到最前面，然后把剩下的元素按sort进行
        //自然排序。
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
