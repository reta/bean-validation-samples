package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

@ConstraintComposition( CompositionType.OR )
@Pattern(regexp = "K")
@Size(min = 2, max = 10)
@ReportAsSingleViolation
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention( RetentionPolicy.RUNTIME )
@Constraint(validatedBy = { })
public @interface PatternOrSize {
	public abstract String message() default "OR";
	public abstract Class<?>[] groups() default { };
	public abstract Class< ? extends Payload >[] payload() default { };
}
