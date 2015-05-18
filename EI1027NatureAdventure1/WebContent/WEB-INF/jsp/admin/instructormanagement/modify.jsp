<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:base>
<jsp:body>
	<h2>Instructor management</h2>
	<h3>Modify instructor ${instructor.name} ${instructor.lastname} (${instructor.idnumber})</h3>
	<div class="row">
		<div class="col-lg-4">
			<form:form method="post" modelAttribute="instructor" role="form">
				<div class="form-group">
					<form:label path="name" for="name" class="control-label">Name: </form:label>
					<p class="form-control-static">${instructor.name}</p>
				</div>
				<div class="form-group">
					<form:label path="lastname" for="lname" class="control-label">Last name: </form:label>
					<p class="form-control-static">${instructor.lastname}</p>
				</div>
				<div class="form-group">
					<form:label path="ssnumber" for="ssn" class="control-label">SS number: </form:label>
					<p class="form-control-static">${instructor.ssnumber}</p>
				</div>
				<div class="form-group">
					<form:label path="idnumber" for="idn" class="control-label">ID number: </form:label>
					<p class="form-control-static">${instructor.idnumber}</p>
				</div>
				<div class="form-group">
					<form:label path="email" for="email" class="control-label">Email: </form:label>
					<form:input path="email" type="email" class="form-control" id="email" placeholder="${instructor.email}" />
				</div>
				<div class="form-group">
					<form:label path="telephone" for="tlf" class="control-label"> Telephone: </form:label>
					<form:input path="telephone" type="tel" class="form-control" id="tlf" placeholder="${instructor.telephone}" />
				</div>
				<div class="form-group">
					<form:label path="expiredate" for="edate">Expire date: </form:label>
					<form:input path="expiredate" type="date" class="form-control" id="edate" placeholder="${instructor.expiredate}"/>
				</div>
				<div class="form-group">
					<form:label path="isactive" class="control-label">Can work?: </form:label>
					<form:input path="isactive" type="checkbox" />
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form:form>
		</div>
	
		<div class="col-lg-2"> </div>
		<div class="col-lg-6">
			<h3>Activities from this instructor</h3>
			<div class="row">
			<p>Clicking on the following button you can add an activity for this instructor.<br>
			<div style="text-align: center"></div><a href="#"><button class="btn btn-primary">Add activity</button></a></div>
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
			 			<td>${act.idact}</td>
			 			<td>${act.name}</td>
			 			<td><span class="label label-danger">Delete</span></td>
			 		</tr>
			 		</c:forEach>
			 		</tbody>
			 	</table>
		 	</div> <!-- row interno -->
 		</div>
 	</div> <!-- ROW -->
</jsp:body>
</t:base>