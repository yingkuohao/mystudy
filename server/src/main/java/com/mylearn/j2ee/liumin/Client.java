package com.mylearn.j2ee.liumin;

import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 17/8/26
 * Time: ����2:12
 * CopyRight: taobao
 * Descrption:
 */

public class Client {

    static Map<Integer, Car> carMap = new HashMap<Integer, Car>();

    public static void main(String[] args) {
        InputStream s=System.in;

        //1.��ʼ��һЩ��
        List<Car> carList = intCarList();
        //2. �û�ȷ���Ƿ��⳵
        System.out.println("�������Ƿ��⳵?0:��1:��");
        String input = args[0];
        if (StringUtils.isNotBlank(input)) {
            Integer confrim = Integer.valueOf(input);

            if (ConfirmType.YES.getType() == confrim) {
                //3.���ȷ��,��ӡ���б�
                printCarList(carList);
                //4. ѡ������
                System.out.println("�������⳵����");
                String num = args[1];
                if (StringUtils.isNotBlank(num) && StringUtils.isNumeric(num)) {
                    Integer orderNum = Integer.valueOf(num);
                    if (orderNum > carList.size() || orderNum < 0) {
                        System.out.println("�����������");
                        return;
                    }
                    //5.ѭ�����복�ı��
                    List<Integer> orderList = new ArrayList<Integer>();
                    for (int i = 0 + 1; i < orderNum + 1; i++) {
                        System.out.println("��" + i + "����");
                        String curCar = args[1 + i];
                        while (!checkNum(carList, curCar)) {
                            System.out.println("����������");
                        }
                        orderList.add(Integer.valueOf(curCar));
                    }
                    //6.�����⳵����
                    System.out.println("�������⳵����");
                    String days = args[1];
                    //7. ������
                    Long totalPrice = calculate(orderList);
                    if (StringUtils.isNotBlank(days) && StringUtils.isNumeric(days)) {
                        if (Integer.valueOf(days) < 0) {
                            System.out.println("�����������");
                            return;
                        }
                        System.out.println("���ձ���:" + Integer.valueOf(days) * totalPrice);
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
                System.out.println("����������Ϸ�");
                return false;
            }
            return true;
        }
        return false;
    }

    private static void printCarList(List<Car> carList) {

 /*       //��������
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i);
            System.out.println("��ǰ����Ϊ:" + car);
        }*/

        //for����
        for (Car car : carList) {
            System.out.println("��ǰ����Ϊ:" + car);
        }

   /*     //iterator����
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            System.out.println("��ǰ����Ϊ:" + car);
        }
        //lamda���ʽ
        carList.forEach(n -> {
            System.out.println("��ǰ����Ϊ:" + n);
        });
        //lamda���ʽ2
        carList.forEach(System.out::println);*/

    }

    private static List<Car> intCarList() {
        List<Car> arrayList = new ArrayList<Car>();

        //��ʼ��һЩ�������ڴ�,���������,�����ݿ�
        Car audi = new Car(1, "�µ�", LoadTypeEnum.ZK.getType(), 400);
        Car benz = new Car(2, "����", LoadTypeEnum.ZK.getType(), 500);
        Car fentian = new Car(3, "����", LoadTypeEnum.ZK.getType(), 300);
        Car q7 = new Car(4, "Q7", LoadTypeEnum.ZH.getType(), 300);
        Car lh = new Car(5, "·��", LoadTypeEnum.ALL.getType(), 300);
        //�������������list
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
