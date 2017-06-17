package com.xiya.service;

import com.xiya.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @file PersonManager.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/6/17
*/

@Service
public class PersonManager {
    private Person people;

    /* 构造器注入
     * 使用：
     * @Autowired
     * @Qualifier("chinese")
     * 或者使用：
     * @Resource(name = "chinese")
     *
     */

    @Resource(name = "chinese")
    public void setPeople(Person people) {
        this.people = people;
    }

    public void sayHello() {
        people.sayHello();
    }

}
