<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>
	<c:if test="${not empty error}">
		<c:choose>
			<c:when test="${error eq 0}">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.instructormanage.alert.1" /> <strong><c:out value="${id}" />, <fmt:message key="admin.instructormanage.alert.add" /></strong> <fmt:message key="admin.instructormanage.alert.success" />.
			</div>
			</c:when>
			<c:when test="${error eq 1}">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.instructormanage.alert.1" /> <strong><c:out value="${id}" />, <fmt:message key="admin.instructormanage.alert.update" /></strong> <fmt:message key="admin.instructormanage.alert.success" />.
			</div>
			</c:when>
		</c:choose>
	</c:if>
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="breadcrumbs.admin.instrmanage" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	

	<h4><fmt:message key="admin.instructormanage.title" /></h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="admin.instructormanage.menu.all" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyActive.html"><fmt:message key="admin.instructormanage.menu.active" /></a>
				<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html"><fmt:message key="admin.instructormanage.menu.inactive" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/add.html"><fmt:message key="admin.instructormanage.menu.instructor" /></a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="admin.instructormanage.helptitle" /></h3>
				</div>
				<div class="panel-body">
					<p><fmt:message key="admin.instructormanage.helpcontent1" /></p>
					<p><fmt:message key="admin.instructormanage.helpcontent2" /> <span class="label label-info"><fmt:message key="admin.instructormanage.helpcontent.details" /></span></p>
					<p><fmt:message key="admin.instructormanage.helpcontent3" /></p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle"><fmt:message key="admin.instructormanage.list.title" /></h6>
	<t:tablaInstructors />

</jsp:body>
</t:template>