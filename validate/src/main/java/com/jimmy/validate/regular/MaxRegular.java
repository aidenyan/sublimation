package com.jimmy.validate.regular;

import com.jimmy.common.utils.ClassUtils;
import com.jimmy.validate.anno.Max;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MaxRegular implements ValidateRegular<Max> {

    @Override
    public boolean validate(Max max, Object obj) {
        if (obj == null) {
            return true;
        }
        if (ClassUtils.isNumber(obj)) {
            if (((Collection) obj).size() == 0) {
               Double number = ClassUtils.getNumber(obj);
                return (number < max.max());
            }
        }
        return false;
    }

    @Override
    public String getMessage(Max max, Object obj, String filedName) {
        return max.value().replace("${filedName}", filedName).replace("${value}", String.valueOf(obj)).replace("${max}", String.valueOf(max.max()));
    }
}
