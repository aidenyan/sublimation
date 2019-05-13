package com.jimmy.validate.regular;

import com.jimmy.common.utils.ClassUtils;
import com.jimmy.validate.anno.MaxSize;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MaxSizeRegular implements ValidateRegular<MaxSize> {

    @Override
    public boolean validate(MaxSize maxSize, Object obj) {
        if (obj == null) {
            return false;
        }
        if (ClassUtils.isArray(obj)) {
            Object[] objArray = (Object[]) obj;
            if (objArray.length <= maxSize.maxSize()) {
                return true;
            }
        } else if (obj instanceof Collection) {
            if (((Collection) obj).size() <= maxSize.maxSize()) {
                return true;
            }
        } else if (obj instanceof String) {
            if (((String) obj).length() <= maxSize.maxSize()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMessage(MaxSize maxSize, Object obj, String fieldName) {
        return maxSize.value().replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj)).replace("${maxSize}", String.valueOf(maxSize.maxSize()));
    }
}
