<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:body>
	<c:if test="${not empty errors}">
		<!-- Alerta -->
	    <c:choose>
			<c:when test="${error eq 0}">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong><fmt:message key="passwordrecovery.success" /> </strong>.
			</div>
			</c:when>
			<c:when test="${error eq 2}">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="passwordrecovery.fail" /> 
			</div>
			</c:when>
		</c:choose>
	</c:if>
	<h4><fmt:message key="recover.title" /></h4>
    <!-- Panel -->
	<div class="panel panel-warning" style="width:570px; margin:auto;">
		<div class="panel-heading">
			<h6 class="panel-title"><fmt:message key="recover.panel.title" /></h6>
		</div>
		<div class="panel-body">
			<p><fmt:message key="recover.panel.msg" /></p>

			<form:form method="post" action="#" modelAttribute="email" role="form">
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">@</div>
						<form:input path="to" class="form-control" type="email" placeholder="Your email" />
					</div>
					<c:set var="errors"><form:errors path="to"/></c:set>
				</div>
				<button class="btn btn-warning pull-right" type=submit><fmt:message key="recover.panel.submit" /></button>
			</form:form>
		</div>
		<div class="panel-footer">
			<p style="font-size: 12px;"><fmt:message key="recover.panel.foot.1" /></p>
			<p style="font-size: 12px;"><fmt:message key="recover.panel.foot.2" /></p>
		</div>
	</div>
	</jsp:body>
</t:template>