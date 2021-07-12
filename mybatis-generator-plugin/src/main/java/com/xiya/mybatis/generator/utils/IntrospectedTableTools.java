package com.xiya.mybatis.generator.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.internal.util.StringUtility;

class IntrospectedTableTools {
    IntrospectedTableTools() {
    }

    static IntrospectedColumn safeGetColumn(IntrospectedTable introspectedTable, String columnName) {
        columnName = columnName.trim();
        String beginningDelimiter = introspectedTable.getContext().getBeginningDelimiter();
        if (StringUtility.stringHasValue(beginningDelimiter)) {
            columnName = columnName.replaceFirst("^" + beginningDelimiter, "");
        }
        String endingDelimiter = introspectedTable.getContext().getEndingDelimiter();
        if (StringUtility.stringHasValue(endingDelimiter)) {
            columnName = columnName.replaceFirst(endingDelimiter + "$", "");
        }
        return introspectedTable.getColumn(columnName);
    }
}
