package com.nortal.assignment.companymanagement.portlet.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;
import com.nortal.assignment.companymanagement.portlet.restconnector.RestConnectorServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RestConnectorServiceTests {
	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private RestConnectorServiceImpl restService;

	@Test
	public void getCompaniesTest() {
		restService.getCompanies();
		Mockito.verify(restTemplate).getForObject(
				"http://localhost:10800/companies", Companies.class);
	}

	@Test
	public void addCompanyTest() {
		Company company = new Company();
		restService.addCompany(company);
		Mockito.verify(restTemplate).postForObject(
				"http://localhost:10800/companies", company, Company.class);
	}

	@Test
	public void editCompanyTest() {
		long id = 1;
		Company company = new Company();
		company.setId(id);
		restService.editCompany(company);
		Mockito.verify(restTemplate).postForObject(
				"http://localhost:10800/companies/" + id, company,
				Company.class);
	}

	@Test
	public void getCompanyTest() {
		long id = 1;
		restService.getCompany(id);
		Mockito.verify(restTemplate).getForObject(
				"http://localhost:10800/companies/" + id, Company.class);
	}
}
