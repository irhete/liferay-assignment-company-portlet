package com.nortal.assignment.companymanagement.portlet.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;
import com.nortal.assignment.companymanagement.portlet.restconnector.RestConnectorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RestConnectorServiceTests {
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private RestConnectorServiceImpl restService;

	private Company company;

	private Address address;

	@Before
	public void setUp() {
		company = new Company();
		company.setId(1);
		address = new Address("street", 123, "city", "country");
		address.setId(1);
		address.setCompany(company);
	}

	@Test
	public void getCompaniesTest() {
		restService.getCompanies();
		Mockito.verify(restTemplate).getForObject(
				"http://localhost:10800/companies", Companies.class);
	}

	@Test
	public void addCompanyTest() {
		restService.addCompany(company);
		Mockito.verify(restTemplate).postForObject(
				"http://localhost:10800/companies", company, Company.class);
	}

	@Test
	public void editCompanyTest() {
		restService.editCompany(company);
		Mockito.verify(restTemplate).postForObject(
				"http://localhost:10800/companies/" + company.getId(), company,
				Company.class);
	}

	@Test
	public void getCompanyTest() {
		long id = 1;
		restService.getCompany(id);
		Mockito.verify(restTemplate).getForObject(
				"http://localhost:10800/companies/" + id, Company.class);
	}

	@Test
	public void addAddressTest() {
		restService.addAddress(address);
		Mockito.verify(restTemplate).postForObject(
				"http://localhost:10800/companies/" + company.getId()
						+ "/addresses", address, Address.class);
	}

	@Test
	public void editAddressTest() {
		restService.editAddress(address);
		Mockito.verify(restTemplate).postForObject(
				"http://localhost:10800/companies/" + company.getId()
						+ "/addresses/" + address.getId(), address,
				Address.class);
	}

	@Test
	public void deleteAddressTest() {
		restService.deleteAddress(address.getId(), company.getId());
		Mockito.verify(restTemplate).delete(
				"http://localhost:10800/companies/" + company.getId()
						+ "/addresses/" + address.getId());
	}
}
