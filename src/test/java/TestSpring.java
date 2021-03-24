import com.xiya.test.A;
import com.xiya.test.Person;
import com.xiya.test.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author N3verL4nd
 * @date 2020/11/7
 */
public class TestSpring {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void test1() {
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }

    @Test
    public void test2() {
        PersonService personService = context.getBean(PersonService.class);
        System.out.println(personService.getPerson());
    }

    @Test
    public void test3() {
        A a = context.getBean(A.class);
    }
}
