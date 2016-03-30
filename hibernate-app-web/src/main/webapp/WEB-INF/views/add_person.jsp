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
			first name <input type="text" name="firstName">
			middle name <input type="text" name="middleName"><br>
			last name <input type="text" name="lastName"><br>
			age <input type="text" name="age"><br>
			<form>
				<input type="radio" name="gender" value="male"> Male<br>
				<input type="radio" name="gender" value="female"> Female
			</form>
			birtdate <input type="text" name="birtdate"><br>
			employed?
			<form>
				<input type="radio" name="employed" value="yes"> Yes<br>
				<input type="radio" name="employed" value="no"> No
			</form>
			gwa <input type="text" name="gwa"><br>
			role
			<form>
				<input type="checkbox" name="role" value="Software Developer"> Sofware Developer<br>
				<input type="checkbox" name="role" value="Project Manager"> Project Manager
			</form>
		</fieldset>
	</div>
	<div>
		<fieldset style="margin:5%">
			<legend>Address</legend>
			street <input type="text" name="address.street">
			house no <input type="text" name="address.houseNo"><br>
			barangay <input type="text" name="address.barangay"><br>
			subdivision <input type="text" name="address.subdivision"><br>
			city <input type="text" name="address.city"><br>
			zipcode <input type="text" name="address.zipcode">
		</fieldset>
	</div>
	<div>
		<fieldset>
			<legend>Contact</legend>
			<form>
				<input type="radio" name="contact.type" value="mobile"> Mobile<br>
				<input type="radio" name="contact.type" value="phone"> Phone
			</form>
			value <input type="text" name="contact.value">
		</fieldset>
	</div>
	<div>
		<input type="submit" name="save" value="save">
	</div>
	</form>
</body>
</html>