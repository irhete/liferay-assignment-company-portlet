package com.nortal.assignment.companymanagement.portlet.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.nortal.assignment.companymanagement.portlet.model.Address;

public class AddressValidator implements Validator {

	public boolean supports(Class clazz) {
		return Address.class.equals(clazz);
	}

	public void validate(Object address, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "street", "empty",
				"Street can not be empty");
		ValidationUtils.rejectIfEmpty(e, "building", "empty",
				"Building can not be empty");
		ValidationUtils.rejectIfEmpty(e, "city", "empty",
				"City can not be empty");
		ValidationUtils.rejectIfEmpty(e, "country", "empty",
				"Country can not be empty");
	}
}
