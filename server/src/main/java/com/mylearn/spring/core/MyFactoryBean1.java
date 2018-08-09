package com.mylearn.spring.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: ����9:53
 * CopyRight: taobao
 * Descrption:
 * spring��bean������,һ������ͨbean,ͨ��xml����ע������,��BeanFactory����id��ȡbean��ʱ��,ֱ�Ӿ��Ǿ���ʵ������.
 * ��һ����ʵ����FactoryBean�ӿڵ�Bean,��BeanFactory�и��ݶ����id��ȡbeanʱ,ʵ������FactoryBean�ӿ��е�getObject�������صĶ���.
 * <p/>
 * MyFactoryBean1.Car car = (MyFactoryBean1.Car) context.getBean("myCar");
 * ͨ������"&"����,���Ի�ô����౾��
 * MyFactoryBean1  myFactoryBean1 =  context.getBean("&myCar",MyFactoryBean1.class);
 */

@Component("myCar")
public class MyFactoryBean1 implements FactoryBean {
    private String fileld = "123";


    @Override
    public Object getObject() throws Exception {
        return new Car("yellow", 10000);
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    class Car {
        private String color;
        private Integer price;

        public Car(String color, Integer price) {
            this.color = color;
            this.price = price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }
}
