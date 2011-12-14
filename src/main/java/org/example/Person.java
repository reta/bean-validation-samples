package org.example;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class Person {
	@NotEmpty private String firstName;
	@NotEmpty private String lastName;
    
	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public @NotNull String saveItem( @Valid @NotNull Person person, @Max( 23 ) BigDecimal age ) {
		return "";
	}
}
