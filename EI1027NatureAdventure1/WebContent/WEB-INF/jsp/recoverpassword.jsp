<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
	<jsp:body>
	<c:if test="${not empty errors}">
		<!-- Alerta -->
	    <div class="alert alert-danger">
	        <a href="#" class="close" data-dismiss="alert">&times;</a>
	        <strong>Error!</strong> A problem has been occurred while submitting your data.
	    </div>
	</c:if>
	<h4>Recovering your password</h4>
    <!-- Panel -->
	<div class="panel panel-warning" style="width:570px; margin:auto;">
		<div class="panel-heading">
			<h6 class="panel-title">Recovery Password Form</h6>
		</div>
		<div class="panel-body">
			<p>We saw you have problems remembering your password. Introduce your email in the following field
			and we will send you an email to restore your password.</p>

			<form:form method="post" action="#" modelAttribute="email" role="form">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">@</div>
						<form:input path="to" class="form-control" type="email" placeholder="Your email" />
					</div>
					<c:set var="errors"><form:errors path="to"/></c:set>
				</div>
				<button class="btn btn-warning pull-right" type=submit>Submit</button>
			</form:form>
		</div>
		<div class="panel-footer">
			<p style="font-size: 12px;">The email will be valid for the next <strong>24 hours</strong>. After this time the link will be disabled.</p>
			<p style="font-size: 12px;">If you do not recive the email, check in your spam folder and the junk box.</p>
		</div>
	</div>
	</jsp:body>
</t:template>