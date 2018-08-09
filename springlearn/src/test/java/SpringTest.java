import com.ying.spring.aop.aspect.AOPTargetClassImpl;
import com.ying.spring.aop.aspect.AOPTargetInterface;
import com.ying.spring.aop.aspect.AOPTargetInterface2;
import com.ying.spring.aop.bytype.TypeTestService;
import com.ying.spring.aop.interceptor.InterceptorTargetClassImpl;
import com.ying.spring.aop.interceptor.TestAnnotInterceptorClas;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: chengjing
 * Date: 16/12/14
 * Time: 下午6:49
 * CopyRight: taobao
 * Descrption:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/common-aop.xml"})
public class SpringTest {
    @Resource
    AOPTargetInterface aopTargetClass;
    @Resource
    AOPTargetInterface2 aopTargetInterface2;
    @Resource
    InterceptorTargetClassImpl interceptorTargetClass;
    @Resource
    TestAnnotInterceptorClas testAnnotInterceptorClas;

    /*
    @Resource
    TypeTestService typeA;
    */

    /*
    @Resource(name = "typeB")
    TypeTestService typeB;
    */

    @Resource(name = "typeB")
    TypeTestService typeC;

    @Autowired
    //@Qualifier("typeA")
    TypeTestService typeA;

    @Test
    public void testAOPAspect() {
        for (int i = 0; i < 100; i++) {
            aopTargetClass.sayHello();
        }

        for (int i = 0; i < 100; i++) {
            testAnnotInterceptorClas.sayHello();
        }
        //        aopTargetClass.sayHello();
        //        aopTargetInterface2.sayHello2();
        //        aopTargetInterface2.sayHello2();
    }

    @Test
    public void testAOPIntercept() {
        interceptorTargetClass.sayHello();
    }

    @Test
    public void testAnnotInterceptorClas() {
        testAnnotInterceptorClas.sayHello();
    }
}
