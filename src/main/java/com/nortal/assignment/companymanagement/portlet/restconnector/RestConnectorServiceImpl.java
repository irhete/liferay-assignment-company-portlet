package com.nortal.assignment.companymanagement.portlet.restconnector;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;

@Service
public class RestConnectorServiceImpl implements RestConnectorService {

	private RestTemplate restTemplate = new RestTemplate();

	public Company addCompany(Company company) {
		return restTemplate.postForObject("http://localhost:10800/companies",
				company, Company.class);
	}

	public Companies getCompanies() throws HttpClientErrorException,
			HttpServerErrorException {
		return restTemplate.getForObject("http://localhost:10800/companies",
				Companies.class);
	}

	public Company editCompany(Company company) {
		return restTemplate.postForObject("http://localhost:10800/companies/"
				+ company.getId(), company, Company.class);
	}

	public Company getCompany(long companyId) {
		return restTemplate.getForObject("http://localhost:10800/companies/"
				+ companyId, Company.class);
	}

	public Address editAddress(Address address) {
		return restTemplate.postForObject(
				"http://localhost:10800/companies/"
						+ address.getCompany().getId() + "/addresses/"
						+ address.getId(), address, Address.class);
	}

	public void deleteAddress(long addressId, long companyId) {
		restTemplate.delete("http://localhost:10800/companies/" + companyId
				+ "/addresses/" + addressId);
	}

	public Address addAddress(Address address) {
		return restTemplate.postForObject("http://localhost:10800/companies/"
				+ address.getCompany().getId() + "/addresses", address,
				Address.class);
	}

}
