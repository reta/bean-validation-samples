package org.example.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.CrossParameterConstraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@CrossParameterConstraint(validatedBy = ConsistentDateParametersValidator.class)
@Documented
public @interface ConsistentDateParameters {
    String message() default "{ConsistentDateParameters.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}