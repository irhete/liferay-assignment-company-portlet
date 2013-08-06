package com.nortal.assignment.companymanagement.portlet.restconnector;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;

public interface RestConnectorService {

	public Company addCompany(Company company);

	public Companies getCompanies();

	public Company editCompany(Company company);

	public Company getCompany(long companyId);

	public Address editAddress(Address address);

	public void deleteAddress(long addressId, long companyId);
}
