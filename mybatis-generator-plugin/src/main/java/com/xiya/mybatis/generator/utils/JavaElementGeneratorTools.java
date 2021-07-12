package com.xiya.mybatis.generator.utils;

import org.mybatis.generator.api.dom.java.*;

public class JavaElementGeneratorTools {
    public static Field generateField(String fieldName, JavaVisibility visibility, FullyQualifiedJavaType javaType, String initString) {
        Field field = new Field(fieldName, javaType);
        field.setVisibility(visibility);
        if (initString != null) {
            field.setInitializationString(initString);
        }
        return field;
    }

    public static Method generateMethod(String methodName, JavaVisibility visibility, FullyQualifiedJavaType returnType, Parameter... parameters) {
        Method method = new Method(methodName);
        method.setVisibility(visibility);
        method.setReturnType(returnType);
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                method.addParameter(parameter);
            }
        }
        return method;
    }

    public static Method generateMethodBody(Method method, String... bodyLines) {
        if (bodyLines != null) {
            for (String bodyLine : bodyLines) {
                method.addBodyLine(bodyLine);
            }
        }
        return method;
    }

    public static Method generateSetterMethod(Field field) {
        return generateMethodBody(generateMethod("set" + FormatTools.upFirstChar(field.getName()), JavaVisibility.PUBLIC, null, new Parameter(field.getType(), field.getName())), "this." + field.getName() + " = " + field.getName() + ";");
    }

    public static Method generateGetterMethod(Field field) {
        return generateMethodBody(generateMethod("get" + FormatTools.upFirstChar(field.getName()), JavaVisibility.PUBLIC, field.getType()), "return this." + field.getName() + ";");
    }
}
