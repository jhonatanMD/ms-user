package com.ms.util.anotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {

    String message() default "";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
