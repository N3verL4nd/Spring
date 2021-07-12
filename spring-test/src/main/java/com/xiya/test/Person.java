package com.xiya.test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author N3verL4nd
 * @date 2020/11/7
 */

public class Person implements InitializingBean, DisposableBean {
    private String name;
    private int age;

    public Person() {
        System.out.println("Person 被实例化");
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person afterPropertiesSet");
    }

    public void start() {
        System.out.println("Person start");
    }

    public void end() {
        System.out.println("Person end");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Person destroy");
    }
}
