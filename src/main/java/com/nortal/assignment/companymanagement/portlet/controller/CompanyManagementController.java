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
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.nortal.assignment.companymanagement.portlet.model.Address;
import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;
import com.nortal.assignment.companymanagement.portlet.restconnector.RestConnectorService;

@SessionAttributes({ "company", "address" })
@Controller(value = "CompanyManagementController")
@RequestMapping("VIEW")
public class CompanyManagementController {

	@Autowired
	RestConnectorService restService;

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		model.addAttribute("company", new Company());
		Companies companies;
		try {
			companies = restService.getCompanies();
		} catch (HttpClientErrorException e) {
			companies = new Companies();
		} catch (HttpServerErrorException e) {
			companies = new Companies();
		}
		model.addAttribute("companies", companies.getCompanies());
		return "defaultRender";
	}

	@ActionMapping(params = "action=addCompany")
	public void addCompanyMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company, BindingResult result) {
		try {
			restService.addCompany(company);
			model.addAttribute("success", "Company successfully added!");
		} catch (HttpClientErrorException e) {
			result.reject("company", "Company could not be added");
		} catch (HttpServerErrorException e) {
			result.reject("company", "Server error, company could not be added");
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
		try {
			restService.editCompany(company);
			model.addAttribute("success", "Company successfully updated!");
		} catch (HttpClientErrorException e) {
			result.reject("company", "Company could not be updated");
		} catch (HttpServerErrorException e) {
			result.reject("company",
					"Server error, company could not be updated");
		}
		response.setRenderParameter("companyId", "" + company.getId());
		response.setRenderParameter("action", "viewCompanyDetails");
	}

	@RenderMapping(params = "action=viewCompanyDetails")
	public String viewCompanyDetailsMethod(RenderRequest request,
			RenderResponse response, Model model,
			@RequestParam("companyId") long companyId,
			@ModelAttribute("company") Company company, BindingResult result) {
		try {
			company = restService.getCompany(companyId);
			model.addAttribute("company", company);
			model.addAttribute("address", new Address());
			System.out.println("view company: " + company.getId());
			return "viewCompany";
		} catch (HttpClientErrorException e) {
			result.reject("company", "Company could not be found");
		} catch (HttpServerErrorException e) {
			result.reject("company", "Server error, company could not be found");
		}
		return handleRenderRequest(request, response, model);
	}

	@ActionMapping(params = "action=addAddress")
	public void addAddressMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company,
			@ModelAttribute("address") Address address, BindingResult result) {
		company.addAddress(address);
		try {
			restService.editCompany(company);
			model.addAttribute("success", "Address successfully added!");
		} catch (HttpClientErrorException e) {
			result.reject("company", "Address could not be added");
		} catch (HttpServerErrorException e) {
			result.reject("company", "Server error, address could not be added");
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
			result.reject("company", "Address could not be deleted");
		} catch (HttpServerErrorException e) {
			result.reject("company",
					"Server error, address could not be deleted");
		}
		response.setRenderParameter("companyId", "" + company.getId());
		response.setRenderParameter("action", "viewCompanyDetails");
	}

	@ActionMapping(params = "action=editAddress")
	public void editAddressMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company,
			@ModelAttribute("address") Address address, BindingResult result) {
		try {
			address.setCompany(company);
			restService.editAddress(address);
			model.addAttribute("success", "Address successfully updated!");
		} catch (HttpClientErrorException e) {
			result.reject("company", "Address could not be updated");
		} catch (HttpServerErrorException e) {
			result.reject("company",
					"Server error, address could not be updated");
		}
		response.setRenderParameter("companyId", "" + company.getId());
		System.out.println("edit: " + company.getId());
		response.setRenderParameter("action", "viewCompanyDetails");
	}

	@RenderMapping(params = "action=renderEditAddress")
	public String renderEditAddressMethod(RenderRequest request,
			RenderResponse response, Model model,
			@RequestParam("addressId") long addressId,
			@ModelAttribute("company") Company company, BindingResult result) {
		model.addAttribute("address", company.getAddress(addressId));
		System.out.println("render: " + company.getId());
		return "editAddress";

	}
}
