package com.jimmy.sublimation.sublimation.validate;

import com.jimmy.sublimation.sublimation.validate.regular.ValidateRegular;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
@ConditionalOnBean(
        annotation = {EnableValidate.class}
)
public class ValidateAutoconfiguration {

    @Resource
    private Map<String, ValidateRegular> validateRegularMap;

    @Bean
    public ParamterValidateAop createParamterValidateAop() {
        ParamterValidateAop paramterValidateAop = new ParamterValidateAop();
        paramterValidateAop.setValidateRegularMap(validateRegularMap);
        return paramterValidateAop;
    }

}
