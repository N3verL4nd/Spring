package com.xiya.test;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.xml.ConfigurationParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liguanghui02
 * @date 2021/2/4
 */
public class MyBatisTest {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(MyBatisTest.class.getClassLoader().getResourceAsStream("generatorConfig.xml"));
        Context context = config.getContexts().get(0);
        System.out.println(context);
    }


}
