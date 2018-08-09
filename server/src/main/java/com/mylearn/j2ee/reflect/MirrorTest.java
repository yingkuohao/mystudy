package com.mylearn.j2ee.reflect;


import net.vidageek.mirror.dsl.Mirror;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/6/12
 * Time: 14:58
 * CopyRight: taobao
 * Descrption:
 * http://projetos.vidageek.net/mirror/constructor/reflecting/
 */

public class MirrorTest {
    public static void main(String[] args) {

        Dog dog = new Dog("xiaobai", 1);
        //1. Field
        Field field = new Mirror().on(Dog.class).reflect().field("name");
        System.out.println("field.name=" + field.getName());
        List<Field> fieldList = new Mirror().on(Dog.class).reflectAll().fields();
        System.out.println("fieldList=" + fieldList.toString());

        //setting Value with an instance field
        new Mirror().on(dog).set().field("name").withValue("xiaohei");
        System.out.println(dog.getName());
//        new Mirror().on(dog).set().field("age").withValue("123");
//        System.out.println(dog.getAge());
        //静态变量赋值
        new Mirror().on(Dog.class).set().field("color").withValue("black");
        System.out.println("Dog.color:" + Dog.color);
        //getting value
        System.out.println(new Mirror().on(dog).get().field("age"));

        //2. Method
        Method method = new Mirror().on(Dog.class).reflect().method("testMethod1").withoutArgs();
        Method method1 = new Mirror().on(Dog.class).reflect().method("testMethod1").withArgs(String.class);
        System.out.println("method without Args=" + method.getName());
        System.out.println("method wity Args=" + method1.getName());
        List<Method> methodList = new Mirror().on(Dog.class).reflectAll().methods();

        List<Method> gettersList = new Mirror().on(Dog.class).reflectAll().getters();
        List<Method> setters = new Mirror().on(Dog.class).reflectAll().setters();

        //Method invoke
        Object returnValue = new Mirror().on(dog).invoke().method("testMethod1").withoutArgs();

        // 3. Invoking a setter method:
        new Mirror().on(dog).invoke().setterFor("name").withValue("大黑");
        System.out.println("dog.getName=" + dog.getName());
        // Invoking a getter method:
        Object name = new Mirror().on(dog).invoke().getterFor("name");
        System.out.println("dog.getName=" + name);

        //4. annotation
        List<Annotation> annotations = new Mirror().on(Dog.class).reflectAll().annotations().atClass();

        List<Annotation> fieldAnnotations = new Mirror().on(Dog.class).reflectAll().annotations().atField("name");
        List<Annotation> methodAnnotations = new Mirror().on(Dog.class).reflectAll().annotations().atMethod("testMethod1").withoutArgs();

        TestAnno testAnno = new Mirror().on(Dog.class).reflect().annotation(TestAnno.class).atMethod("testMethod1").withoutArgs();
        System.out.println("anno.name=" + testAnno.appName());
    }

    static class Dog {
        private String name;
        private int age;
        private static String color = "yellow";

        @TestAnno(appName = "test", level = 1)
        public void testMethod1() {
            System.out.println("method1");
        }

        public void testMethod1(String value) {
            System.out.println("method1");
        }

        public void testMethod2(String value) {
            System.out.println("method1" + value);
        }

        public Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
