package org.example;

public class Starter {
	public static void main( String[] args ) {
		new ValidatorUtil().add( Person.class, "firstName" );
		new ValidatorUtil().find( Person.class, "firstName" );
		new ValidatorUtil().find( Department.class, "persons" );
		new ValidatorUtil().validate( new Person() );
	}

}
