package com.xiya.entity;

import org.springframework.stereotype.Component;

/**
 * Created by N3verL4nd on 2017/4/9.
 */
@Component
public class American implements People {
    @Override
    public void sayHello() {
        System.out.println("Hello, I come form America!");
    }
}
