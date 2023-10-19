package com.ms.util.anotation;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<Email, String> {

    @Value("${validate.email.regex}")
    private String emailRegex;
    @Value("${validate.email.message}")
    private String emailMessage;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        context.buildConstraintViolationWithTemplate(emailMessage).addConstraintViolation();
        return Pattern.matches(emailRegex, value);
    }
}
