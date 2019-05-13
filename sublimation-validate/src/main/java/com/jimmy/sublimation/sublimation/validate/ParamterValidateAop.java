package com.jimmy.sublimation.sublimation.validate;

import com.jimmy.sublimation.sublimation.common.utils.ClassUtils;
import com.jimmy.sublimation.sublimation.common.utils.StringUtils;
import com.jimmy.sublimation.sublimation.validate.anno.ParamValidate;
import com.jimmy.sublimation.sublimation.validate.exception.ValidateException;
import com.jimmy.sublimation.sublimation.validate.exception.ValidateRegularException;
import com.jimmy.sublimation.sublimation.validate.regular.ValidateRegular;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
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
        String[] paramNames = ((CodeSignature) joinPoint.getStaticPart().getSignature()).getParameterNames();
        if (objArray == null || objArray.length == 0) {
            return joinPoint.proceed(joinPoint.getArgs());
        }
        Map<String, Object> paremterMap = new HashMap<>();
        for (int i = 0; i < objArray.length; i++) {
            paremterMap.put(paramNames[i], objArray[i]);
        }
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
                    validate(annotation, objArray[i], group, paramNames[i]);
                }
            }
        }
        for (String fileName : paremterMap.keySet()) {
            validate(group, fileName, paremterMap.get(fileName));
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }

    /**
     * 检查如果参数是对象的情况下的内部验证
     *
     * @param group
     * @param preFileName
     * @param obj
     */
    private void validate(String group, String preFileName, Object obj) throws IllegalAccessException {
        if (obj instanceof HttpServletResponse
                || obj instanceof HttpServletRequest) {
            return;
        }
        if (obj == null || ClassUtils.isBaseClass(obj)) {
            return;
        }
        if (ClassUtils.isArray(obj)) {
            Object[] objArray = (Object[]) obj;
            obj = Arrays.asList(objArray);
        }
        if (obj instanceof Collection) {
            int i = 0;
            for (Object targetObj : (Collection) obj) {
                validate(group, preFileName + "[" + i + "].", targetObj);
            }
            return;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        if (fields.length == 0) {
            return;
        }
        Object fieldObj = null;
        for (Field field : fields) {
            field.setAccessible(true);
            fieldObj = field.get(obj);
            Annotation[] annotations = field.getAnnotations();
            if (annotations != null && annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    validate(annotation, fieldObj, group, preFileName + "." + field.getName());
                }
            }
            validate(group, preFileName + "." + field.getName(), fieldObj);
        }
    }


    private void validate(Annotation annotation, Object obj, String group, String fileName) {
        Collection<ValidateRegular> validateRegularSet = validateRegularMap.values();
        ValidateRegular validateRegular = null;
        for (ValidateRegular regular : validateRegularSet) {
            if (annotation.annotationType().getName().equals(regular.getClassName())) {
                validateRegular = regular;
            }
        }
        if (validateRegular == null) {
            return;
        }
        if (StringUtils.isNotBlank(group) && !group.equals(getGroupName(annotation))) {
            return;
        }

        if (!validateRegular.validate(annotation, obj)) {
            throw new ValidateException(validateRegular.getMessage(annotation, obj, fileName));
        }
    }

    public void setValidateRegularMap(Map<String, ValidateRegular> validateRegularMap) {
        this.validateRegularMap = validateRegularMap;
    }

    /**
     * 获取分组类的名称
     *
     * @param annotation
     * @return
     */
    private String getGroupName(Annotation annotation) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
            declaredField.setAccessible(true);
            Map memberValues = (Map) declaredField.get(invocationHandler);
            if (memberValues == null) {
                throw new ValidateRegularException("get proxy is error");
            }
            return (String) memberValues.get("group");
        } catch (IllegalAccessException e) {
            throw new ValidateRegularException(e, "get proxy is error");
        } catch (NoSuchFieldException e) {
            throw new ValidateRegularException(e, "get proxy is error");
        }
    }
}
