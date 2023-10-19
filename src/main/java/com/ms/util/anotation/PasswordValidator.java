package com.ms.util.anotation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;


public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Value("${validate.password.uppercase.regex}")
    private String uppercaseRegex;

    @Value("${validate.password.uppercase.message}")
    private String uppercaseMessage;

    @Value("${validate.password.digit.regex}")
    private String digitRegex;

    @Value("${validate.password.digit.message}")
    private String digitMessage;

    @Value("${validate.password.length.regex}")
    private String lengthRegex;

    @Value("${validate.password.length.message}")
    private String lengthMessage;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        var errorMessage = new ArrayList<String>();


       if(!value.matches(uppercaseRegex))
          errorMessage.add(uppercaseMessage);
       if(!value.matches(digitRegex))
            errorMessage.add(digitMessage);
       if(!value.matches(lengthRegex))
            errorMessage.add(lengthMessage);


        if(errorMessage.isEmpty())
            return true;

        context.buildConstraintViolationWithTemplate(String.join(" , " , errorMessage)).addConstraintViolation();

        return false;
    }
}