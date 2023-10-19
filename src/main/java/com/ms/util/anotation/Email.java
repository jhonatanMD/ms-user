package com.ms.util.anotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface Email {

    String message() default "";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
