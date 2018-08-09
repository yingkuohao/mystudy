package com.mylearn.taobao.metadata.reactor.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.Reactor;
import reactor.event.Event;
import reactor.spring.annotation.Selector;

import javax.print.DocFlavor;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/24
 * Time: 17:16
 * CopyRight: taobao
 * Descrption:
 */

@Component
public class IndexHandler {
    @Autowired
    @Qualifier("rootReactor")
    private Reactor reactor;

          //×¢²áÊÂ¼þ
    @Selector(value = "hello", reactor = "@rootReactor")
    public void handleTestTopic(Event<String> evt)  throws Exception {
        System.out.println("asdfasdf"+evt.getData());
    }
}
