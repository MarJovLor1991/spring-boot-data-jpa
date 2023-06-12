package com.proyectoClase.app.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BooleanValueValidator implements ConstraintValidator<ValidBooleanValue, Boolean> {
    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value != null;
    }
}
