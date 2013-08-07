<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<portlet:actionURL var="editAddressMethodURL">
	<portlet:param name="action" value="editAddress"></portlet:param>
</portlet:actionURL>

<portlet:renderURL var="viewCompanyDetailsMethodURL">
	<portlet:param name="action" value="viewCompanyDetails"></portlet:param>
</portlet:renderURL>

<form:errors path="address" cssClass="error" />
<form:form action="${editAddressMethodURL}" method="post"
	modelAttribute="address">
	<table class="addressFormTable">
		<tr>
			<th><spring:message code="street.text" text='Street'/></th>
			<td><input name="street" type="text" value="${address.street}"/></td>
			<td><form:errors path="street"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="building.text" text='Building'/></th>
			<td><input name="building" type="text" value="${address.building}"/></td>
			<td><form:errors path="building"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="city.text" text='City'/></th>
			<td><input name="city" type="text" value="${address.city}"/></td>
			<td><form:errors path="city"
					cssClass="error" /></td>
		</tr>
		<tr>
			<th><spring:message code="country.text" text='Country'/></th>
			<td><input name="country" type="text" value="${address.country}"/></td>
			<td><form:errors path="country"
					cssClass="error" /></td>
		</tr>
		<tr>
			<td><input type="submit" value="<spring:message code="edit.text" text='Edit'/>" /></td>
		</tr>
	</table>
</form:form>

<a href="${viewCompanyDetailsMethodURL}&companyId=${company.id}"><spring:message code="back.text"/></a>
