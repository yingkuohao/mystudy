package com.mylearn.taobao.metadata.reactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.Reactor;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/21
 * Time: 11:01
 * CopyRight: taobao
 * Descrption:
 */
@Service("fangReactorPublisher")
public class FangReactorPublisher {

    @Autowired
    @Qualifier("rootReactor")
    private Reactor reactor;

    public void publish(FangEvent fangEvent) {
        //·¢ËÍÊÂ¼þ
        reactor.notify(fangEvent.getName(), fangEvent);
    }
}
