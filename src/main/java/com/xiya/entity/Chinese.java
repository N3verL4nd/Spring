package com.xiya.entity;

import org.springframework.stereotype.Component;

/**
* @file Chinese.java
* @CopyRight (C) http://blog.csdn.net/x_iya
* @Description
* @author N3verL4nd
* @email lgh1992314@qq.com
* @date 2017/6/17
*/

@Component
public class Chinese implements Person {
    @Override
    public void sayHello() {
        System.out.println("你好，我来自中国！");
    }
}
