package com.jimmy.sublimation.validate.regular;

import com.jimmy.sublimation.sublimation.common.utils.ClassUtils;
import com.jimmy.sublimation.sublimation.common.utils.StringUtils;
import com.jimmy.sublimation.validate.anno.NotEmpty;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class NotEmptyRegular implements ValidateRegular<NotEmpty> {

    @Override
    public boolean validate(NotEmpty notEmpty, Object obj) {
        if (obj == null) {
            return false;
        }
        if (ClassUtils.isCollection(obj)) {
            if (((Collection) obj).size() == 0) {
                return false;
            }
        } else if (ClassUtils.isString(obj)) {
            return StringUtils.isNotBlank((String) obj);
        } else if (ClassUtils.isArray(obj)) {
            Object[] objectArray = (Object[]) obj;
            if (objectArray.length == 0) {
                return false;
            }
            for (Object tempObj : objectArray) {
                if (tempObj != null) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public String getMessage(NotEmpty notEmpty, Object obj, String fieldName) {
        return notEmpty.value().replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj));
    }
}
