package com.jimmy.common.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

public class ClassUtils extends org.apache.commons.lang3.ClassUtils {

    public static boolean isCollection(Object obj) {
        return obj instanceof Collection;
    }

    public static boolean isArray(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.getClass().isArray();
    }

    public static boolean isNumber(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj instanceof Number
                || obj instanceof Double
                || obj instanceof Float
                || obj instanceof BigInteger
                || obj instanceof BigDecimal
                || obj instanceof Long
                || obj instanceof Integer;
    }

    public static boolean isBaseClass(Object obj) {
        if (obj == null) {
            return false;
        }
        return isNumber(obj)
                || obj instanceof Character
                || obj instanceof Byte
                || obj instanceof Boolean
                || obj instanceof String
                || obj instanceof Short;
    }

    public static Double getNumber(Object obj) {
        if (isNumber(obj)) {
            return null;
        } else if (obj instanceof Number) {
            return ((Number) obj).doubleValue();
        } else if (obj instanceof Double) {
            return ((Double) obj).doubleValue();
        } else if (obj instanceof Float) {
            return ((Float) obj).doubleValue();
        } else if (obj instanceof BigInteger) {
            return ((BigInteger) obj).doubleValue();
        } else if (obj instanceof BigDecimal) {
            return ((BigDecimal) obj).doubleValue();
        } else if (obj instanceof Long) {
            return ((Long) obj).doubleValue();
        } else if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        }
        return null;
    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }


    public static void main(String[]arg){
        String[] temp=new String[5];
        Object[] obj=temp;
        System.out.println(Arrays.asList(obj).size());
    }
}
