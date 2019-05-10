package com.jimmy.validate.regular;

import com.jimmy.validate.anno.NotNull;
import org.springframework.stereotype.Component;

@Component
public class NotNullRegular implements ValidateRegular<NotNull> {

    @Override
    public boolean validate(NotNull notNull, Object obj) {
        return obj == null;
    }

    @Override
    public String getMessage(NotNull notNull, Object obj, String filedName) {
        return notNull.value().replace("${filedName}", filedName).replace("${value}", String.valueOf(obj));
    }
}
