package org.example;

import java.math.BigDecimal;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class Starter {
	public static void main( String[] args ) throws NoSuchMethodException, SecurityException {
		final ValidatorUtil validatorUtil = new ValidatorUtil();
		
        validatorUtil.add( Person.class, "firstName" );
		validatorUtil.find( Person.class, "firstName" );
		validatorUtil.find( Department.class, "persons" );

		try {
		    final Person person = new Person( new Department( "Dept" ) );
		    
		    validatorUtil.validate( 
		        Person.class.getMethod( "saveItem", Person.class, BigDecimal.class ), 
		        new Object[] { person, new BigDecimal( 0 ) }, 
		        person
		    );
		    
			validatorUtil.validate( new Person( new Department( "D" ) ) );
		} catch( ConstraintViolationException ex ) {
			for( final ConstraintViolation< ? > violation: ex.getConstraintViolations() ) {
				System.out.println( String.format(
					"Instance: %s, property: %s, with message: %s",
					violation.getRootBean(),
					violation.getPropertyPath(),
					violation.getMessage()
				) );
			}
		}		
	}

}
