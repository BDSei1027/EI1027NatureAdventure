<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	<h2>Instructor management</h2>
	<hr>
	<h3>Modify instructor <strong>${instructor.name} ${instructor.lastName}</strong> (${instructor.idNumber})</h3>
	<div class="col-lg-5">
		<form:form method="post" modelAttribute="instructor" role="form">
			<div class="form-group">
				<form:label path="name" for="nam" class="control-label">Name: </form:label>
				<form:input path="name" type="text" class="form-control" id="nam" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="lastName" for="lname" class="control-label">Last name: </form:label>
				<form:input path="lastName" type="text" class="form-control" id="lname" />
				<form:errors path="lastName" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="ssNumber" for="ssn" class="control-label">SS number: </form:label>
				<form:input path="ssNumber" type="text" class="form-control" id="ssn" />
				<form:errors path="ssNumber" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="idNumber" for="idn" class="control-label">ID number: </form:label>
				<form:input path="idNumber" type="text" class="form-control" id="idn" />
				<form:errors path="idNumber" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="email" for="email" class="control-label">Email: </form:label>
				<form:input path="email" type="email" class="form-control" id="email" />
				<form:errors path="email" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="telephone" for="tlf" class="control-label"> Telephone: </form:label>
				<form:input path="telephone" type="tel" class="form-control" id="tlf" />
				<form:errors path="telephone" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="expireDate" for="edate">Expire date: </form:label>
				<form:input path="expireDate" type="datetime" class="form-control" id="edate" />
				<form:errors path="expireDate" class="text-danger" />
			</div>
						
			<button type="submit" class="btn btn-primary">Submit</button>
			<button type="reset" class="btn btn-danger">Clear</button>
			<c:choose>
	        	<c:when test="${instructor.isActive()}">
	        		<a href="${pageContext.request.contextPath}/admin/instructorManagement/disable/${instructor.ssNumber}.html"><button class="btn btn-warning">Set inactive</button></a>	
	        	</c:when>
	        	<c:otherwise>
	        		<a href="${pageContext.request.contextPath}/admin/instructorManagement/enable/${instructor.ssNumber}.html"><button class="btn btn-success">Set active</button></a>
	        	</c:otherwise>
	        </c:choose>
		</form:form>
	</div>
		
	<div class="col-lg-1"> </div>
	<div class="col-lg-6">
			<h3>Activities from this instructor</h3>
			<div class="row">
				<p>Clicking on the following button you can add an activity for this instructor.<br>
		
				<a href="${pageContext.request.contextPath}/admin/instructorManagement/addActivity/${instructor.ssNumber}.html"><button class="btn btn-primary">Add activity</button></a>
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
		 		<!-- Aviso MODAL -->
					<div class="modal fade" id="deleteActModal" tabindex='-1' role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header modal-header-warning" id="delecteActivityModalHead">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title"><span class="glyphicon glyphicon-alert"></span> Delete activity <c:out value="${act.name}" /></h4>
								</div>
								<div class="modal-body" id="deleteActivityModalBody">
									<div class="row">
										<p style="padding-left: 15px">Are you sure you want delete the following activity from this instructor?</p>
									</div>
									<div class="row">
										<div class="col-lg-6 text-center" style="padding-left: 25px; padding-right: 25px;">
											<p style="border-bottom: 1px solid #E5E5E5"><strong>Instructor</strong></p>
											<p><c:out value="${instructor.name}" /> <c:out value="${instructor.lastName}" /></p>
											<p>SS number: <c:out value="${instructor.ssNumber}" /></p>
										</div>
										<div class="col-lg-6 text-center" style="padding-left: 25px; padding-right: 25px;">
											<p style="border-bottom: 1px solid #E5E5E5"><strong>Activity</strong></p>
											<p><c:out value="${act.name}" /></p>
											<p>ID activity: <c:out value="${act.idAct}" /></p>
										</div>
									</div>
								</div>	
								<div class="modal-footer" id="deleteActivityModalFooter">
									<a href="${pageContext.request.contextPath}/admin/instructorManagement/removeActivity/${instructor.ssNumber}&${act.idAct}.html"><button type="button" class="btn btn-danger" data-dismiss="modal" id="deleteActBtnModal">Delete</button></a>
									<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								</div>
							</div>
						</div>
					</div>
		 		<tr>
		 			<td>${act.idAct}</td>
		 			<td>${act.name}</td>
		 			
		 			<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteActModal">Delete</button></td>
		 		</tr>
		 		</c:forEach>
		 		</tbody>
		 	</table>
	 	</div> <!-- row interno -->
	</div>
</jsp:body>
</t:template>