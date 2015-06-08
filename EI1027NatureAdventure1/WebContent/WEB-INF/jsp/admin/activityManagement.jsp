<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
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
			  <fmt:message key="admin.activitymanage.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.activitymanage.alert.add" /></strong> <fmt:message key="admin.activitymanage.alert.success" />.
			</div>
			</c:when>
			<c:when test="${error eq 1}">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.activitymanage.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.activitymanage.alert.updated" /></strong> <fmt:message key="admin.activitymanage.alert.success" />.
			</div>
			</c:when>
		</c:choose>
	</c:if>
	
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/activityManagement.html"><fmt:message key="breadcrumbs.admin.actmanage" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	
	<h4><fmt:message key="admin.activitymanage.title" /></h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="${pageContext.request.contextPath}/admin/activityManagement.html"><fmt:message key="admin.activitymanage.menu.all" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/onlyActive.html"><fmt:message key="admin.activitymanage.menu.active" /></a>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/onlyInactive.html"><fmt:message key="admin.activitymanage.menu.inactive" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/add.html"><fmt:message key="admin.activitymanage.menu.activity" /></a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="admin.activitymanage.helptitle" /></h3>
				</div>
				<div class="panel-body">
					<p><fmt:message key="admin.activitymanage.helpcontent1" /></p>
					<p><fmt:message key="admin.activitymanage.helpcontent2" /> <span class="label label-info"><fmt:message key="admin.activitymanage.helpcontent.details" /></span>. <fmt:message key="admin.activitymanage.helpcontent3" /> <span class="label label-success"><fmt:message key="admin.activitymanage.helpcontent.activate" /></span> <fmt:message key="admin.activitymanage.helpcontent4" /> <span class="label label-warning"><fmt:message key="admin.activitymanage.helpcontent.inactivate" /></span>.</p>
					<p><fmt:message key="admin.activitymanage.helpcontent5" /></p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle"><fmt:message key="admin.activitymanage.list.title" /></h6>
	<t:tablaActivity />

</jsp:body>
</t:template>