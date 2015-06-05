<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	<c:choose>
		<c:when test="${not empty error and error eq 0 }">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Success!</strong> Your changes have done correctly.
			</div>
		</c:when>
		<c:when test="${not empty error and error eq 1 }">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Warning!</strong> Your email is incorrect, rectify it please.
			</div>
		</c:when>
		<c:when test="${not empty error and error eq 2 }">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Warning!</strong> Your email or telephone are incorrect, rectify them please.
			</div>
		</c:when>
		<c:when test="${not empty error and error eq 2 }">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Error!</strong> The passwords have to be the same.
			</div>
		</c:when>
		
	</c:choose>
	<h4>Account details</h4>
	<h6 class="subtitle">Details about the user</h6>
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Personal information</h3>
				</div>
				<div class="panel-body">
					<c:choose>
						<c:when test="${user.type eq 1}">
							<p>Here it shows the information we have about you in the database.</p>
							<form:form modelAttribute="instructor" action="${pageContext.request.contextPath}/account/updateInstructor" method="post" role="form">
								<div class="form-group row">
									<form:label path="idNumber" for="iid" class="control-label col-lg-3">ID </form:label>
									<div class="col-lg-9">
										<div id="iid" class="form-control"><c:out value="${instructor.idNumber}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="ssNumber" for="issn" class="control-label col-lg-3">SSN </form:label>
									<div class="col-lg-9">
										<div id="issn" class="form-control"><c:out value="${instructor.ssNumber}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="name" for="iname" class="control-label col-lg-3">Name </form:label>
									<div class="col-lg-9">
										<div id="iname" class="form-control"><c:out value="${instructor.name}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="lastName" for="ilname" class="control-label col-lg-3">Last name </form:label>
									<div class="col-lg-9">
										<div id="ilname" class="form-control"><c:out value="${instructor.lastName}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="email" for="iemail" class="control-label col-lg-3">Email </form:label>
									<div class="col-lg-9">
										<form:input path="email" id="iemail" class="form-control" type="email" />
										<form:errors path="email" />
									</div>
								</div>
								<div class="form-group row">
									<form:label path="telephone" for="itlf" class="control-label col-lg-3">Telephone </form:label>
									<div class="col-lg-9">
										<form:input path="telephone" id="itlf" class="form-control" type="text" />
										<form:errors path="telephone" />
									</div>
								</div>
								<div class="text-center">
									<div class="btn-group">
										<button type="submit" class="btn btn-primary">Change</button>
										<button type="reset" class="btn btn-danger">Clear</button>
									</div>
								</div>
							</form:form>
						</c:when>
						<c:when test="${user.type eq 2}">
							<p>Here it shows the information we have about you in the database.</p>
							<form:form modelAttribute="client" method="post" action="${pageContext.request.contextPath}/account/updateClient" role="form">
								<div class="form-group row">
									<form:label path="clientId" for="cid" class="control-label col-lg-3">ID </form:label>
									<div class="col-lg-9">
										<div id="cid" class="form-control"><c:out value="${client.clientId}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="clientName" for="cname" class="control-label col-lg-3">Name </form:label>
									<div class="col-lg-9">
										<div id="cname" class="form-control"><c:out value="${client.clientName}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="clientLastName" for="clname" class="control-label col-lg-3">Last name </form:label>
									<div class="col-lg-9">
										<div id="clname" class="form-control"><c:out value="${client.clientLastName}" /></div>
									</div>
								</div>
								<div class="form-group row">
									<form:label path="clientEmail" for="cemail" class="control-label col-lg-3">Email </form:label>
									<div class="col-lg-9">
										<form:input path="clientEmail" id="cemail" class="form-control" type="email" />
										<form:errors path="clientEmail" />
									</div>
								</div>
								<div class="text-center"><button type="submit" class="btn btn-primary">Change email</button></div>
							</form:form>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div> <!-- col -->
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">User's information</h3>
				</div>
				<div class="panel-body">
					<div class="form-group row">
						<label class="control-label col-lg-2">Username </label>
						<div class="col-lg-10">
							<div class="form-control">${user.user}</div>
						</div>
					</div>
					<form:form modelAttribute="doublepassword" method="post" action="${pageContext.request.contextPath}/account/updateAuth" role="form">
						<div class="form-group row">
							<form:label path="password" for="p1" class="col-lg-2 control-label">Password </form:label>
							<div class="col-lg-10">
								<form:input path="password" id="p1" type="password" class="form-control" />
								<form:errors path="password" />
							</div>
						</div>
						<div class="form-group row">
							<form:label path="confirmation" for="p2" class="col-lg-2 control-label"> Confirmation </form:label>
							<div class="col-lg-10">
								<form:input path="confirmation" id="p2" type="password" class="form-control" />
								<form:errors path="confirmation" />	
							</div>
						</div>
						<div class="text-center">
							<div class="btn-group">
								<button type="submit" class="btn btn-primary">Change password</button>
								<button type="reset" class="btn btn-danger">Reset</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div> <!-- row -->
</jsp:body>
</t:template>
