package com.unicauca.sga.testService.Infrastructure.Controllers.Validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = MaxFileSizeValidator.class)
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface MaxFileSize {
    String message() default "El archivo supera el tamaño máximo permitido";

    long value(); // bytes

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
