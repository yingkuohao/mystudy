package com.mylearn.java8.lamda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
*
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/11
 * Time: 16:26
 * CopyRight: taobao
 * Descrption: jdk8��lamda��������
 * <p/>
 * http://www.jdon.com/idea/java/10-example-of-lambda-expressions-in-java8.html

*/


public class LamdaTest {

    public static void main(String[] args) {
        //1. ���߳�
        new Thread(() -> System.out.println("In java8")).start();
        //2. ���ϱ���
        List features = Arrays.asList("lambda", "default method", "apid");
        features.forEach(n -> System.out.println(n));
        //��������c++�������ʽ
        features.forEach(System.out::println);

        //3.lamda���ʽ�ͺ����ӿ�
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.length() > 4);

        //4. ���ӵĽ��predicateʹ��
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream().filter(startsWithJ.and(fourLetterLong)).forEach((n) -> System.out.print("Name, which starts with 'J' and four letter long is : " + n));

        //5. map��reduce
        // 5.1 applying 12% VAT on each purchase
        // Without lambda expressions:
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            System.out.println(price);
        }
        // With Lambda expression:
        costBeforeTax.stream().map((cost) -> cost + .12 * cost).forEach(System.out::println);
        //5.2reduce() �ǽ�����������ֵ��Ͻ�һ����Reduce����SQL����е�sum(), avg() ��count(),
        // Old way:
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12 * cost;
            total = total + price;
        }
        //New way:
        double bill = costBeforeTax.stream().map((cost) -> cost + .12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("total=" + bill);

        //6.  .ͨ��filtering ����һ���ַ���String�ļ���
        List<String> strList = Arrays.asList("lambda", "default method", "apid");
        List<String> filtered = strList.stream().filter(x -> x.length() > 2)
                .collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n",
                strList, filtered);

        //8.�Լ�����ÿ��Ԫ��Ӧ�ú���
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany",
                "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase())
                .collect(Collectors.joining(", "));
        System.out.println(G7Countries);

        // 9.ͨ�����Ʋ�ͬ��ֵ����һ�����б�
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map(i -> i * i).distinct()
                .collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates :%s %n", numbers, distinct);
        // 10.����List�е�Ԫ�ص����ֵ����Сֵ���ܺͼ�ƽ��ֵ
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + "");
            }
        }
    }

    //���õĹ��˷���
    public static void filter2(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + "");

        });
    }

}
