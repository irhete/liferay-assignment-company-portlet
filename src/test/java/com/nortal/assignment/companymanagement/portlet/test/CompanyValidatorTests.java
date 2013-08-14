package com.nortal.assignment.companymanagement.portlet.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.model.Company;
import com.nortal.assignment.companymanagement.portlet.validator.CompanyValidator;

public class CompanyValidatorTests {
	private Company company;
	private Errors errors;
	private CompanyValidator validator;

	@Before
	public void setUp() {
		company = new Company();
		validator = new CompanyValidator();
		errors = new BeanPropertyBindingResult(company, "company");
	}

	@Test
	public void validateSuccessfulTest() {
		company.setName("company");
		company.setDescription("description");
		company.setYear(2002);
		Address address = new Address("street", 123, "city", "country");
		company.addAddress(address);

		validator.validate(company, errors);
		assertEquals(false, errors.hasErrors());
	}

	@Test
	public void validateNameNullTest() {
		company.setDescription("description");
		company.setYear(2002);
		Address address = new Address("street", 123, "city", "country");
		company.addAddress(address);

		validator.validate(company, errors);
		assertNotNull(errors.getFieldError("name"));
	}

	@Test
	public void validateDescriptionNullTest() {
		company.setName("company");
		company.setYear(2002);
		Address address = new Address("street", 123, "city", "country");
		company.addAddress(address);

		validator.validate(company, errors);
		assertNotNull(errors.getFieldError("description"));
	}

	@Test
	public void validateAddressNullTest() {
		company.setName("company");
		company.setDescription("description");
		company.setYear(2002);

		validator.validate(company, errors);
		assertNotNull(errors.getFieldError("addresses.addresses[0].street"));
	}

}
