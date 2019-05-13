package com.jimmy.sublimation.validate.regular;

import com.jimmy.sublimation.sublimation.common.utils.StringUtils;
import com.jimmy.sublimation.validate.exception.ValidateRegularException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.Map;

public interface ValidateRegular<T> {

    boolean validate(T t, Object obj);

    default String getMessage(T t, Object obj, String fieldName) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(t);
            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
            declaredField.setAccessible(true);
            Map memberValues = (Map) declaredField.get(invocationHandler);
            if (memberValues == null) {
                throw new ValidateRegularException("get proxy is error");
            }
            if (StringUtils.isBlank((String) memberValues.get("value"))) {
                return ((String) memberValues.get("value")).replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj));
            } else {
                return "";
            }
        } catch (IllegalAccessException e) {
            throw new ValidateRegularException(e, "get proxy is error");
        } catch (NoSuchFieldException e) {
            throw new ValidateRegularException(e, "get proxy is error");
        }
    }

    default Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }

    default String getClassName() {
        String className = getTClass().getName();
        return className;
    }
}
