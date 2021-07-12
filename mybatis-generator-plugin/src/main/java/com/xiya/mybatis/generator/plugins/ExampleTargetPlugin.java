package com.xiya.mybatis.generator.plugins;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

import java.util.List;
import java.util.Properties;

@Slf4j
public class ExampleTargetPlugin extends PluginAdapter {
    public static final String PRO_TARGET_PACKAGE = "targetPackage";
    private String targetPackage;



    @Override
    public boolean validate(List<String> warnings) {
        Properties properties = getProperties();
        this.targetPackage = properties.getProperty(ExampleTargetPlugin.PRO_TARGET_PACKAGE);
        if (this.targetPackage == null) {
            warnings.add("请配置 ExampleTargetPlugin 插件的目标包名(targetPackage)!");
            return false;
        }
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        super.initialized(introspectedTable);
        String exampleType = introspectedTable.getExampleType();
        // context 标签
        Context context = getContext();
        // javaModelGenerator 标签
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String targetPackage = configuration.getTargetPackage();
        String newExampleType = exampleType.replace(targetPackage, this.targetPackage);
        introspectedTable.setExampleType(newExampleType);
        log.info("更改 Example 文件所在位置 --> 原始位置: {}, 目标位置: {}", targetPackage, this.targetPackage);
    }
}
