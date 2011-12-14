package org.example;

import java.util.Collection;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

public class Department {
	@NotEmpty @Valid private Collection< Person > persons;

	public void setPersons( Collection< Person > persons ) {
		this.persons = persons;
	}

	public Collection< Person > getPersons() {
		return persons;
	}
}
