package com.proyectoClase.app.util.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BooleanValueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBooleanValue {
    String message() default "El valor debe ser true o false";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

