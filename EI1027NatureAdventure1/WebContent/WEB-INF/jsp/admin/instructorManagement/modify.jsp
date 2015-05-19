<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	<h2>Instructor management</h2>
	<h3>Modify instructor <strong>${instructor.name} ${instructor.lastName}</strong> (${instructor.idNumber})</h3>
	<div class="col-lg-5">
		<form:form method="post" modelAttribute="instructor" role="form">
			<div class="form-group">
				<form:label path="name" for="nam" class="control-label">Name: </form:label>
				<form:input path="name" type="text" class="form-control" id="nam" />
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
				<form:input path="expireDate" type="datetime" class="form-control" id="edate" />
				<form:errors path="expireDate" />
			</div>
			
			<button type="submit" class="btn btn-primary">Submit</button>
		</form:form>
	</div>
		
	<div class="col-lg-1"> </div>
	<div class="col-lg-6">
			<h3>Activities from this instructor</h3>
			<div class="row">
				<p>Clicking on the following button you can add an activity for this instructor.<br>
		
				<a href="${pageContext.request.contextPath}/admin/instructorManagement/modify/addActivity"><button class="btn btn-primary">Add activity</button></a>
			</div>
		
		<h4>This instructor can teach</h4>
		<div class="row">
			<table class="table table-striped">
		 	<thead>
		 		<tr>
		 			<th class="col-lg-2">ID activity</th>
		 			<th class="col-lg-8">Name</th>
		 			<th class="col-lg-2"></th>
		 		</tr>
		 	</thead>
		 	<tbody>
		 		<c:forEach items="${activities}" var="act">
		 		<tr>
		 			<td>${act.idAct}</td>
		 			<td>${act.name}</td>
		 			
		 			<td><a href="removeActivity/${instructor.ssNumber}&${act.idAct}.html"><span class="label label-danger">Delete</span></a></td>
		 		</tr>
		 		</c:forEach>
		 		</tbody>
		 	</table>
	 	</div> <!-- row interno -->
	
	</div>
		
</jsp:body>
</t:template>