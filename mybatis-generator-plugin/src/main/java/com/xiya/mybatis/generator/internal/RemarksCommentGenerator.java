package com.xiya.mybatis.generator.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

public class RemarksCommentGenerator extends DefaultCommentGenerator {
    private boolean addRemarkComments;

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.addRemarkComments) {
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" *");
            topLevelClass.addJavaDocLine(" *   表名: " + introspectedTable.getFullyQualifiedTable());
            String remarks = introspectedTable.getRemarks();
            if (StringUtility.stringHasValue(remarks)) {
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                for (String remarkLine : remarkLines) {
                    topLevelClass.addJavaDocLine(" *   " + remarkLine);
                }
            }
            topLevelClass.addJavaDocLine(" */");
        }
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (this.addRemarkComments) {
            String comments = introspectedColumn.getActualColumnName();
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" *   字段: " + comments);
            String remarks = introspectedColumn.getRemarks();
            if (StringUtility.stringHasValue(remarks)) {
                String[] remarkLines = remarks.split(System.getProperty("line.separator"));
                String remarks2 = " *   说明: ";
                for (String remarkLine : remarkLines) {
                    field.addJavaDocLine(remarks2 + remarkLine);
                    remarks2 = " *      ";
                }
            }
            field.addJavaDocLine(" */");
        }
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
        RemarksCommentGenerator.super.addConfigurationProperties(properties);
    }
}
