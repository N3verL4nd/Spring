import com.xiya.test.Person;
import com.xiya.test.PersonService;
import com.xiya.test.dal.example.ActivityExample;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author liguanghui02
 * @date 2020/11/11
 */
public class T {
    @Test
    public void test1() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        PersonService personService = beanFactory.getBean(PersonService.class);
        System.out.println(personService.getPerson());
        Person person = beanFactory.getBean(Person.class);
        System.out.println(person);
    }

    @Test
    public void test3() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("2s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        });
        thread.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ends");
    }

    @Test
    public void test4() {
        int x = 0x48;
        System.out.println(Integer.toBinaryString(x));
    }

    @Test
    public void test5() {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void test6() {
        Enhancer e = new Enhancer();
        e.setSuperclass(Person.class);
        e.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("目标方法执行前:" + method);
                Object object = methodProxy.invokeSuper(obj, objects);
                System.out.println("目标方法执行后:" + method);
                return object;
            }
        });

        Person person = (Person) e.create();
        System.out.println(person);
    }

    @Test
    public void test7() {
        Map<String, String> transformedBeanNameCache = new ConcurrentHashMap<>();
        transformedBeanNameCache.put("name", "exist");
        String name = transformedBeanNameCache.computeIfAbsent("name", beanName -> beanName);

        System.out.println(name);
        System.out.println(transformedBeanNameCache);

    }

    @Test
    public void test8() {
        ActivityExample example = new ActivityExample();
    }
}
