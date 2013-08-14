package com.nortal.assignment.companymanagement.portlet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nortal.assignment.companymanagement.portlet.model.Company;

public class CompanyValidator implements Validator {

	public boolean supports(Class clazz) {
		return Company.class.equals(clazz);
	}

	public void validate(Object company, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "empty",
				"Name can not be empty");
		ValidationUtils.rejectIfEmpty(e, "description", "empty",
				"Description can not be empty");
		ValidationUtils.rejectIfEmpty(e, "year", "empty",
				"Year can not be empty");
		ValidationUtils.rejectIfEmpty(e, "addresses.addresses[0].street",
				"empty", "Street can not be empty");
		ValidationUtils.rejectIfEmpty(e, "addresses.addresses[0].building",
				"empty", "Building can not be empty");
		ValidationUtils.rejectIfEmpty(e, "addresses.addresses[0].city",
				"empty", "City can not be empty");
		ValidationUtils.rejectIfEmpty(e, "addresses.addresses[0].country",
				"empty", "Country can not be empty");
	}
}
