import com.xiya.test.Person;
import com.xiya.test.PersonService;
import com.xiya.test.cricle.A;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

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
        ((ConfigurableApplicationContext) context).close();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        PersonService personService = context.getBean(PersonService.class);
        System.out.println(personService.getPerson());
    }

    @Test
    public void test3() {
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }

    @Test
    public void test4() {
        PersonService personService = (PersonService) context.getBean("personService");
        System.out.println(personService.getPerson());
    }

    @Test
    public void testCircle() {
        A a = context.getBean("a", A.class);
        System.out.println(a);
    }
}
