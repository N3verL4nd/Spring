package com.xiya.entity;

import org.springframework.stereotype.Component;

/**
* @file American.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/6/17
*/

@Component
public class American implements Person {
    @Override
    public void sayHello() {
        System.out.println("Hello, I come from America!");
    }
}
