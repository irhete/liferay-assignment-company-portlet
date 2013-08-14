<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<h1>
	<spring:message code="add.company.text" text='Add Company' />
</h1>

<portlet:defineObjects />

<portlet:actionURL var="addCompanyMethodURL">
	<portlet:param name="action" value="addCompany"></portlet:param>
</portlet:actionURL>

<portlet:renderURL var="viewCompanyDetailsMethodURL">
	<portlet:param name="action" value="viewCompanyDetails"></portlet:param>
</portlet:renderURL>

<c:if test="${not empty success}">
	<p class="success">${success}</p>
</c:if>
<form:errors path="company" cssClass="error" />
<form:form action="${addCompanyMethodURL}" method="post"
	commandName="company">

	<table class="companyFormTable">
		<tr>
			<th><spring:message code="name.text" text='Name' />:</th>
			<td><input name="name" type="text" /></td>
			<td><form:errors path="name" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="description.text" text='Description' />:</th>
			<td><input name="description" type="text" /></td>
			<td><form:errors path="description" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="year.text" text='Foundation year' />:</th>
			<td><input name="year" type="text" /></td>
			<td><form:errors path="year" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="street.text" text='Street' />:</th>
			<td><input name="addresses.addresses[0].street" type="text" /></td>
			<td><form:errors path="addresses.addresses[0].street" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="building.text" text='Building' />:</th>
			<td><input name="addresses.addresses[0].building" type="text" /></td>
			<td><form:errors path="addresses.addresses[0].building" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="city.text" text='City' />:</th>
			<td><input name="addresses.addresses[0].city" type="text" /></td>
			<td><form:errors path="addresses.addresses[0].city" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="country.text" text='Country' />:</th>
			<td><input name="addresses.addresses[0].country" type="text" /></td>
			<td><form:errors path="addresses.addresses[0].country" cssClass="error" /></td>
		</tr>
		<tr>
			<td><input type="submit"
				value="<spring:message code="add.text" text='Add'/>" /></td>
		</tr>
	</table>
</form:form>

<h1>
	<spring:message code="companies.text" text="Companies" />
</h1>
<table id="viewCompaniesTable">
	<tr>
		<th><spring:message code="id.text" text='ID' /></th>
		<th><spring:message code="name.text" text='Name' /></th>
	</tr>
	<c:forEach items="${companies}" var="company">
		<tr>
			<td>${company.id}</td>
			<td>${company.name}</td>
			<td><a
				href="${viewCompanyDetailsMethodURL}&companyId=${company.id}"><spring:message
						code="view.text" text="View" /></a></td>
		</tr>
	</c:forEach>
</table>