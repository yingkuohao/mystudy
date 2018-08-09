package com.mylearn.taobao.metadata.reactor.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/11/24
 * Time: 17:13
 * CopyRight: taobao
 * Descrption:
 */

@Configuration
@EnableReactor
public class ReactorConfig {

    @Bean(name="rootReactor")
    public Reactor rootReactor(Environment env) {
        return Reactors.reactor().env(env).get();
    }

    @Bean(name="reportReactor")
     public Reactor reportReactor(Environment env) {
         return Reactors.reactor().env(env).get();
     }
}
