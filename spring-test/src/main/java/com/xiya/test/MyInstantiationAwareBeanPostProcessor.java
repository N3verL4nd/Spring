package com.xiya.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Service;

/**
 * 自定义处理器
 *
 * @author
 */
@Service
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    /**
     * BeanPostProcessor接口中的方法
     * 在Bean的自定义初始化方法之前执行
     * Bean对象已经存在了
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println(">>postProcessBeforeInitialization");
        }
        return bean;
    }

    /**
     * BeanPostProcessor接口中的方法
     * 在Bean的自定义初始化方法执行完成之后执行
     * Bean对象已经存在了
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("<<postProcessAfterInitialization");
        }
        return bean;
    }

    /**
     * InstantiationAwareBeanPostProcessor中自定义的方法
     * 在方法实例化之前执行  Bean对象还没有
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("--->postProcessBeforeInstantiation");
            return new Person("postProcessBeforeInstantiation", 30);
        }
        return null;
    }

    /**
     * InstantiationAwareBeanPostProcessor中自定义的方法
     * 在方法实例化之后执行  Bean对象已经创建出来了
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("<---postProcessAfterInstantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (beanName.equals("person")) {
            System.out.println("<---postProcessPropertyValues--->");
        }
        return pvs;
    }
}
