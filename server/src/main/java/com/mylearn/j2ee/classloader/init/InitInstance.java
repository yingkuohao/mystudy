package com.mylearn.j2ee.classloader.init;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-24
 * Time: ����9:41
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class InitInstance {
    public static void main(String args[]) {
        //1.����ĳ�����ʵ��ʱ
        Sample sample1 = new Sample(5, 4);
        Sample sample2 = new Sample(50, 40);
//        2.������ĳ����ľ�̬����ʱ
        Sample.metod1();
//        3.��ʹ��ĳ�����ӿڵľ�̬�ֶΣ����߶Ը��ֶθ�ֵʱ����final���εľ�̬�ֶγ��⡣
        System.out.println(Sample.a);
        System.out.println(Sample.e);
        System.out.println(Sample.c);
        System.out.println(Sample.d);  //����ʱʱ����
        System.out.println(Sample.a);  //����ʱʱ����
        //4. �����÷��䷽��ʱ
//        try {
//        4.1 //forName�ǿ��Գ�ʼ����
//            Object obj = Class.forName("com.mylearn.j2ee.classloader.init.Sample");

        //4.2.1  loadClass����ֻ����أ��õ���class�ǻ�û�����ӵģ��������ʼ����ֻ�����״�ʹ�õ�ʱ��Ż����ӣ���ʼ��
//            ClassLoader classLoader =Sample.class.getClassLoader();
//            classLoader.loadClass("com.mylearn.j2ee.classloader.init.Sample");
        //4.2.2    loadClass�����ṩ���ط������ڶ������������Ƿ�ִ�����ӣ�true����ִ�����ӣ�false����ִ�У�ֻ�������������protected���ϱߵ�loadClass����������ʵ����false�Ĵ˷�����
//            classLoader.loadClass("com.mylearn.j2ee.classloader.init.Sample",true);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        5.��ʼ��ĳ���������ʱ��ĳ�����ʼ��ʱ��Ҫ�����ĳ����Ѿ�����ʼ���ˣ�
        Child child = new Child();
        System.out.println(Child.a); //ע�����a�Ǹ����
        child.doSth(); //�������������

//        5.1ע�⸸�������ı����ͷ�������
        Parent parent = new Child();
        System.out.println(Parent.a); //ע�����a�Ǹ����
        parent.doSth(); //�������������

        System.out.println(Angry.greeting);
        System.out.println(Dog.greeting);

        //5.2 ע�⣺�ӿڵ�������
        Angry angry = new Dog();  //��ʼ�����࣬�ӿڲ��ᱻ��ʼ��
        System.out.println("-----------");
        System.out.println(Angry.greeting); //���ýӿڵĳ����ֶΣ�Ҳ���ᱻ��ʼ��
        System.out.println("-----------");
        System.out.println(Angry.angerLevel);  //���ýӿڵķǳ����ֶΣ����Ա���ʼ��

        //6.�����������ʱĳ������עΪ��������ࣨ������main�������࣬������ִ��Java����ִ��ĳ����ʱ��

    }
}

class Sample {

    public static String cart = "JD_CART";

    public static int a;

    public static final int e = 3; // ��final���εľ�̬�ֶγ���

    public static final int c = 6 / 2; // ��final���εı���ʱ����Ҳ��������ʹ��

    public static final double d = Math.random() * 5;   //��final���ε�����ʱ��������������

    public int b;

    static {
        a = 3;
        System.out.println("Sample��ִ�г�ʼ����Ϊ�������ֵ,a=" + a);
    }

    public Sample(int a1, int b1) {
        a = a1;
        b = b1;
        System.out.println("Sample��ִ�й��췽����Ϊʵ��������ֵ,b=" + b);
    }

    public Sample() {
        a = 5;
        b = 4;
        System.out.println("Sample��ִ�й��췽����Ϊʵ��������ֵ,b=" + b);
    }

    public static void metod1() {
        System.out.println("��̬����execute��,Ϊ�������ֵ,a=" + a);
    }

    public void metod2() {
        System.out.println("ʵ������execute��");
    }

}

class Parent {
    static int a = 3;

    static {
        System.out.println("Parent ��ʼ���������a=" + a);
    }

    public void doSth() {
        System.out.println("Parent dosth��");
    }
}

class Child extends Parent {
    static int a = 4;

    static {
        System.out.println("Child ��ʼ���������a=" + a);
    }

    public void doSth() {
        System.out.println("Child dosth��");
    }
}

interface Angry {
    String greeting = "Grrr��";

    //���Angry����ʼ���ˣ��� Dog.getAngerLevel();�ᱻ���ã�����ӡ Angry was initialized!
    int angerLevel = Dog.getAngerLevel();
}

class Dog implements Angry {

    static final String greeting = "Woof, woof, woof";

    static {
        System.out.println("Dog was initialized!");
    }

    public static int getAngerLevel() {
        System.out.println("Angry was initialized!");
        return 1;
    }
}

