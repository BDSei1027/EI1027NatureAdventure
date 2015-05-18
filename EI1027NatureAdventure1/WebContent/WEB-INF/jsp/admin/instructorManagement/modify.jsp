<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	<h2>Instructor management</h2>
	<h3>Modify instructor ${instructor.name} ${instructor.lastname} (${instructor.idnumber})</h3>
	<div class="row">
		<div class="col-lg-4">
			<form:form method="post" modelAttribute="instructor" role="form">
				<div class="form-group">
					<form:label path="name" for="name" class="control-label">Name: </form:label>
					<form:input path="name" type="text" class="form-control" id="name" />
					<form:errors path="name" />
				</div>
				<div class="form-group">
					<form:label path="lastName" for="lname" class="control-label">Last name: </form:label>
					<form:input path="lastName" type="text" class="form-control" id="lname" />
					<form:errors path="lastName" />
				</div>
				<div class="form-group">
					<form:label path="ssNumber" for="ssn" class="control-label">SS number: </form:label>
					<form:input path="ssNumber" type="text" class="form-control" id="ssn" />
					<form:errors path="ssNumber" />
				</div>
				<div class="form-group">
					<form:label path="idNumber" for="idn" class="control-label">ID number: </form:label>
					<form:input path="idNumber" type="text" class="form-control" id="idn" />
					<form:errors path="idNumber" />
				</div>
				<div class="form-group">
					<form:label path="email" for="email" class="control-label">Email: </form:label>
					<form:input path="email" type="email" class="form-control" id="email" />
					<form:errors path="email" />
				</div>
				<div class="form-group">
					<form:label path="telephone" for="tlf" class="control-label"> Telephone: </form:label>
					<form:input path="telephone" type="tel" class="form-control" id="tlf" />
					<form:errors path="telephone" />
				</div>
				<div class="form-group">
					<form:label path="expireDate" for="edate">Expire date: </form:label>
					<form:input path="expireDate" type="date" class="form-control" id="edate" />
					<form:errors path="expireDate" />
				</div>
				
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
		</div>
	
		
</jsp:body>
</t:template>