package com.jimmy.validate.regular;

import java.lang.reflect.ParameterizedType;

public interface ValidateRegular<T> {

    boolean validate(T t, Object obj);

    String getMessage(T t, Object obj, String filedName);

    default Class<T> getTClass() {
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
