package com.xiya.mybatis.generator.utils;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.config.GeneratedKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class XmlElementGeneratorTools {
    public static void useGeneratedKeys(XmlElement element, IntrospectedTable introspectedTable) {
        useGeneratedKeys(element, introspectedTable, null);
    }

    private static void useGeneratedKeys(XmlElement element, IntrospectedTable introspectedTable, String prefix) {
        IntrospectedColumn introspectedColumn;
        GeneratedKey gk = introspectedTable.getGeneratedKey();
        if (gk != null && (introspectedColumn = IntrospectedTableTools.safeGetColumn(introspectedTable, gk.getColumn())) != null) {
            element.addAttribute(new Attribute("useGeneratedKeys", "true"));
            StringBuilder sb = new StringBuilder();
            if (prefix == null) {
                prefix = "";
            }
            element.addAttribute(new Attribute("keyProperty", sb.append(prefix).append(introspectedColumn.getJavaProperty()).toString()));
            element.addAttribute(new Attribute("keyColumn", introspectedColumn.getActualColumnName()));
        }
    }

    public static List<Element> generateKeys(List<IntrospectedColumn> columns, boolean bracket) {
        return generateCommColumns(columns, null, bracket, 1);
    }

    public static List<Element> generateValues(List<IntrospectedColumn> columns, String prefix) {
        return generateValues(columns, prefix, true);
    }

    public static List<Element> generateValues(List<IntrospectedColumn> columns, String prefix, boolean bracket) {
        return generateCommColumns(columns, prefix, bracket, 2);
    }

    private static List<Element> generateCommColumns(List<IntrospectedColumn> columns, String prefix, boolean bracket, int type) {
        return generateCommColumns(columns, prefix, bracket, type, false);
    }

    private static List<Element> generateCommColumns(List<IntrospectedColumn> columns, String prefix, boolean bracket, int type, boolean upsert) {
        List<Element> list = new ArrayList<>();
        if (!upsert || !hasIdentityAndGeneratedAlwaysColumns(columns)) {
            StringBuilder sb = new StringBuilder(bracket ? "(" : "");
            Iterator<IntrospectedColumn> columnIterator = columns.iterator();
            while (columnIterator.hasNext()) {
                IntrospectedColumn introspectedColumn = columnIterator.next();
                switch (type) {
                    case 1:
                        sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                        break;
                    case 2:
                        sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, prefix));
                        break;
                }
                if (columnIterator.hasNext()) {
                    sb.append(", ");
                }
                if (type != 1 && type != 2) {
                    list.add(new TextElement(sb.toString()));
                    sb.setLength(0);
                } else if (sb.length() > 80) {
                    list.add(new TextElement(sb.toString()));
                    sb.setLength(0);
                    OutputUtilities.xmlIndent(sb, 1);
                }
            }
            if (sb.length() <= 0 && !bracket) {
                return list;
            }
            list.add(new TextElement(sb.append(bracket ? ")" : "").toString()));
            return list;
        }
        XmlElement trimEle = generateTrim(bracket);
        for (IntrospectedColumn introspectedColumn2 : columns) {
            if (introspectedColumn2.isGeneratedAlways() || introspectedColumn2.isIdentity()) {
                generateSelectiveToTrimEleTo(trimEle, introspectedColumn2, prefix, type);
            } else {
                generateSelectiveCommColumnTo(trimEle, introspectedColumn2, prefix, type);
            }
        }
        return Arrays.asList(trimEle);
    }

    private static XmlElement generateTrim(boolean bracket) {
        XmlElement trimEle = new XmlElement("trim");
        if (bracket) {
            trimEle.addAttribute(new Attribute("prefix", "("));
            trimEle.addAttribute(new Attribute("suffix", ")"));
            trimEle.addAttribute(new Attribute("suffixOverrides", ","));
        } else {
            trimEle.addAttribute(new Attribute("suffixOverrides", ","));
        }
        return trimEle;
    }

    private static void generateSelectiveToTrimEleTo(XmlElement trimEle, IntrospectedColumn introspectedColumn, String prefix, int type) {
        if (type == 3 || (!introspectedColumn.isSequenceColumn() && !introspectedColumn.getFullyQualifiedJavaType().isPrimitive())) {
            XmlElement eleIf = new XmlElement("if");
            eleIf.addAttribute(new Attribute("test", introspectedColumn.getJavaProperty(prefix) + " != null"));
            generateSelectiveCommColumnTo(eleIf, introspectedColumn, prefix, type);
            trimEle.addElement(eleIf);
            return;
        }
        generateSelectiveCommColumnTo(trimEle, introspectedColumn, prefix, type);
    }

    private static void generateSelectiveCommColumnTo(XmlElement element, IntrospectedColumn introspectedColumn, String prefix, int type) {
        switch (type) {
            case 1:
                element.addElement(new TextElement(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn) + ","));
                return;
            case 2:
                element.addElement(new TextElement(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, prefix) + ","));
                return;
            default:
                return;
        }
    }

    private static boolean hasIdentityAndGeneratedAlwaysColumns(List<IntrospectedColumn> columns) {
        for (IntrospectedColumn column : columns) {
            if (column.isGeneratedAlways() || column.isIdentity()) {
                return true;
            }
        }
        return false;
    }
}
