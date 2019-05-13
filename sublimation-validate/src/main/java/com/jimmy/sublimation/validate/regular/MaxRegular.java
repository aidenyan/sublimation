package com.jimmy.sublimation.validate.regular;

import com.jimmy.sublimation.common.utils.ClassUtils;
import com.jimmy.sublimation.validate.anno.Max;
import org.springframework.stereotype.Component;

@Component
public class MaxRegular implements ValidateRegular<Max> {

    @Override
    public boolean validate(Max max, Object obj) {
        if (obj == null) {
            return true;
        }
        if (ClassUtils.isNumber(obj)) {
            Double number = ClassUtils.getNumber(obj);
            return (number < max.max());
        }
        return false;
    }

    @Override
    public String getMessage(Max max, Object obj, String fieldName) {
        return max.value().replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj)).replace("${max}", String.valueOf(max.max()));
    }
}
