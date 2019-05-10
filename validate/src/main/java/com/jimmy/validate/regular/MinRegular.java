package com.jimmy.validate.regular;

import com.jimmy.common.utils.ClassUtils;
import com.jimmy.validate.anno.Min;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MinRegular implements ValidateRegular<Min> {

    @Override
    public boolean validate(Min min, Object obj) {
        if (obj == null) {
            return true;
        }
        if (ClassUtils.isNumber(obj)) {
            if (((Collection) obj).size() == 0) {
                Double number = ClassUtils.getNumber(obj);
                return (number > min.min());
            }
        }
        return false;
    }

    @Override
    public String getMessage(Min min, Object obj, String filedName) {
        return min.value().replace("${filedName}", filedName).replace("${value}", String.valueOf(obj)).replace("${max}", String.valueOf(min.min()));
    }
}
