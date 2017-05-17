package com.xiya.service;

import com.xiya.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by N3verL4nd on 2017/5/17.
 */
@Service
public class PeopleManager {
    @Autowired
    @Qualifier("chinese")
//    @Qualifier("american")
    private People people;

    public void sayHello() {
        people.sayHello();
    }

}
