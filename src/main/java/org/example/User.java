package org.example;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConvertGroup;
import javax.validation.Valid;
import javax.validation.groups.Default;

import org.example.Address.BasicPostal;
import org.example.Address.Complete;
import org.example.Address.FullPostal;

public class User {
	 @Valid
	 @ConvertGroup.List(
		{
			@ConvertGroup(from = Default.class, to = BasicPostal.class),
	        @ConvertGroup(from = Complete.class, to = FullPostal.class)
	    }
	 )
	 Set<Address> addresses = new HashSet<Address>();
}
