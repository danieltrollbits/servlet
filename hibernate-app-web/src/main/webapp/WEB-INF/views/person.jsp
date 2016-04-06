<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>Person</title>
</head>
<body style="padding:2% 10% 10% 10%">
	<form action="${pageContext.request.contextPath}/index" method="post">
		<div class="row">
			<div class="column column-6"><span style="color:red">${error}</span></div>
		</div>
		<div class="row">
			<c:forEach var="error" items="${errors}">
				<div class="column column-6"><span style="color:red">${error}</span></div>
			</c:forEach>
		</div>
	<div>
		<fieldset>
		<legend>
			<c:choose>
				<c:when test="${personId.isEmpty()}">Add Person</c:when>
				<c:otherwise>Update Person</c:otherwise>
			</c:choose>
		</legend>	
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>General Information</legend>
				<input type="hidden" name="personId" value="${person.id}">
				<div class="row">
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">First Name<span class="required">*</span></div>
				            <div class="column column-8"><input type="text" name="firstName" value="${person.firstName}"></div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Middle Name<span class="required">*</span></div>
				            <div class="column column-8"><input type="text" name="middleName" value="${person.middleName}"></div>
				        </div>
				        <div class="row">
				            <div class="column column-4">BirthDate<span class="required">*</span></div>
				            <div class="column column-8">
				            	<fmt:formatDate pattern="MM-dd-yyy" value="${person.birthdate}" var="formatDate"/>
				            	<input type="text" name="birthdate" value="${formatDate}" placeholder="12-30-1900">
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Gwa<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="text" name="gwa" value="${person.gwa}">
				            </div>
				        </div>
				    </div>
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">Last Name<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="text" name="lastName" value="${person.lastName}">
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Gender<span class="required">*</span></div>
				            <div class="column column-8">
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
								<input type="radio" name="gender" value="female" ${isFeMale}> female
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Employed?<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="radio" name="employed" value="yes" ${person.employed ? 'checked' : ''}> Yes
								<input type="radio" name="employed" value="no" ${person.employed ? '' : 'checked'}> No
				            </div>
				        </div>
				    </div>
				</div>
				<div class="row">
				    <div class="column column-6">
				    	<fieldset>
							<legend>Role<span class="required">*</span></legend>
							<c:set var="sdChecked" value=""/>
							<c:set var="qaChecked" value=""/>
							<c:set var="ftChecked" value=""/>
							<c:set var="pmChecked" value=""/>
							<c:forEach var="roleDto" items="${person.roleDtos}">
								<c:if test="${roleDto.role.equals('Software Developer')}">
									<c:set var="sdChecked" value="checked"/>
								</c:if>
								<c:if test="${roleDto.role.equals('QA Engineer')}">
									<c:set var="qaChecked" value="checked"/>
								</c:if>
								<c:if test="${roleDto.role.equals('Fasttrack Instructor')}">
									<c:set var="ftChecked" value="checked"/>
								</c:if>
								<c:if test="${roleDto.role.equals('Project Manager')}">
									<c:set var="pmChecked" value="checked"/>
								</c:if>
							</c:forEach>
							<input type="checkbox" name="role" value="Software Developer" ${sdChecked}> Sofware Developer<br>
							<input type="checkbox" name="role" value="QA Engineer" ${qaChecked}> QA Engineer<br>
							<input type="checkbox" name="role" value="Fasttrack Instructor" ${ftChecked}> Fasttrack Instructor<br>
							<input type="checkbox" name="role" value="Project Manager" ${pmChecked}> Project Manager
						</fieldset>
				    </div>
				</div>        
				
			</fieldset>
		</div>
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>Address</legend>
				<div class="row">
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">Street<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="text" name="street" value="${person.addressDto.street}">
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Barangay<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="text" name="barangay" value="${person.addressDto.barangay}">
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">City<span class="required">*</span></div>
				            <div class="column column-8">
			            		<input type="text" name="city" value="${person.addressDto.city}">
				            </div>
				        </div>
				    </div>
				    <div class="column column-6">
				        <div class="row">
				            <div class="column column-4">House No.<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="text" name="houseNo" value="${person.addressDto.houseNo}">
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Subdivision</div>
				            <div class="column column-8">
				            	<input type="text" name="subdivision" value="${person.addressDto.subdivision}">
				            </div>
				        </div>
				        <div class="row">
				            <div class="column column-4">Zipcode<span class="required">*</span></div>
				            <div class="column column-8">
				            	<input type="text" name="zipcode" value="${person.addressDto.zipCode}">
				            </div>
				        </div>
				    </div>
				</div>
			</fieldset>
		</div>
		
		<div style="padding:1%">
			<fieldset style="padding:1%">
				<legend>Contact</legend>
				<c:if test="${!personId.isEmpty()}">
					<c:forEach var="contact" items="${person.contactDtos}">
						<c:if test="${contact.id.toString() != '0'}">
							<div class="row">
								<div class="column column-2">${contact.type}</div>
								<div class="column column-4">
									<input type="text" name="savedContactValue" value="${contact.value}">
								</div>
								<input type="hidden" name="contactId" value="${contact.id}">
								<input type="hidden" name="savedContactType" value="${contact.type}">
							</div>
						</c:if>
					</c:forEach>
				</c:if>
				<div class="row">
					<c:set var="cType" value=""/>
					<c:set var="cVal" value=""/>
					<c:forEach var="c" items="${person.contactDtos}">
						<c:if test="${c.id == 0 || c.id == null}">
							<c:set var="cType" value="${c.type}"/>
							<c:set var="cVal" value="${c.value}"/>
						</c:if>	
					</c:forEach>
					<div class="column column-2">Type</div>
					<div class="column column-4">
						<input type="radio" name="contactType" value="mobile" ${cType.toString().equals('MOBILE') ? 'checked' : ''}> Mobile
						<input type="radio" name="contactType" value="phone" ${cType.toString().equals('PHONE') ? 'checked' : ''}> Phone
					</div>
				</div>
				<div class="row">
					<div class="column column-2">Number</div>
					<div class="column column-4">
						<input type="text" name="contactValue" value="${cVal}">
					</div>
				</div>
			</fieldset>
		</div>
		<div style="padding:1%">
			<button type="submit" name="save" value="save">Save</button>
		</div>
		</fieldset>	
	</div>
	</form>

	<style>
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