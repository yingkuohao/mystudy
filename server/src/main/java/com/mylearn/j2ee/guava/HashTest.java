package com.mylearn.j2ee.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import com.google.common.hash.PrimitiveSink;
import com.google.common.primitives.Ints;
import com.google.common.reflect.ClassPath;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/29
 * Time: 9:58
 * CopyRight: taobao
 * Descrption: 布隆过滤测试
 */

public class HashTest {
    public static void main(String args[]) {
        //classpath的使用
//        classpathTest();
//        boolemFilterTest();
        HashTest hashTest = new HashTest();
        hashTest.bloomFilterTest2();
    }

    private static void classpathTest() {
        ClassLoader classloader = HashTest.class.getClassLoader();
        ClassPath classpath = null;
        try {
            classpath = ClassPath.from(classloader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses("com.mylearn.j2ee.guava")) {
            System.out.println(classInfo.getName());
        }
    }

    //简单的bloomFilter，过滤简单原生类型
    public static void boolemFilterTest() {
        //初始化布隆过滤
        BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1000);
        //putting elements into the filter
        int n = 1000;
        for (int i = 0; i < n; i++) {
            bloomFilter.put(i);
        }
        //校验元素是否在set中
        System.out.println("conta2=" + bloomFilter.mightContain(2));
        System.out.println("conta3=" + bloomFilter.mightContain(3));
        System.out.println("conta1002=" + bloomFilter.mightContain(1002));
    }

    /**
     * 复杂的过滤器，过滤对象
     */
    public void bloomFilterTest2() {
        //定义一个funnel，描述了如何把一个具体的对象类型分解为原生字段值，从而写入PrimitiveSink。
        Funnel<Person> personFunnel = new Funnel<Person>() {
            private static final long serialVersionUID = -5292476436911390775L;

            @Override
            public void funnel(Person from, PrimitiveSink into) {
                into.putInt(from.id)
                        .putString(from.firstName, Charsets.UTF_8)
                        .putString(from.lastName, Charsets.UTF_8)
                        .putInt(from.birth);
            }
        };
        //定义一个bloomfilter，期待插入值为500，失败概率为1%
        BloomFilter<Person> friends = BloomFilter.create(personFunnel, 500, 0.01);
        for (int i = 0; i < 500; i++) {
            Person person = createPerson(i);
            friends.put(person);    //插入数据
        }

        //测试校验
        Person person = createPerson(1);
        if(!friends.mightContain(person)) {
         friends.put(person);    //插入数据
        }
        System.out.println();
        Person person2 = createPerson(1000);
        System.out.println(friends.mightContain(person2));
        Person dude = new Person();
        if (friends.mightContain(dude)) {
            //yingkhtodo:description:如果dube不是朋友，但是还是运行到这里了，概率仅为1%
        }
    }

    private Person createPerson(int i) {
        Person person = new Person();
        person.setBirth(i);
        person.setFirstName(i + "first");
        person.setLastName(i + "last");
        person.setId(i);
        return person;
    }

    class Person {
        int id;
        String firstName;
        String lastName;
        int birth;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getBirth() {
            return birth;
        }

        public void setBirth(int birth) {
            this.birth = birth;
        }
    }
}
