package org.example;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class Starter {
	public static void main( String[] args ) {
		new ValidatorUtil().add( Person.class, "firstName" );
		new ValidatorUtil().find( Person.class, "firstName" );
		new ValidatorUtil().find( Department.class, "persons" );

		try {
			new ValidatorUtil().validate( new Person() );
		} catch( ConstraintViolationException ex ) {
			for( final ConstraintViolation< ? > violation: ex.getConstraintViolations() ) {
				System.out.println( String.format(
					"Instance: %s, property: %s",
					violation.getRootBean(),
					violation.getPropertyPath()
				) );
			}
		}
	}

}
