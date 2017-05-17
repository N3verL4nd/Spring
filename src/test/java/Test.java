import com.xiya.service.PeopleManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by N3verL4nd on 2017/4/9.
 */
public class Test {
    @org.junit.Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-helloworld.xml");
        PeopleManager peopleManager = context.getBean(PeopleManager.class);
        peopleManager.sayHello();
    }
}
