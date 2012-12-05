package org.example;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class Address {
	//validation groups
	public interface Complete extends Default {}
	public interface BasicPostal {}
	public interface FullPostal extends BasicPostal {}
	
	@NotNull(groups = BasicPostal.class)
    String street1;

    @NotNull
    String street2;

    @Size(groups = BasicPostal.class, min = 3)
    String zipCode = "12";

    @Size(groups = FullPostal.class, max = 2)
    String doorCode = "ABC";
}
