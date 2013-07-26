package com.nortal.assignment.companymanagement.portlet.controller;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.nortal.assignment.companymanagement.portlet.model.Companies;
import com.nortal.assignment.companymanagement.portlet.model.Company;

@Controller(value = "CompanyManagementController")
@RequestMapping("VIEW")
public class CompanyManagementController {

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		Companies companies = new RestTemplate().getForObject(
				"http://localhost:10800/companies", Companies.class);
		model.addAttribute("companies", companies.getCompanies());
		return "defaultRender";
	}

	@ActionMapping(params = "action=addCompany")
	public void addCompanyMethod(ActionRequest request,
			ActionResponse response, Model model,
			@ModelAttribute("company") Company company, BindingResult result) {
		System.out.println(company.getName());
	}

	@RenderMapping(params = "action=viewCompanies")
	public String viewCompaniesMethod(RenderRequest request,
			RenderResponse response, Model model) {

		return "viewCompanies";
	}

	@RenderMapping(params = "action=editCompany")
	public String editCompanyMethod(RenderRequest request,
			RenderResponse response, Model model) {

		return "editCompany";
	}
}
