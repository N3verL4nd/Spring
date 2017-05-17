package com.xiya.entity;

import org.springframework.stereotype.Component;

/**
 * Created by N3verL4nd on 2017/4/9.
 */
@Component
public class Chinese implements People {
    @Override
    public void sayHello() {
        System.out.println("你好，我来自中国！");
    }
}
