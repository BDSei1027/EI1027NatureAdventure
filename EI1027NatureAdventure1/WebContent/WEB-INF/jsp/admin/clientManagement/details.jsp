<%@ page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
	<c:if test="${not empty error}">
		<c:choose>
			<c:when test="${error eq 0}">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Email sended!</strong>.
			</div>
			</c:when>
			<c:when test="${error eq 1}">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Error!</strong> You had an error with the <strong class="high">email</strong>. Please check it and correct it.
			</div>
			</c:when>
		</c:choose>
	</c:if>
	<h4>Client management</h4>
	<h6 class="subtitle">Client  <strong><c:out value="${client.clientId}" /></strong></h6>
	<div style="width: 80%;">
		<form:form method="post" action="${pageContext.request.contextPath}/admin/clientManagement/details/${client.clientId}.html" modelAttribute="client" id="formClient">
			<div class="form-group row">
				<form:label path="clientId" for="cid" class="col-lg-2 control-label">ID Number</form:label>
				<div class="col-lg-10">
					<form:hidden path="clientId" />
					<div class="form-control" id="cid"><c:out value="${client.clientId}" /></div>
					<form:errors path="clientId" />
				</div>
			</div>
			<div class="form-group row">
				<form:label path="clientName" for="cname" class="col-lg-2 control-label">Name</form:label>
				<div class="col-lg-10">
					<form:input path="clientName" id="cname" type="text" class="form-control" />
					<form:errors paths="clientName" />
				</div>
			</div>
			<div class="form-group row">
				<form:label path="clientLastName" for="clast" class="col-lg-2 control-label">Last name</form:label>
				<div class="col-lg-10">
					<form:input path="clientLastName" id="clast" type="text" class="form-control" />
					<form:errors path="clientLastName" />
				</div>
			</div>
			<div class="form-group row">
				<form:label path="clientEmail" for="cemail" class="col-lg-2 control-label">Email</form:label>
				<div class="col-lg-10">
					<form:input path="clientEmail" id="cemail" type="email" class="form-control" />
					<form:errors path="clientEmail" />
				</div>
			</div>
			
			<div class="text-center">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary">Submit</button>
					<button type="reset" class="btn btn-danger">Clear</button>
					<a href="#emailModal" data-toggle="modal" data-target="#emailModal"><button type="button" class="btn btn-success">Contact</button></a>
				</div>
			</div>
		</form:form>
	</div>
        <!-- Modal -->
			<div class="modal fade" id="emailModal" tabindex='-1' role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header" id="emailModalHead">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title"> Email to <c:out value="${client.clientEmail}" /></h4>
						</div>
						<form:form id="emailForm" method="post" modelAttribute="email" role="form">
						<div class="modal-body" id="emailModalBody">
							<div class="form-group">
								<div class="input-group">
									<div class="input-group-addon">@</div>
									<!-- Hay que poner el valor por defecto al controlador -->
									<form:input path="to" class="form-control" type="email" placeholder="Enter the email"/>
								</div>
							</div>
							<div class="form-group">
								<form:label path="message" class="control-label" for="msg">Message:</form:label>
								<form:textarea path="message" class="form-control" rows="3" id="msg" placeholder="Type here your message..."/>
							</div>						
						</div>
						<div class="modal-footer">
							<div class="text-right">
								<div class="btn-group">
									<button class="btn btn-success" type="submit">Send</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
								</div>
							</div>
						</div>
						</form:form>
					</div>
				</div>
			</div>
			
</jsp:body>
</t:template>