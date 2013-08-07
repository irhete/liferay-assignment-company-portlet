<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1><spring:message code="add.company.text" text='Add Company'/></h1>

<portlet:defineObjects />

<portlet:actionURL var="editCompanyMethodURL">
	<portlet:param name="action" value="editCompany"></portlet:param>
</portlet:actionURL>

<portlet:renderURL var="viewCompanyDetailsMethodURL">
	<portlet:param name="action" value="viewCompanyDetails"></portlet:param>
</portlet:renderURL>

<c:if test="${not empty success}">
	<p class="success">${success}</p>
</c:if>

<form:errors path="company" cssClass="error" />
<form:form action="${editCompanyMethodURL}" method="post"
	modelAttribute="company">
	<table class="companyFormTable">
		<tr>
			<th><spring:message code="name.text" text='Name'/>:</th>
			<td><input name="name" type="text" value="${company.name}"/></td>
			<td><form:errors path="name"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="description.text" text='Description'/>:</th>
			<td><input name="description" type="text" value="${company.description}"/></td>
			<td><form:errors path="description"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="year.text" text='Foundation year'/>:</th>
			<td><input name="year" type="text" value="${company.year}"/></td>
			<td><form:errors path="year"
					cssClass="error" /></td>
		</tr>

		<tr>
			<td><input type="submit" value="<spring:message code="edit.text" text='Edit'/>" /></td>
		</tr>
	</table>
</form:form>

<a href="${viewCompanyDetailsMethodURL}&companyId=${company.id}"><spring:message code="back.text"/></a>
