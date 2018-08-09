package com.mylearn.j2ee.liumin;

import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/8/26
 * Time: 下午2:12
 * CopyRight: taobao
 * Descrption:
 */

public class Client {

    static Map<Integer, Car> carMap = new HashMap<Integer, Car>();

    public static void main(String[] args) {
        InputStream s=System.in;

        //1.初始化一些车
        List<Car> carList = intCarList();
        //2. 用户确认是否租车
        System.out.println("请输入是否租车?0:否1:是");
        String input = args[0];
        if (StringUtils.isNotBlank(input)) {
            Integer confrim = Integer.valueOf(input);

            if (ConfirmType.YES.getType() == confrim) {
                //3.如果确认,打印车列表
                printCarList(carList);
                //4. 选择数量
                System.out.println("请输入租车数量");
                String num = args[1];
                if (StringUtils.isNotBlank(num) && StringUtils.isNumeric(num)) {
                    Integer orderNum = Integer.valueOf(num);
                    if (orderNum > carList.size() || orderNum < 0) {
                        System.out.println("输出数量超限");
                        return;
                    }
                    //5.循环输入车的编号
                    List<Integer> orderList = new ArrayList<Integer>();
                    for (int i = 0 + 1; i < orderNum + 1; i++) {
                        System.out.println("第" + i + "辆车");
                        String curCar = args[1 + i];
                        while (!checkNum(carList, curCar)) {
                            System.out.println("请重新输入");
                        }
                        orderList.add(Integer.valueOf(curCar));
                    }
                    //6.输入租车天数
                    System.out.println("请输入租车天数");
                    String days = args[1];
                    //7. 计算结果
                    Long totalPrice = calculate(orderList);
                    if (StringUtils.isNotBlank(days) && StringUtils.isNumeric(days)) {
                        if (Integer.valueOf(days) < 0) {
                            System.out.println("输出数量超限");
                            return;
                        }
                        System.out.println("最终报价:" + Integer.valueOf(days) * totalPrice);
                    }
                }

            }
        }


    }

    private static Long calculate(List<Integer> orderList) {
        Long totalPrice = 0l;
        for (Integer orderNum : orderList) {
            Car car = carMap.get(orderNum);
            if (car != null) {
                totalPrice += car.getPrice();
            }
        }
        return totalPrice;
    }

    private static boolean checkNum(List<Car> carList, String curCar) {
        if (StringUtils.isNotBlank(curCar) && StringUtils.isNumeric(curCar)) {
            Integer curCarNum = Integer.valueOf(curCar);
            if (curCarNum > carList.size() || curCarNum < 0) {
                System.out.println("输出数量不合法");
                return false;
            }
            return true;
        }
        return false;
    }

    private static void printCarList(List<Car> carList) {

 /*       //基础遍历
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println("当前汽车为:" + car);
        }*/

        //for遍历
        for (Car car : carList) {
            System.out.println("当前汽车为:" + car);
        }

   /*     //iterator遍历
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            System.out.println("当前汽车为:" + car);
        }
        //lamda表达式
        carList.forEach(n -> {
            System.out.println("当前汽车为:" + n);
        });
        //lamda表达式2
        carList.forEach(System.out::println);*/

    }

    private static List<Car> intCarList() {
        List<Car> arrayList = new ArrayList<Car>();

        //初始化一些汽车到内存,如果数量多,读数据库
        Car audi = new Car(1, "奥迪", LoadTypeEnum.ZK.getType(), 400);
        Car benz = new Car(2, "奔驰", LoadTypeEnum.ZK.getType(), 500);
        Car fentian = new Car(3, "丰田", LoadTypeEnum.ZK.getType(), 300);
        Car q7 = new Car(4, "Q7", LoadTypeEnum.ZH.getType(), 300);
        Car lh = new Car(5, "路虎", LoadTypeEnum.ALL.getType(), 300);
        //把汽车对象加入list
        arrayList.add(audi);
        arrayList.add(benz);
        arrayList.add(fentian);
        arrayList.add(q7);
        arrayList.add(lh);

        carMap.put(1, audi);
        carMap.put(2, benz);
        carMap.put(3, fentian);
        carMap.put(4, q7);
        carMap.put(5, lh);
        return arrayList;
    }
}
