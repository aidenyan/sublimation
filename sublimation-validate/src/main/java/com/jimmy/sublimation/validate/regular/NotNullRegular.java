package com.jimmy.sublimation.validate.regular;

import com.jimmy.sublimation.validate.anno.NotNull;
import org.springframework.stereotype.Component;

@Component
public class NotNullRegular implements ValidateRegular<NotNull> {

    @Override
    public boolean validate(NotNull notNull, Object obj) {
        return obj != null;
    }

    @Override
    public String getMessage(NotNull notNull, Object obj, String fieldName) {
        return notNull.value().replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj));
    }
}
