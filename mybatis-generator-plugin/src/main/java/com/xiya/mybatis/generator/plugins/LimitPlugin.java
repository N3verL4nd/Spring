package com.xiya.mybatis.generator.plugins;

import com.xiya.mybatis.generator.utils.FormatTools;
import com.xiya.mybatis.generator.utils.JavaElementGeneratorTools;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;

public class LimitPlugin extends PluginAdapter {
    private static final int DEFAULT_START_PAGE = 0;
    private static final String PRO_START_PAGE = "startPage";
    private int startPage;

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        String startPage = getProperties().getProperty(LimitPlugin.PRO_START_PAGE);
        if (StringUtility.stringHasValue(startPage)) {
            this.startPage = Integer.parseInt(startPage);
        } else {
            this.startPage = DEFAULT_START_PAGE;
        }
        super.initialized(introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();
        Field offsetField = JavaElementGeneratorTools.generateField("offset", JavaVisibility.PROTECTED, integerWrapper, null);
        topLevelClass.addField(offsetField);

        Field rowsField = JavaElementGeneratorTools.generateField("rows", JavaVisibility.PROTECTED, integerWrapper, null);
        topLevelClass.addField(rowsField);

        Method mSetOffset = JavaElementGeneratorTools.generateSetterMethod(offsetField);
        FormatTools.addMethodWithBestPosition(topLevelClass, mSetOffset);

        Method mGetOffset = JavaElementGeneratorTools.generateGetterMethod(offsetField);
        FormatTools.addMethodWithBestPosition(topLevelClass, mGetOffset);

        Method mSetRows = JavaElementGeneratorTools.generateSetterMethod(rowsField);
        FormatTools.addMethodWithBestPosition(topLevelClass, mSetRows);

        Method mGetRows = JavaElementGeneratorTools.generateGetterMethod(rowsField);
        FormatTools.addMethodWithBestPosition(topLevelClass, mGetRows);
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        generateLimitElement(element);
        return true;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        generateLimitElement(element);
        return true;
    }


    private void generateLimitElement(XmlElement element) {
        XmlElement ifLimitNotNullElement = new XmlElement("if");
        ifLimitNotNullElement.addAttribute(new Attribute("test", "rows != null"));
        XmlElement ifOffsetNotNullElement = new XmlElement("if");
        ifOffsetNotNullElement.addAttribute(new Attribute("test", "offset != null"));
        ifOffsetNotNullElement.addElement(new TextElement("limit ${offset}, ${rows}"));
        ifLimitNotNullElement.addElement(ifOffsetNotNullElement);
        XmlElement ifOffsetNullElement = new XmlElement("if");
        ifOffsetNullElement.addAttribute(new Attribute("test", "offset == null"));
        ifOffsetNullElement.addElement(new TextElement("limit ${rows}"));
        ifLimitNotNullElement.addElement(ifOffsetNullElement);
        element.addElement(ifLimitNotNullElement);
    }
}
