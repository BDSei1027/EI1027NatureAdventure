<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<t:template>
	<jsp:body>
		<!-- Alerta -->
		<c:if test="${not empty errors}">
			<!-- Alerta -->
		    <div class="alert alert-danger">
		        <a href="#" class="close" data-dismiss="alert">&times;</a>
		        <fmt:message key="recover.auth.alert" />
		    </div>
		</c:if>
		<h4><fmt:message key="recover.auth.title" /></h4>
		<div class="padding" style="padding-left:10 px">
			<p><fmt:message key="recover.auth.message.1" /></p>
			<p><fmt:message key="recover.auth.message.2" /></p>
		
			<form:form  method="post" modelAttribute="doublepassword" action="">
				<div class="row recoverauth">
					<h6><fmt:message key="recover.auth.title" /></h6>
					<p><fmt:message key="recover.auth.token" /> <strong class="high"><c:out value="${token}" /></strong></p>
				<div class="form-group">
					<form:label path="password" class="control-label" for="p1"><fmt:message key="recover.auth.enter" /> </form:label>
					<form:input path="password" class="form-control" id="p1" type="password" />
					<form:errors path="password"/>
				</div>
				<div class="form-group">
					<form:label path="confirmation" for="p2"><fmt:message key="recover.auth.confirm" /> </form:label>
					<form:input path="confirmation" class="form-control" id="p2" type="password" />
					<form:errors path="confirmation" />
				</div>
				<button type="submit" class="btn btn-primary pull-right"><fmt:message key="recover.auth.submit" /></button>
				</div>
			</form:form>
			<p class="text-right"><fmt:message key="recover.auth.rem" /></p>
			<h5 class="text-right" style="margin-top: 20px;">NatureAdventure</h5>
		</div>
	</jsp:body>
</t:template>