package com.nortal.assignment.companymanagement.portlet.controller;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller(value = "CompanyManagementController")
@RequestMapping("VIEW")
public class CompanyManagementController {

	@RenderMapping
	public String handleRenderRequest(RenderRequest request,
			RenderResponse response, Model model) {
		return "defaultRender";
	}

	@RenderMapping(params = "action=addCompany")
	public String addCompanyMethod(RenderRequest request,
			RenderResponse response, Model model) {

		return "addCompany";
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
