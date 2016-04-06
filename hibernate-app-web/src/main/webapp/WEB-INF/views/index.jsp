<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
	<head>
		<title>index</title>
	</head>
	<body style="padding:2% 10% 10% 10%">
		<div>
			<div class="row">
				<div class="column column-6">
					<span style="color:blue">${param.message}</span>
				</div>
			</div>
			<fieldset>
				<legend>Persons</legend>
				<div style="padding:1%">
					<fieldset style="padding:1%">
						<legend>Search Filter</legend>
						<form action="${pageContext.request.contextPath}/index" method="get">
							<div class="row">
							    <div class="column column-6">
							        <div class="row">
							            <div class="column column-4">First Name</div>
							            <div class="column column-8">
							            	<input type="text" name="firstName" value="${param.firstName}">
							            </div>
							        </div>
							        <div class="row">
							            <div class="column column-4">Middle Name</div>
							            <div class="column column-8">
							            	<input type="text" name="middleName" value="${param.middleName}">
							            </div>
							        </div>
							    </div>
							    <div class="column column-6">
							        <div class="row">
							            <div class="column column-4">Last Name</div>
							            <div class="column column-8">
							            	<input type="text" name="lastName" value="${param.lastName}">
							            </div>
							        </div>
							        <div class="row">
							            <div class="column column-4">Role</div>
							            <div class="column column-8">
							            	<select name="roles">
												<option value="">Select role...</option>
												<c:forEach var="role" items="${roles}">
													<option ${role.role == param.roles ? 'selected="selected"' : ''} 
													value="${role.role}">${role.role}</option>
												</c:forEach>
											</select>
							            </div>
							        </div>
							    </div>
							</div>
							<div style="padding:1%" align="right">
								<button type="submit" name="search" value="search">search</button>
							</div>
						</form>
					</fieldset>
				</div>
				<div style="padding:1%">
					<fieldset style="padding:1%">
						<legend>Person Table</legend>
						<form action="${pageContext.request.contextPath}/index" method="post">
							<div align="right" style="padding:1%">
								<button type="submit" value="add" name="add">Add</button>
								<button type="submit" value="update" name="update">Update</button>
								<button type="submit" value="delete" name="delete">Delete</button>
							</div>
							<table class="table" border="1">
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
											<td><fmt:formatDate dateStyle="long" value="${person.birthdate}"/></td>
											<td>${person.employed}</td>
											<td>${person.gwa}</td>
											<td>${person.rolesToString()}</td>
										</tr>
									</c:forEach>
									</c:if>
									<c:if test="${persons.isEmpty()}">
										<td colspan="10" align="center">NO DETAILS</td>
									</c:if>
								</tbody>
							</table>
						</form>
					</fieldset>	
				</div>
			</fieldset>
		</div>

		<style>
		.table{
		    width: 100%;
		}	
		.table > th, td {
		    padding: 8px;
		}
		.required{
			color:red;
		}
		.row, .column {
		    box-sizing: border-box;
		}
		.row:before,
		.row:after {
		    content: " ";
		    display: table;
		}
		.row:after {
		    clear: both;
		}
		.column {
		    position: relative;
		    float: left;
		    padding: 4px;
		}
		.column + .column {
		    margin-left: 1.6%;
		}
		.column-1 {
		    width: 6.86666666667%;
		}

		.column-2 {
		    width: 15.3333333333%;
		}

		.column-3 {
		    width: 23.8%;
		}

		.column-4 {
		    width: 32.2666666667%;
		}

		.column-5 {
		    width: 40.7333333333%;
		}

		.column-6 {
		    width: 49.2%;
		}

		.column-7 {
		    width: 57.6666666667%;
		}

		.column-8 {
		    width: 66.1333333333%;
		}

		.column-9 {
		    width: 74.6%;
		}

		.column-10 {
		    width: 83.0666666667%;
		}

		.column-11 {
		    width: 91.5333333333%;
		}

		.column-12 {
		    width: 100%;
		}
		@media only screen and (max-width: 550px) {
		    .column-1, 
		    .column-2, 
		    .column-3, 
		    .column-4, 
		    .column-5, 
		    .column-6, 
		    .column-7, 
		    .column-8, 
		    .column-9, 
		    .column-10, 
		    .column-11, 
		    .column-12 {
		        width: auto;
		        float: none;
		    }

		    .column + .column {
		        margin-left: 0;
		    }
		}
	</style>
	</body>
</html>