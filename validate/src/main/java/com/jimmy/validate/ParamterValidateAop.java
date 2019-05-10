package com.jimmy.validate;

import com.jimmy.validate.anno.ParamValidate;
import com.jimmy.validate.exception.ValidateException;
import com.jimmy.validate.regular.ValidateRegular;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

/**
 * @author aiden
 * @date 2017/3/16
 */
@Aspect
public class ParamterValidateAop {

    private Map<String, ValidateRegular> validateRegularMap;

    @Around(value = "@annotation(paramValidate)")
    public Object tokenAround(ProceedingJoinPoint joinPoint, ParamValidate paramValidate) throws Throwable {
        Object[] objArray = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        String group = paramValidate.value();
        /**
         * 检查参数
         */
        Annotation[][] annotations = targetMethod.getParameterAnnotations();
        Annotation annotation = null;
        if (annotations != null && annotations.length > 0) {
            for (int i = 0; i < annotations.length; i++) {
                for (int j = 0; j < annotations[i].length; j++) {
                    annotation = annotations[i][j];
                    validate(annotation, objArray[i], group);
                }
            }
        }
        for (Object obj : objArray) {

        }
        return joinPoint.proceed(joinPoint.getArgs());
    }


    private void validate(Annotation annotation, Object obj, String group) {
        Collection<ValidateRegular> validateRegularSet = validateRegularMap.values();
        ValidateRegular validateRegular = null;
        for (ValidateRegular regular : validateRegularSet) {
            if (annotation.getClass().equals(regular.getTClass())) {
                validateRegular = regular;
            }
        }
        if (!validateRegular.validate(annotation, obj)) {
           throw new ValidateException(validateRegular.getMessage(annotation,obj,null));
        }


    }


    public void setValidateRegularMap(Map<String, ValidateRegular> validateRegularMap) {
        this.validateRegularMap = validateRegularMap;
    }
}
