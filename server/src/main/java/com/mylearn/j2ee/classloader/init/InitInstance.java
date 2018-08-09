package com.mylearn.j2ee.classloader.init;

/**
 * Created by IntelliJ IDEA.
 * User: yingkuohao
 * Date: 13-12-24
 * Time: 上午9:41
 * CopyRight:360buy
 * Descrption:
 * To change this template use File | Settings | File Templates.
 */
public class InitInstance {
    public static void main(String args[]) {
        //1.创建某个类的实例时
        Sample sample1 = new Sample(5, 4);
        Sample sample2 = new Sample(50, 40);
//        2.当调用某个类的静态方法时
        Sample.metod1();
//        3.当使用某个类或接口的静态字段，或者对该字段赋值时，用final修饰的静态字段除外。
        System.out.println(Sample.a);
        System.out.println(Sample.e);
        System.out.println(Sample.c);
        System.out.println(Sample.d);  //运行时时常量
        System.out.println(Sample.a);  //运行时时常量
        //4. 当调用反射方法时
//        try {
//        4.1 //forName是可以初始化的
//            Object obj = Class.forName("com.mylearn.j2ee.classloader.init.Sample");

        //4.2.1  loadClass方法只会加载，得到的class是还没有连接的，更不会初始化，只有在首次使用的时候才会链接，初始化
//            ClassLoader classLoader =Sample.class.getClassLoader();
//            classLoader.loadClass("com.mylearn.j2ee.classloader.init.Sample");
        //4.2.2    loadClass方法提供重载方法，第二个参数代表是否执行链接，true代表执行链接，false代表不执行，只不过这个方法是protected，上边的loadClass方法掉的其实就是false的此方法。
//            classLoader.loadClass("com.mylearn.j2ee.classloader.init.Sample",true);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//        }
//        5.初始化某个类的子类时（某个类初始化时，要求它的超类已经被初始化了）
        Child child = new Child();
        System.out.println(Child.a); //注意这里，a是父类的
        child.doSth(); //而方法是子类的

//        5.1注意父类和子类的变量和方法调用
        Parent parent = new Child();
        System.out.println(Parent.a); //注意这里，a是父类的
        parent.doSth(); //而方法是子类的

        System.out.println(Angry.greeting);
        System.out.println(Dog.greeting);

        //5.2 注意：接口的特殊性
        Angry angry = new Dog();  //初始化子类，接口不会被初始化
        System.out.println("-----------");
        System.out.println(Angry.greeting); //调用接口的常量字段，也不会被初始化
        System.out.println("-----------");
        System.out.println(Angry.angerLevel);  //调用接口的非常量字段，可以被初始化

        //6.当虚拟机启动时某个被标注为启动类的类（即含有main方法的类，如我们执行Java命令执行某个类时）

    }
}

class Sample {

    public static String cart = "JD_CART";

    public static int a;

    public static final int e = 3; // 用final修饰的静态字段除外

    public static final int c = 6 / 2; // 用final修饰的编译时常量也不是主动使用

    public static final double d = Math.random() * 5;   //用final修饰的运行时常量是主动引用

    public int b;

    static {
        a = 3;
        System.out.println("Sample类执行初始化，为类变量赋值,a=" + a);
    }

    public Sample(int a1, int b1) {
        a = a1;
        b = b1;
        System.out.println("Sample类执行构造方法，为实例变量赋值,b=" + b);
    }

    public Sample() {
        a = 5;
        b = 4;
        System.out.println("Sample类执行构造方法，为实例变量赋值,b=" + b);
    }

    public static void metod1() {
        System.out.println("静态方法execute！,为类变量赋值,a=" + a);
    }

    public void metod2() {
        System.out.println("实例方法execute！");
    }

}

class Parent {
    static int a = 3;

    static {
        System.out.println("Parent 初始化类变量！a=" + a);
    }

    public void doSth() {
        System.out.println("Parent dosth！");
    }
}

class Child extends Parent {
    static int a = 4;

    static {
        System.out.println("Child 初始化类变量！a=" + a);
    }

    public void doSth() {
        System.out.println("Child dosth！");
    }
}

interface Angry {
    String greeting = "Grrr！";

    //如果Angry被初始化了，则 Dog.getAngerLevel();会被调用，则会打印 Angry was initialized!
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

