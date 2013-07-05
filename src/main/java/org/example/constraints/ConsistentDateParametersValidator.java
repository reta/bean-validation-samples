package org.example.constraints;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

@SupportedValidationTarget( value = ValidationTarget.PARAMETERS )
public class ConsistentDateParametersValidator implements ConstraintValidator<ConsistentDateParameters, Object[]> {
	@Override
    public void initialize(ConsistentDateParameters constraintAnnotation) {
        //nothing to do
    }

    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if ( value.length != 2 ) {
            throw new IllegalArgumentException( "Unexpected method signature" );
        }

        if ( value[0] == null || value[1] == null ) {
            return true;
        }

        if ( !( value[0] instanceof Date ) || !( value[1] instanceof Date ) ) {
            throw new IllegalArgumentException( "Unexpected method signature" );
        }

        return ( ( Date ) value[0] ).before( ( Date ) value[1] );
    }
}
