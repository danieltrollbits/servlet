<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<title>index</title>
	</head>
	<body>
		<div>
			<div>${param.message}</div><div>${message}</div>
			<fieldset style="margin:5% 10% 1% 10%">
				<legend>Search Person</legend>
				<div>
					<form action="${pageContext.request.contextPath}/index" method="get">
						first name <input type="text" name="firstName" value="${param.firstName}">
						last name <input type="text" name="lastName" value="${param.lastName}"><br>
						middle name <input type="text" name="middleName" value="${param.middleName}">
						Role
						<select name="roles">
							<option value="">Select role...</option>
							<c:forEach var="role" items="${roles}">
								<option ${role.role == param.roles ? 'selected="selected"' : ''} 
								value="${role.role}">${role.role}</option>
							</c:forEach>
						</select><br>
						<div align="right">
							<button type="submit" name="search" value="search">search</button>
						</div>
						<hr>
					</form>
				</div>
				<div>
					<fieldset>
						<legend>Person Table</legend>
						<form action="${pageContext.request.contextPath}/index" method="post">
							<div align="right" style="margin-bottom:2%">
								<button type="submit" value="add" name="add">Add</button>
								<button type="submit" value="update" name="update">Update</button>
								<button type="submit" value="delete" name="delete">Delete</button>
							</div>
							<table border="1">
								<thead>
								<tr>
									<th></th>
									<th>Id</th>
									<th>First Name</th>
									<th>Middle Name</th>
									<th>Last Name</th>
									<th>Gender</th>
									<th>Birthdate</th>
									<th>Employed</th>
									<th>Gwa</th>
									<th>Role</th>
								</tr>
								</thead>
								<tbody>
									<c:if test="${!persons.isEmpty()}">
									<c:forEach var="person" items="${persons}">
										<tr>
											<td><input type="checkbox" name="personId" value="${person.id}"></td>
											<td>${person.id}</td>
											<td>${person.firstName}</td>
											<td>${person.middleName}</td>
											<td>${person.lastName}</td>
											<td>${person.gender}</td>
											<td><fmt:formatDate type="date" value="${person.birthdate}"/></td>
											<td>${person.employed}</td>
											<td>${person.gwa}</td>
											<td>${person.rolesToString()}</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${persons.isEmpty()}">
										<td colspan="10">NO DETAILS</td>
									</c:if>
								</tbody>
							</table>
						</form>
					</fieldset>	
				</div>
			</fieldset>
		</div>
	</body>
</html>