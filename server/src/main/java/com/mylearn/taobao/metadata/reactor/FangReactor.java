package com.mylearn.taobao.metadata.reactor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;
/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/21
 * Time: 10:56
 * CopyRight: taobao
 * Descrption:
 */
@Configuration
@EnableReactor
public class FangReactor {

    @Bean(name="rootReactor")
     public Reactor rootReactor(Environment env) {
         return Reactors.reactor().env(env).get();
     }

}
