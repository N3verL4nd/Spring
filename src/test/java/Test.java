import com.xiya.service.PersonManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* @file Test.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/6/17
*/

public class Test {
    @org.junit.Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        PersonManager peopleManager = context.getBean(PersonManager.class);
        peopleManager.sayHello();
    }
}
