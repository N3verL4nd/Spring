package com.xiya.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liguanghui02
 * @date 2020/11/7
 */
@Service
public class PersonService {
    private Logger log = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private Person person;

    public Person getPerson() {
        log.info("PersonService getPerson starts.");
        return person;
    }
}
