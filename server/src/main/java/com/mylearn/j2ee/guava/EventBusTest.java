package com.mylearn.j2ee.guava;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/4/28
 * Time: 17:45
 * CopyRight: taobao
 * Descrption:   GuavaµÄÊÂ¼þ¼àÌýÆ÷
 */

public class EventBusTest {
    public static void main(String args[]) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Object() {
            @Subscribe
            public void listen(Integer integer) {
                System.out.println("listen integer+" + integer);
            }

            @Subscribe
            public void listen(String str) {
                System.out.println("listen str+" + str);
            }

            @Subscribe
            public void listen(Long l) {
                System.out.println("listen Long+" + l);
            }
        });

        eventBus.post(1);
        eventBus.post("haha");
    }
}
