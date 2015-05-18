<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>
<h2>Instructor management</h2>
<h3>Add Instructor</h3>
<form:form method="post" modelAttribute="instructor" role="form">
	<div class="form-group">
		<form:label path="name" for="name" class="control-label">Name: </form:label>
		<form:input path="name" type="text" class="form-control" id="name" />
	</div>
	<div class="form-group">
		<form:label path="lastname" for="lname" class="control-label">Last name: </form:label>
		<form:input path="lastname" type="text" class="form-control" id="lname" />
	</div>
	<div class="form-group">
		<form:label path="ssnumber" for="ssn" class="control-label">SS number: </form:label>
		<form:input path="ssnumber" type="text" class="form-control" id="ssn" />
		<form:errors path="ssnumber" />
	</div>
	<div class="form-group">
		<form:label path="idnumber" for="idn" class="control-label">ID number: </form:label>
		<form:input path="idnumber" type="text" class="form-control" id="idn" />
		<form:errors path="idnumber" />
	</div>
	<div class="form-group">
		<form:label path="email" for="email" class="control-label">Email: </form:label>
		<form:input path="email" type="email" class="form-control" id="email" />
	</div>
	<div class="form-group">
		<form:label path="telephone" for="tlf" class="control-label"> Telephone: </form:label>
		<form:input path="telephone" type="tel" class="form-control" id="tlf" />
	</div>
	<div class="form-group">
		<form:label path="isactive" class="control-label">Ready for work: </form:label>
		<form:input path="isactive" type="checkbox" />
	</div>
	<button type="submit" class="btn btn-primary">Submit</button>
</form:form>
 
</jsp:body>
</t:template>