<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>

	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html"><fmt:message key="breadcrumbs.admin.onlyinactive" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="breadcrumbs.admin.instr" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>

	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a> 
		<span class="fui-arrow-right"></span> 
		<a href="${pageContext.request.contextPath}/admin.html">Admin</a> 
		<span class="fui-arrow-right"></span> 
		<a href="${pageContext.request.contextPath}/admin/instructorManagement.html">Instructor</a> 
		<span class="fui-arrow-right"></span> 
		<a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html">Only inactive</a>
	</div>

	<h4><fmt:message key="admin.instructormanage.title" /></h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="admin.instructormanage.menu.all" /></a></li>
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyActive.html"><fmt:message key="admin.instructormanage.menu.active" /></a>
				<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html"><fmt:message key="admin.instructormanage.menu.inactive" /></a></li>
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