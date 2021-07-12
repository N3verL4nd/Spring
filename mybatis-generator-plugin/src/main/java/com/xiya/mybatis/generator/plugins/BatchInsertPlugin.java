package com.xiya.mybatis.generator.plugins;

import com.xiya.mybatis.generator.utils.FormatTools;
import com.xiya.mybatis.generator.utils.JavaElementGeneratorTools;
import com.xiya.mybatis.generator.utils.XmlElementGeneratorTools;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.*;
import org.mybatis.generator.codegen.mybatis3.ListUtilities;

import java.util.List;

public class BatchInsertPlugin extends PluginAdapter {
    private static final String METHOD_BATCH_INSERT = "batchInsert";

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType importParam = new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param");
        interfaze.addImportedType(importParam);

        FullyQualifiedJavaType importList = new FullyQualifiedJavaType("java.util.List");
        interfaze.addImportedType(importList);

        FullyQualifiedJavaType paramListType = FullyQualifiedJavaType.getNewListInstance();
        paramListType.addTypeArgument(introspectedTable.getRules().calculateAllFieldsClass());
        Method mBatchInsert = JavaElementGeneratorTools.generateMethod(METHOD_BATCH_INSERT, JavaVisibility.DEFAULT, FullyQualifiedJavaType.getIntInstance(), new Parameter(paramListType, "list", "@Param(\"list\")"));
        FormatTools.addMethodWithBestPosition(interfaze, mBatchInsert);
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        // 1. batchInsert
        XmlElement batchInsertEle = new XmlElement("insert");
        batchInsertEle.addAttribute(new Attribute("id", METHOD_BATCH_INSERT));

        // 参数类型
        batchInsertEle.addAttribute(new Attribute("parameterType", "map"));

        // 使用JDBC的getGenereatedKeys方法获取主键并赋值到keyProperty设置的领域模型属性中。所以只支持MYSQL和SQLServer
        XmlElementGeneratorTools.useGeneratedKeys(batchInsertEle, introspectedTable);

        batchInsertEle.addElement(new TextElement("insert into " + introspectedTable.getFullyQualifiedTableNameAtRuntime()));
        for (Element element : XmlElementGeneratorTools.generateKeys(ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns()), true)) {
            batchInsertEle.addElement(element);
        }

        // 添加foreach节点
        XmlElement foreachElement = new XmlElement("foreach");
        foreachElement.addAttribute(new Attribute("collection", "list"));
        foreachElement.addAttribute(new Attribute("item", "item"));
        foreachElement.addAttribute(new Attribute("separator", ","));

        for (Element element : XmlElementGeneratorTools.generateValues(ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns()), "item.")) {
            foreachElement.addElement(element);
        }

        // values 构建
        batchInsertEle.addElement(new TextElement("values"));
        batchInsertEle.addElement(foreachElement);
        document.getRootElement().addElement(batchInsertEle);
        return true;
    }
}
