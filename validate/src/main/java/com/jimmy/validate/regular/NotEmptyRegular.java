package com.jimmy.validate.regular;

import com.jimmy.common.utils.ClassUtils;
import com.jimmy.common.utils.StringUtils;
import com.jimmy.validate.anno.NotEmpty;
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
            return false;
        }
        return true;
    }

    @Override
    public String getMessage(NotEmpty notEmpty, Object obj, String filedName) {
        return notEmpty.value().replace("${filedName}", filedName).replace("${value}", String.valueOf(obj));
    }
}
