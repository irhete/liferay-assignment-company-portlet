package com.nortal.assignment.companymanagement.portlet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.validator.AddressValidator;

public class AddressValidatorTests {

	private Address address;
	private Errors errors;
	private AddressValidator validator;

	@Before
	public void setUp() {
		address = new Address();
		validator = new AddressValidator();
		errors = new BeanPropertyBindingResult(address, "address");
	}

	@Test
	public void validateSuccessfulTest() {
		address.setStreet("street");
		address.setBuilding(123);
		address.setCity("city");
		address.setCountry("country");

		validator.validate(address, errors);
		assertEquals(false, errors.hasErrors());
	}

	@Test
	public void validateStreetNullTest() {
		address.setBuilding(123);
		address.setCity("city");
		address.setCountry("country");

		validator.validate(address, errors);
		assertNotNull(errors.getFieldError("street"));
	}

	@Test
	public void validateCityNullTest() {
		address.setStreet("street");
		address.setBuilding(123);
		address.setCountry("country");

		validator.validate(address, errors);
		assertNotNull(errors.getFieldError("city"));
	}

	@Test
	public void validateCountryNullTest() {
		address.setStreet("street");
		address.setBuilding(123);
		address.setCity("city");

		validator.validate(address, errors);
		assertNotNull(errors.getFieldError("country"));
	}
}
