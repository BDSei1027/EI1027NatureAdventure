<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/BookingManagement/accepted.html"><fmt:message key="breadcrumbs.admin.book.accepted" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/BookingManagement.html"><fmt:message key="breadcrumbs.admin.book" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	
	<h4><fmt:message key="admin.bookingmanage.title" /></h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="${pageContext.request.contextPath}/admin/bookingManagement.html"><fmt:message key="admin.bookingmanage.menu.all" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/bookingManagement/pendings.html"><fmt:message key="admin.bookingmanage.menu.pendings" /> <span class="badge"><c:out value="${pendings}" /></span></a>
				<li class="active"><a href="${pageContext.request.contextPath}/admin/bookingManagement/accepted.html"><fmt:message key="admin.bookingmanage.menu.accepted" /></a></li>
				<li><a href="${pageContext.request.contextPath}/admin/bookingManagement/declined.html"><fmt:message key="admin.bookingmanage.menu.declined" /></a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="admin.bookingmanage.helptitle" /></h3>
				</div>
				<div class="panel-body">
					<p><fmt:message key="admin.bookingmanage.onlyActive.msg1" /> </p>
					<p><fmt:message key="admin.bookingmanage.helpcontent2" />  <span class="label label-info"><fmt:message key="admin.bookingmanage.helpcontent.details" /> </span></p>
					<p><fmt:message key="admin.bookingmanage.helpcontent3" /> </p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle"><fmt:message key="admin.bookingmanage.list.title" /> </h6>
	<t:tablaBooking />

</jsp:body>
</t:template>