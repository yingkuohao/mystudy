import com.mylearn.spring.Person;
import com.mylearn.spring.integration.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/12/3
 * Time: 14:04
 * CopyRight: taobao
 * Descrption:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/common-aop.xml"})
public class TestSpring {

    @Autowired
    private Map<String, HelloService>   helloMap;
    @Resource
    Person person;

    @Test
    public void testPerson() {
        person.run();
    }

}
