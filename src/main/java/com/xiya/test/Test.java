package com.xiya.test;

import com.xiya.service.PeopleManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by N3verL4nd on 2017/5/17.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans-helloworld.xml");
        PeopleManager peopleManager = context.getBean(PeopleManager.class);
        peopleManager.sayHello();
    }
}
