<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>

	<!-- Estan los campos de Form:form preparados no se porque falla -->
	<h4><fmt:message key="admin.instructormanage.title" /></h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="admin.instructormanage.menu.all" /></a></li>
				<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyActive.html"><fmt:message key="admin.instructormanage.menu.active" /></a>
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html"><fmt:message key="admin.instructormanage.menu.inactive" /></a></li>
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement/add.html"><fmt:message key="admin.instructormanage.menu.instructor" /></a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="admin.instructormanage.helptitle" /></h3>
				</div>
				<div class="panel-body">
					<p><fmt:message key="admin.instructormanage.onlyActive.text1" /></p>
					<p><fmt:message key="admin.instructormanage.helpcontent2" /> <span class="label label-info"><fmt:message key="admin.instructormanage.helpcontent.details" /></span></p>
					<p><fmt:message key="admin.instructormanage.helpcontent3" /></p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle"><fmt:message key="admin.instructormanage.onlyActive.subtitle" /></h6>
	<t:tablaInstructors />

</jsp:body>
</t:template>