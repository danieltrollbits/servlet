<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Add Person</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/person" method="post">
	<div>
		<fieldset style="margin:5%">
			<legend>General Information</legend>
			<input type="hidden" name="personId" value="${person.id}">
			first name <input type="text" name="firstName" value="${person.firstName}">
			middle name <input type="text" name="middleName" value="${person.middleName}"><br>
			last name <input type="text" name="lastName" value="${person.lastName}"><br>
			gender
				<input type="radio" name="gender" value="male"> Male 
				<input type="radio" name="gender" value="female"> Female
			birtdate <input type="text" name="birtdate" value="${person.firstName}"><br>
			employed?
				<input type="radio" name="employed" value="yes"> Yes
				<input type="radio" name="employed" value="no"> No
			gwa <input type="text" name="gwa"><br>
			role
				<input type="checkbox" name="role" value="Software Developer"> Sofware Developer<br>
				<input type="checkbox" name="role" value="Project Manager"> Project Manager
		</fieldset>
	</div>
	<div>
		<fieldset style="margin:5%">
			<legend>Address</legend>
			street <input type="text" name="street" value="${person.addressDto.street}">
			house no <input type="text" name="houseNo" value="${person.addressDto.houseNo}"><br>
			barangay <input type="text" name="barangay" value="${person.addressDto.barangay}"><br>
			subdivision <input type="text" name="subdivision" value="${person.addressDto.subdivision}"><br>
			city <input type="text" name="city" value="${person.addressDto.city}"><br>
			zipcode <input type="text" name="zipcode" value="${person.addressDto.zipCode}">
		</fieldset>
	</div>
	<!-- <c:forEach var="contact" items="${person.contacts}">
		<span value="${contact.type}"></span>
		<input type="text" readonly="readonly" value="${contact.value}"><br>
	</c:forEach> -->
	<div>
		<fieldset>
			<legend>Contact</legend>
				<input type="radio" name="type" value="mobile"> Mobile<br>
				<input type="radio" name="type" value="phone"> Phone
			value <input type="text" name="value">
		</fieldset>
	</div>
	<div>
		<button type="submit" name="save" value="save">Save</button>
	</div>
	</form>
</body>
</html>