package com.jimmy.sublimation.sublimation.validate.regular;

import com.jimmy.sublimation.sublimation.common.utils.ClassUtils;
import com.jimmy.sublimation.sublimation.validate.anno.Min;
import org.springframework.stereotype.Component;

@Component
public class MinRegular implements ValidateRegular<Min> {

    @Override
    public boolean validate(Min min, Object obj) {
        if (obj == null) {
            return true;
        }
        if (ClassUtils.isNumber(obj)) {
            Double number = ClassUtils.getNumber(obj);
            return (number > min.min());
        }
        return false;
    }

    @Override
    public String getMessage(Min min, Object obj, String fieldName) {
        return min.value().replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj)).replace("${max}", String.valueOf(min.min()));
    }
}
