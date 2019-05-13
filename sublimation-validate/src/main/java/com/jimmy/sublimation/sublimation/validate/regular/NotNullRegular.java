package com.jimmy.sublimation.sublimation.validate.regular;

import com.jimmy.sublimation.sublimation.validate.anno.NotNull;
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
