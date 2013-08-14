<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<portlet:defineObjects />

<portlet:renderURL var="renderEditCompanyMethodURL">
	<portlet:param name="action" value="renderEditCompany"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="renderEditAddressMethodURL">
	<portlet:param name="action" value="renderEditAddress"></portlet:param>
</portlet:renderURL>

<portlet:actionURL var="addAddressMethodURL">
	<portlet:param name="action" value="addAddress"></portlet:param>
</portlet:actionURL>

<portlet:actionURL var="deleteAddressMethodURL">
	<portlet:param name="action" value="deleteAddress"></portlet:param>
</portlet:actionURL>

<portlet:renderURL var="handleRenderRequestMethodURL">
</portlet:renderURL>

<c:if test="${not empty success}">
	<p class="success">${success}</p>
</c:if>

<table class="companyFormTable">
	<tr>
		<th><spring:message code="id.text" text='ID' />:</th>
		<td>${company.id}</td>
	</tr>
	<tr>
		<th><spring:message code="name.text" text='Name' />:</th>
		<td>${company.name}</td>
	</tr>
	<tr>
		<th><spring:message code="description.text" text='Description' />:</th>
		<td>${company.description}</td>
	</tr>
	<tr>
		<th><spring:message code="year.text" text='Foundation year' />:</th>
		<td>${company.year}</td>
	</tr>
	<tr>
		<td><a
			href="${renderEditCompanyMethodURL}&companyId=${company.id}"><spring:message
					code="edit.text" text="Edit" /></a></td>
	</tr>
	<tr>
		<th><spring:message code="addresses.text" text='Addresses' />:</th>
	</tr>
	<tr>
		<th><spring:message code="street.text" text='Street' />:</th>
		<th><spring:message code="building.text" text='Building' />:</th>
		<th><spring:message code="city.text" text='City' />:</th>
		<th><spring:message code="country.text" text='Country' />:</th>
	</tr>
	<c:forEach items="${company.addresses.addresses}" var="address">
		<tr>
			<td>${address.street}</td>
			<td>${address.building}</td>
			<td>${address.city}</td>
			<td>${address.country}</td>
			<td><a
				href="${renderEditAddressMethodURL}&addressId=${address.id}"><spring:message
						code="edit.text" text="Edit" /></a></td>
			<td><a href="${deleteAddressMethodURL}&addressId=${address.id}"><spring:message
						code="delete.text" text="Delete" /></a></td>
		</tr>
	</c:forEach>

</table>

<form:errors path="address" cssClass="error" />
<form:form action="${addAddressMethodURL}" method="post"
	commandName="address">
	<table class="addressFormTable">
		<tr>
			<th><spring:message code="street.text" text='Street' /></th>
			<td><input name="street" type="text" /></td>
			<td><form:errors path="street" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="building.text" text='Building' /></th>
			<td><input name="building" type="text" /></td>
			<td><form:errors path="building" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="city.text" text='City' /></th>
			<td><input name="city" type="text" /></td>
			<td><form:errors path="city" cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="country.text" text='Country' /></th>
			<td><input name="country" type="text" /></td>
			<td><form:errors path="country" cssClass="error" /></td>
		</tr>
		<tr>
			<td><input type="submit"
				value="<spring:message code="add.text" text='Add'/>" /></td>
		</tr>
	</table>
</form:form>

<a href="${handleRenderRequestMethodURL}"><spring:message
		code="back.text" /></a>
