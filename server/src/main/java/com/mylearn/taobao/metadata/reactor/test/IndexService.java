package com.mylearn.taobao.metadata.reactor.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.Reactor;
import reactor.event.Event;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/24
 * Time: 17:19
 * CopyRight: taobao
 * Descrption:
 */

@Service
public class IndexService {
    @Autowired
    @Qualifier("rootReactor")
    private Reactor r;


    public void clean() {
        //发送事件
        r.notify("hello", Event.wrap("你好！"));
    }


}
