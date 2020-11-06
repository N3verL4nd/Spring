import com.xiya.service.PersonManager;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author N3verL4nd
 * @date 2020/11/7
 */
public class TestSpring {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonManager peopleManager = context.getBean(PersonManager.class);
        peopleManager.sayHello();
    }
}
