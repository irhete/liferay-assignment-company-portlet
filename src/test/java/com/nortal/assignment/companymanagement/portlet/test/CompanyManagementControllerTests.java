package com.nortal.assignment.companymanagement.portlet.test;

import static org.junit.Assert.assertEquals;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.nortal.assignment.companymanagement.portlet.controller.CompanyManagementController;
import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;
import com.nortal.assignment.companymanagement.portlet.restconnector.RestConnectorService;

@RunWith(MockitoJUnitRunner.class)
public class CompanyManagementControllerTests {
	@Mock
	private RestConnectorService restService;

	@Mock
	private RenderRequest request;
	@Mock
	private RenderResponse response;
	@Mock
	private Model model;
	@Mock
	private ActionRequest actionRequest;
	@Mock
	private ActionResponse actionResponse;
	@Mock
	private BindingResult result;
	@Mock
	private Companies companies;

	@InjectMocks
	private CompanyManagementController controller;

	@Test
	public void defaultViewTest() {
		Mockito.when(restService.getCompanies()).thenReturn(new Companies());
		String viewName = controller.handleRenderRequest(request, response,
				model);
		assertEquals("defaultRender", viewName);
	}

	@Test
	public void renderEditViewTest() {
		String viewName = controller.renderEditCompanyMethod(request, response,
				model);
		assertEquals("editCompany", viewName);
	}

	@Test
	public void viewCompanyDetailsSuccessfulTest() {
		String viewName = controller.viewCompanyDetailsMethod(request,
				response, model, 1, new Company(), result);
		assertEquals("viewCompany", viewName);
	}

	@Test
	public void viewCompanyDetailsServerErrorTest() {
		Mockito.when(restService.getCompany(1)).thenThrow(
				new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		Mockito.when(restService.getCompanies()).thenReturn(new Companies());
		String viewName = controller.viewCompanyDetailsMethod(request,
				response, model, 1, new Company(), result);
		Mockito.verify(result).reject("company",
				"Server error, company could not be found");
		assertEquals("defaultRender", viewName);
	}

	@Test
	public void viewCompanyDetailsClientErrorTest() {
		Mockito.when(restService.getCompany(1)).thenThrow(
				new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		Mockito.when(restService.getCompanies()).thenReturn(new Companies());
		String viewName = controller.viewCompanyDetailsMethod(request,
				response, model, 1, new Company(), result);
		Mockito.verify(result).reject("company", "Company could not be found");
		assertEquals("defaultRender", viewName);
	}

	@Test
	public void addCompanySuccessfulTest() {
		Company company = new Company("test company name",
				"test company description", 1996);
		controller.addCompanyMethod(actionRequest, actionResponse, model,
				company, result);
		Mockito.verify(model).addAttribute("success",
				"Company successfully added!");
	}

	@Test
	public void addCompanyServerError() {
		Company company = new Company("test company name",
				"test company description", 1996);
		Mockito.when(restService.addCompany(company)).thenThrow(
				new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		controller.addCompanyMethod(actionRequest, actionResponse, model,
				company, result);
		Mockito.verify(result).reject("company",
				"Server error, company could not be added");
	}

	@Test
	public void addCompanyClientError() {
		Company company = new Company("test company name",
				"test company description", 1996);
		Mockito.when(restService.addCompany(company)).thenThrow(
				new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		controller.addCompanyMethod(actionRequest, actionResponse, model,
				company, result);
		Mockito.verify(result).reject("company", "Company could not be added");
	}

	@Test
	public void editCompanySuccessfulTest() {
		Company company = new Company("test company name",
				"test company description", 1996);
		company.setId(1);
		controller.editCompanyMethod(actionRequest, actionResponse, model,
				company, result);
		Mockito.verify(model).addAttribute("success",
				"Company successfully updated!");
	}

	@Test
	public void editCompanyServerError() {
		Company company = new Company(1, "test company name",
				"test company description", 1996);
		Mockito.when(restService.editCompany(company)).thenThrow(
				new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		controller.editCompanyMethod(actionRequest, actionResponse, model,
				company, result);
		Mockito.verify(result).reject("company",
				"Server error, company could not be updated");
	}

	@Test
	public void editCompanyClientError() {
		Company company = new Company(1, "test company name",
				"test company description", 1996);
		Mockito.when(restService.editCompany(company)).thenThrow(
				new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		controller.editCompanyMethod(actionRequest, actionResponse, model,
				company, result);
		Mockito.verify(result)
				.reject("company", "Company could not be updated");
	}
}
