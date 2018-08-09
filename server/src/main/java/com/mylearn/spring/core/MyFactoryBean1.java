package com.mylearn.spring.core;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/22
 * Time: 上午9:53
 * CopyRight: taobao
 * Descrption:
 * spring的bean有两种,一种是普通bean,通过xml或者注解声明,在BeanFactory根据id获取bean的时候,直接就是具体实现类型.
 * 另一种是实现了FactoryBean接口的Bean,在BeanFactory中根据定义的id获取bean时,实际上是FactoryBean接口中的getObject方法返回的对象.
 * <p/>
 * MyFactoryBean1.Car car = (MyFactoryBean1.Car) context.getBean("myCar");
 * 通过加上"&"符号,可以获得代理类本身
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
