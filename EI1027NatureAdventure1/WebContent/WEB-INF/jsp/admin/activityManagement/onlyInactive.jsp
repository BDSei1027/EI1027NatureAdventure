<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>

	<h4><fmt:message key="admin.activitymanage.title" /></h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement.html"><fmt:message key="admin.activitymanage.menu.all" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/onlyActive.html"><fmt:message key="admin.activitymanage.menu.active" /></a>
				<li class="active"><a href="${pageContext.request.contextPath}/admin/activityManagement/onlyInactive.html"><fmt:message key="admin.activitymanage.menu.inactive" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/add.html"><fmt:message key="admin.activitymanage.menu.activity" /></a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="admin.activitymanage.helptitle" /></h3>
				</div>
				<div class="panel-body">
					<p><fmt:message key="admin.activitymanage.onlyInactive.text1" /></p>
					<p><fmt:message key="admin.activitymanage.helpcontent2" /> <span class="label label-info"><fmt:message key="admin.activitymanage.helpcontent.details" /></span>.</p>
					<p><fmt:message key="admin.activitymanage.helpcontent5" /></p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle"><fmt:message key="admin.activitymanage.onlyInactive.subtitle" /></h6>
	<t:tablaActivity />

</jsp:body>
</t:template>