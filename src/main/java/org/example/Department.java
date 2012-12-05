package org.example;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.example.constraints.ConsistentDateParameters;
import org.hibernate.validator.constraints.NotEmpty;

public class Department {
	@NotEmpty @Valid private Collection< Person > persons;

	public void setPersons( Collection< Person > persons ) {
		this.persons = persons;
	}

	public Collection< Person > getPersons() {
		return persons;
	}
	
	@ConsistentDateParameters
	public void createCalendarEvent(Date start, Date end) {
	}
		
	public void placeOrder(@NotNull Item item, @Min(1) int quantity) {    
	    //actual implementation
	}
}
