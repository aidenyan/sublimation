package com.jimmy.validate;

import com.jimmy.validate.regular.ValidateRegular;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
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
