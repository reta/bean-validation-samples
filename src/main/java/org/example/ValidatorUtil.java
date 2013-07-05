package org.example;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import javax.validation.groups.Default;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.MethodDescriptor;
import javax.validation.metadata.MethodType;
import javax.validation.metadata.ParameterDescriptor;
import javax.validation.metadata.PropertyDescriptor;
import javax.validation.metadata.Scope;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.cfg.defs.NotNullDef;
import org.hibernate.validator.cfg.defs.SizeDef;

public class ValidatorUtil {
	private final ValidatorFactory factory;
	
	public ValidatorUtil() {
		factory = Validation.buildDefaultValidatorFactory();
	}
		
	public< T > void validate( final T instance ) {		
		final Validator validator = factory.getValidator();
		
		final Set< ConstraintViolation< T > > violations = validator
			.validate( instance, Default.class );

		if( !violations.isEmpty() ) {
			final Set< ConstraintViolation< ? > > constraints = 
				new HashSet< ConstraintViolation< ? > >( violations.size() );

			for ( final ConstraintViolation< ? > violation: violations ) {
				constraints.add( violation );
			}
			
			throw new ConstraintViolationException( constraints );
		}
	}
	
	public< T > void find( final Class< T > clazz, final String propertName ) {
		final Validator validator = factory.getValidator();
		find( clazz, propertName, validator );
	}
	
	public< T > void find( final Class< T > clazz, final String propertName, final Validator validator ) {
		PropertyDescriptor property = validator
			.getConstraintsForClass( clazz )
			.getConstraintsForProperty( propertName );

		Set< ConstraintDescriptor< ? > > fieldConstraints = property
		       .findConstraints()
		       .lookingAt( Scope.LOCAL_ELEMENT )
		       .declaredOn( ElementType.FIELD )
		       .getConstraintDescriptors();
		
		System.out.println( "Constraints declarations on field level: ");
		for( final ConstraintDescriptor< ? > descriptor: fieldConstraints ) {
			System.out.println( descriptor.toString() );	
		}

		Set< ConstraintDescriptor< ? > > propertyConstraints = property
			.findConstraints()
		    .lookingAt( Scope.LOCAL_ELEMENT )
		    .declaredOn( ElementType.METHOD )
		    .getConstraintDescriptors();
		
		System.out.println( "Constraints declarations on method level: ");
		for( final ConstraintDescriptor< ? > descriptor: propertyConstraints ) {
			System.out.println( descriptor.toString() );	
		}

	}
	
	/**
	 * http://in.relation.to/Bloggers/HibernateValidator410Beta2WithProgrammaticConstraintConfigurationAPI
	 */
	public< T > void add( final Class< T > clazz, final String propertName ) {
		final HibernateValidatorConfiguration config = Validation
				.byProvider( HibernateValidator.class )
				.configure();

		final ConstraintMapping mapping =  config.createConstraintMapping();		
		mapping.type( clazz )
		    .property( propertName, ElementType.METHOD )
		        .constraint( new NotNullDef() )
		    .property( propertName, ElementType.FIELD )
		        .constraint( new NotNullDef() )
		        .constraint( new SizeDef()
		            .min( 2 )
		            .max( 14 )
		         );
				
		config.addMapping( mapping );
		ValidatorFactory factory = config.buildValidatorFactory();
		Validator validator = factory.getValidator();
		
		find( clazz, propertName, validator );
	}
	
	public< T > void validate( final Method method, Object[] arguments, final T instance  ) {
		ExecutableValidator methodValidator = Validation
			    .buildDefaultValidatorFactory()
			    .getValidator()
			    .forExecutables();

		Set< ConstraintViolation< T > > violations = methodValidator.validateParameters( instance, method, arguments);
		if( !violations.isEmpty() ) {
			throw new ConstraintViolationException( violations );
		}
				
	}
	
	/**
	 * Walk over method arguments
	 * @param <T>
	 * @param clazz
	 */
	public< T > void validate( final Class< T > clazz  ) {
		final Validator validator = Validation
			.byProvider( HibernateValidator.class )
	    	.configure()
	    	.failFast( true )	// validation will terminate on the first validation error
	    	.buildValidatorFactory()
	    	.getValidator()
	    	.unwrap( Validator.class );

		BeanDescriptor descriptor = validator.getConstraintsForClass( clazz );
		Set< MethodDescriptor > constrainedMethods = descriptor.getConstrainedMethods( MethodType.GETTER, MethodType.values() );

		for( final MethodDescriptor methodDescriptor: constrainedMethods ) {
			List< ParameterDescriptor > parameterConstraints = methodDescriptor.getParameterDescriptors();
			ParameterDescriptor parameterDescriptor = parameterConstraints.get( 0 );
			System.out.println( parameterDescriptor.getElementClass().getName() );
		}
	}
}
