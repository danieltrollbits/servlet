<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Add Person</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/index" method="post">
	<div>
		<fieldset style="margin:5% 10% 1% 10%">
		<legend>
			<c:choose>
				<c:when test="${personId.isEmpty()}">Add Person</c:when>
				<c:otherwise>Update Person</c:otherwise>
			</c:choose>
		</legend>	
		<div>
			<fieldset style="margin:2% 2% 2% 2%">
				<legend>General Information</legend>
				<input type="hidden" name="personId" value="${person.id}">
				first name <input type="text" name="firstName" value="${person.firstName}">
				last name <input type="text" name="lastName" value="${person.lastName}"><br>
				middle name <input type="text" name="middleName" value="${person.middleName}">
				gender
					<c:set var="isMale" value=""/>
					<c:set var="isFeMale" value=""/>
					<c:choose>
						<c:when test="${person.gender.toString().equals('MALE')}">
							<c:set var="isMale" value="checked"/>
							<c:set var="isFeMale" value=""/>
						</c:when>
						<c:when test="${person.gender.toString().equals('FEMALE')}">
							<c:set var="isMale" value=""/>
							<c:set var="isFeMale" value="checked"/>
						</c:when>
						<c:otherwise>
							<c:set var="isMale" value=""/>
							<c:set var="isFeMale" value=""/>
						</c:otherwise>
					</c:choose>
					<input type="radio" name="gender" value="male" ${isMale}> Male 
					<input type="radio" name="gender" value="female" ${isFeMale}> female<br>
					<fmt:formatDate dateStyle="long" value="${person.birthdate}" var="formatDate"/>
				birtdate <input type="text" name="birthdate" value="${formatDate}" placeholder="MMMM-dd-yyyy">
				employed?
					<c:if test="${person.employed}">
						<input type="radio" name="employed" value="yes" checked> Yes
						<input type="radio" name="employed" value="no"> No
					</c:if>
					<c:if test="${!person.employed}">
						<input type="radio" name="employed" value="yes"> Yes
						<input type="radio" name="employed" value="no" checked> No
					</c:if><br>
				gwa <input type="text" name="gwa" value="${person.gwa}"><br>
				<fieldset style="margin-right:60%">
					<legend>Role</legend>
					<input type="checkbox" name="role" value="Software Developer"> Sofware Developer<br>
					<input type="checkbox" name="role" value="QA Engineer"> QA Engineer<br>
					<input type="checkbox" name="role" value="Fasttrack Instructor"> Fasttrack Instructor<br>
					<input type="checkbox" name="role" value="Project Manager" ${pmChecked}> Project Manager
				</fieldset>
			</fieldset>
		</div>
		<div>
			<fieldset style="margin:2% 2% 2% 2%">
				<legend>Address</legend>
				street <input type="text" name="street" value="${person.addressDto.street}">
				house no <input type="text" name="houseNo" value="${person.addressDto.houseNo}"><br>
				barangay <input type="text" name="barangay" value="${person.addressDto.barangay}">
				subdivision <input type="text" name="subdivision" value="${person.addressDto.subdivision}"><br>
				city <input type="text" name="city" value="${person.addressDto.city}">
				zipcode <input type="text" name="zipcode" value="${person.addressDto.zipCode}">
			</fieldset>
		</div>
		<c:if test="${!personId.isEmpty()}">
		<div style="margin:2% 2% 2% 2%">
			<c:forEach var="contact" items="${person.contactDtos}">
				<span>${contact.type}</span>
				<input type="hidden" name="contactType" value="${contact.type}">
				<input type="text" name="contactValue" value="${contact.value}"><br>
			</c:forEach>
		</div>
		</c:if>
		<div>
			<fieldset style="margin:2% 2% 2% 2%">
				<legend>Contact</legend>
				Type <input type="radio" name="contactType" value="mobile"> Mobile
				<input type="radio" name="contactType" value="phone"> Phone<br>
				Number <input type="text" name="contactValue">
			</fieldset>
		</div>
		<div style="margin:2% 2% 2% 2%">
			<button type="submit" name="save" value="save">Save</button>
		</div>
		</fieldset>	
	</div>
	</form>
</body>
</html>