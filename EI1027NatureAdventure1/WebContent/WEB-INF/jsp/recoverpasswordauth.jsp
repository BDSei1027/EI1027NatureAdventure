<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
	<jsp:body>
		<!-- Alerta -->
		<c:if test="${not empty errors}">
			<!-- Alerta -->
		    <div class="alert alert-danger">
		        <a href="#" class="close" data-dismiss="alert">&times;</a>
		        <strong>Error!</strong> The passwords are not the same. Correct it please.
		    </div>
		</c:if>
		<h2>Recovering your password</h2>
		<div class="padding" style="padding-left:10 px">
			<p>We are glad to see that you recived the email to reset your password.</p>
			<p>Please, type your new password in the next form and confirm it, typing another time the password.</p>
		
			<form:form  method="post" modelAttribute="doublepassword" action="">
				<div class="row recoverauth">
					<h4>Reset your password</h4>
					<p>Your token is: <strong><c:out value="${token}" /></strong></p>
				<div class="form-group">
					<form:label path="password" class="control-label" for="p1">Enter your new password </form:label>
					<form:input path="password" class="form-control" id="p1" type="password" />
					<form:errors path="password"/>
				</div>
				<div class="form-group">
					<form:label path="confirmation" for="p2">Confirm your new password </form:label>
					<form:input path="confirmation" class="form-control" id="p2" type="password" />
					<form:errors path="confirmation" />
				</div>
				<button type="submit" class="btn btn-primary pull-right">Submit</button>
				</div>
			</form:form>
			<p class="text-right">The next time remember your password. Thank you.</p>
			<h4 class="text-right" style="padding-right:2%;">NatureAdventure</h4>
		</div>
	</jsp:body>
</t:template>