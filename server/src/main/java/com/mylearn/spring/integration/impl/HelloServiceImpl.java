package com.mylearn.spring.integration.impl;

import com.mylearn.spi.HelloInterface;
import com.mylearn.spring.integration.HelloService;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/9/19
 * Time: 上午9:08
 * CopyRight: taobao
 * Descrption:
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(String name) {
        System.out.println("Hello, " + name);
    }
}
