<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>
<h2>Instructor management</h2>
<h3>Add Instructor</h3>
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">Form</div>
	</div>
	<div class="panel-body">
		<form:form method="post" modelAttribute="instructor" role="form">
			<div class="row form-group">
				<form:label path="name" for="name" class="control-label">Name: </form:label>
				<form:input path="name" type="text" class="form-control" id="name" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div class="row form-group">
				<form:label path="lastName" for="lname" class="control-label">Last name: </form:label>
				<form:input path="lastName" type="text" class="form-control" id="lname" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div class="row form-group">
				<form:label path="ssNumber" for="ssn" class="control-label">SS number: </form:label>
				<form:input path="ssNumber" type="text" class="form-control" id="ssn" />
				<form:errors path="ssNumber" class="text-danger" />
			</div>
			<div class="row form-group">
				<form:label path="idNumber" for="idn" class="control-label">ID number: </form:label>
				<form:input path="idNumber" type="text" class="form-control" id="idn" />
				<form:errors path="idNumber" class="text-danger" />
			</div>
			<div class="row form-group">
				<form:label path="email" for="email" class="control-label">Email: </form:label>
				<form:input path="email" type="email" class="form-control" id="email" />
				<form:errors path="email" class="text-danger" />
			</div>
			<div class="row form-group">
				<form:label path="telephone" for="tlf" class="control-label"> Telephone: </form:label>
				<form:input path="telephone" type="tel" class="form-control" id="tlf" />
				<form:errors path="telephone" class="text-danger" />
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
			<button type="reset" class="btn btn-danger">Clear</button>
		</form:form>
 	</div>
 </div>
</jsp:body>
</t:template>