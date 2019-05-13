package com.jimmy.sublimation.sublimation.validate.regular;

import com.jimmy.sublimation.sublimation.common.utils.ClassUtils;
import com.jimmy.sublimation.sublimation.validate.anno.MinSize;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MinSizeRegular implements ValidateRegular<MinSize> {

    @Override
    public boolean validate(MinSize minSize, Object obj) {
        if (obj == null) {
            return false;
        }
        if (ClassUtils.isArray(obj)) {
              Object[] objArray= (Object[]) obj;
              if(objArray.length>=minSize.minSize()){
                  return true;
              }
        }else if(obj instanceof Collection){
            if(((Collection)obj).size()>=minSize.minSize()){
                return true;
            }
        }else if(obj instanceof String){
            if(((String)obj).length()>=minSize.minSize()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMessage(MinSize minSize, Object obj, String fieldName) {
        return minSize.value().replace("${fieldName}", fieldName).replace("${value}", String.valueOf(obj)).replace("${minSize}", String.valueOf(minSize.minSize()));
    }
}
