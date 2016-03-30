<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<title>index</title>
	</head>
	<body>
		<div>
			<fieldset style="margin:5%">
				<legend>Search</legend>
				<div>
					<form action="${pageContext.request.contextPath}/index" method="get">
						last name <input type="text" name="lastName"><br>
						first name <input type="text" name="firstName"><br>
						middle name <input type="text" name="middleName"><br>
						role <input type="text" name="role"><br>
						<button type="submit" name="search" value="search">search</button>
					</form>
				</div>
			</fieldset>
		</div>
		<div>
			<fieldset style="margin:5%">
				<legend>Person Table</legend>
				<form action="${pageContext.request.contextPath}/person" method="get">
					<button type="submit" value="add" name="add">Add</button>
					<button type="submit" value="update" name="update">Update</button>
					<button type="submit" value="delete">Delete</button><hr>
					<table border="1">
						<thead>
						<tr>
							<th></th>
							<th>id</th>
							<th>first name</th>
							<th>middle name</th>
							<th>last name</th>
							<th>gender</th>
							<th>birthdate</th>
						</tr>
						</thead>
						<tbody>
							<c:forEach var="person" items="${persons}">
								<tr>
									<td><input type="checkbox" name="personId" value="${person.id}"></td>
									<td>${person.id}</td>
									<td>${person.firstName}</td>
									<td>${person.middleName}</td>
									<td>${person.lastName}</td>
									<td>${person.gender}</td>
									<td><fmt:formatDate type="date" value="${person.birthdate}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
			</fieldset>	
		</div>

<style>
	.div-border {
    width: 500px;
    padding: 25px;
    border: 1px solid gray;
    margin: 25px;
	}
</style>

	</body>
</html>