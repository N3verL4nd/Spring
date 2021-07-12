package com.xiya.mybatis.generator.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Parameter;

import java.util.Iterator;
import java.util.List;

public class PrimaryKeyUtil {
    public static String primaryKey(Interface interfaze, IntrospectedTable introspectedTable) {
        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
        if (introspectedColumns.size() > 1) {
            FullyQualifiedJavaType primaryKeyType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
            interfaze.addImportedType(primaryKeyType);
            return primaryKeyType.getShortName();
        }
        StringBuilder sb = new StringBuilder();
        Iterator<IntrospectedColumn> it = introspectedColumns.iterator();
        if (!it.hasNext()) {
            return sb.toString();
        }
        IntrospectedColumn introspectedColumn = it.next();
        FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
        interfaze.addImportedType(type);
        return new Parameter(type, introspectedColumn.getJavaProperty()).getType().getShortName();
    }
}
