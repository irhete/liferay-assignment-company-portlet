package com.nortal.assignment.companymanagement.portlet.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;
import com.nortal.assignment.companymanagement.portlet.restconnector.RestConnectorService;
import com.nortal.assignment.companymanagement.portlet.validator.AddressValidator;
import com.nortal.assignment.companymanagement.portlet.validator.CompanyValidator;

@SessionAttributes({ "company", "address" })
@Controller(value = "CompanyManagementController")
@RequestMapping("VIEW")
public class CompanyManagementController {

	@Autowired
	RestConnectorService restService;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		if (!model.containsAttribute("company")) {
			model.addAttribute("company", new Company());
		}
		Companies companies;
		try {
			companies = restService.getCompanies();
		} catch (HttpClientErrorException e) {
			companies = new Companies();
		} catch (HttpServerErrorException e) {
			companies = new Companies();
		} catch (ResourceAccessException e) {
			companies = new Companies();
		}
		model.addAttribute("companies", companies.getCompanies());
		return "defaultRender";
	}

	@ActionMapping(params = "action=addCompany")
	public void addCompanyMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company, BindingResult result) {
		CompanyValidator validator = new CompanyValidator();
		validator.validate(company, result);
		if (!result.hasErrors()) {
			try {
				company.setId(0);
				restService.addCompany(company);
				model.addAttribute("success", "Company successfully added!");
			} catch (HttpClientErrorException e) {
				result.reject("client.error");
			} catch (HttpServerErrorException e) {
				result.reject("server.error");
			} catch (ResourceAccessException e) {
				result.reject("connection.error");
			}
		}
	}

	@RenderMapping(params = "action=renderEditCompany")
	public String renderEditCompanyMethod(RenderRequest request,
			RenderResponse response, Model model) {
		return "editCompany";
	}

	@ActionMapping(params = "action=editCompany")
	public void editCompanyMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company, BindingResult result) {
		CompanyValidator validator = new CompanyValidator();
		validator.validate(company, result);
		response.setRenderParameter("action", "renderEditCompany");
		if (!result.hasErrors()) {
			try {
				restService.editCompany(company);
				model.addAttribute("success", "Company successfully updated!");
				response.setRenderParameter("companyId", "" + company.getId());
				response.setRenderParameter("action", "viewCompanyDetails");
			} catch (HttpClientErrorException e) {
				result.reject("client.error");
			} catch (HttpServerErrorException e) {
				result.reject("server.error");
			} catch (ResourceAccessException e) {
				result.reject("connection.error");
			}
		}
	}

	@RenderMapping(params = "action=viewCompanyDetails")
	public String viewCompanyDetailsMethod(RenderRequest request,
			RenderResponse response, Model model,
			@RequestParam("companyId") long companyId,
			@ModelAttribute("company") Company company, BindingResult result) {
		try {
			company = restService.getCompany(companyId);
			model.addAttribute("company", company);
			if (!model.containsAttribute("address")) {
				model.addAttribute("address", new Address());
			}
			return "viewCompany";
		} catch (HttpClientErrorException e) {
			result.reject("client.error");
		} catch (HttpServerErrorException e) {
			result.reject("server.error");
		} catch (ResourceAccessException e) {
			result.reject("connection.error");
		}
		return handleRenderRequest(request, response, model);
	}

	@ActionMapping(params = "action=addAddress")
	public void addAddressMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company,
			@ModelAttribute("address") Address address, BindingResult result) {
		AddressValidator validator = new AddressValidator();
		validator.validate(address, result);
		if (!result.hasErrors()) {
			try {
				address.setId(0);
				address.setCompany(company);
				restService.addAddress(address);
				model.addAttribute("success", "Address successfully added!");
				model.addAttribute("address", new Address());
			} catch (HttpClientErrorException e) {
				result.reject("client.error");
			} catch (HttpServerErrorException e) {
				result.reject("server.error");
			} catch (ResourceAccessException e) {
				result.reject("connection.error");
			}
		}
		response.setRenderParameter("companyId", "" + company.getId());
		response.setRenderParameter("action", "viewCompanyDetails");
	}

	@ActionMapping(params = "action=deleteAddress")
	public void deleteAddressMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company, BindingResult result,
			@RequestParam("addressId") long addressId) {
		try {
			restService.deleteAddress(addressId, company.getId());
			model.addAttribute("success", "Address successfully deleted!");
		} catch (HttpClientErrorException e) {
			result.reject("client.error");
		} catch (HttpServerErrorException e) {
			result.reject("server.error");
		} catch (ResourceAccessException e) {
			result.reject("connection.error");
		}
		response.setRenderParameter("companyId", "" + company.getId());
		response.setRenderParameter("action", "viewCompanyDetails");
	}

	@ActionMapping(params = "action=editAddress")
	public void editAddressMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("address") Address address, BindingResult result,
			@ModelAttribute("company") Company company) {
		AddressValidator validator = new AddressValidator();
		validator.validate(address, result);
		response.setRenderParameter("addressId", "" + address.getId());
		response.setRenderParameter("action", "renderEditAddressErrors");
		if (!result.hasErrors()) {
			try {
				address.setCompany(company);
				restService.editAddress(address);
				model.addAttribute("success", "Address successfully updated!");
				response.setRenderParameter("companyId", "" + company.getId());
				response.setRenderParameter("action", "viewCompanyDetails");
			} catch (HttpClientErrorException e) {
				result.reject("client.error");
			} catch (HttpServerErrorException e) {
				result.reject("server.error");
			} catch (ResourceAccessException e) {
				result.reject("connection.error");
			}
		}
	}

	@RenderMapping(params = "action=renderEditAddress")
	public String renderEditAddressMethod(RenderRequest request,
			RenderResponse response, Model model,
			@RequestParam("addressId") long addressId,
			@ModelAttribute("company") Company company) {
		model.addAttribute("address", company.getAddress(addressId));
		return "editAddress";

	}

	@RenderMapping(params = "action=renderEditAddressErrors")
	public String renderEditAddressErrorsMethod(RenderRequest request,
			RenderResponse response, Model model) {
		return "editAddress";

	}
}
