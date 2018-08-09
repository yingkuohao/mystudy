import com.mylearn.taobao.metadata.reactor.FangEvent;
import com.mylearn.taobao.metadata.reactor.FangReactorPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2014/7/16
 * Time: 16:09
 * CopyRight: taobao
 * Descrption:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/common-aop.xml"})
public class TestComand  {
    @Resource
    FangReactorPublisher fangReactorPublisher;




    @Test
    public void testclean() {
        FangEvent fangEvent=new FangEvent("hello");
        fangEvent.setName("hello");
        fangReactorPublisher.publish(fangEvent);
    }
}
