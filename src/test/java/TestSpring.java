import com.xiya.entity.Person;
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
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
