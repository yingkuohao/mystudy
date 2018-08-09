package com.mylearn.repository.mapper;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/6/23
 * Time: 下午3:30
 * CopyRight: taobao
 * Descrption:测试接口,看他会不会生成代理类,结论是会生成,
 * 但是执行具体的方法时会报错,因为没有SELECT标签,在调用MapperProxyBean的invoke方法时构造
 * MapperMethod时会校验失败
 */

public interface TestInterface {
    void sayHello();
}
