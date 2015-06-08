<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:template>
	<jsp:body>
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/activityManagement/modify/${activity.idAct}.html"><fmt:message key="breadcrumbs.admin.act.details" /> <em>${activity.name}</em></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/activityManagement.html"><fmt:message key="breadcrumbs.admin.act" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	
	<h4><fmt:message key="admin.activitymanage.title" /></h4>
	<h6 class="subtitle"><fmt:message key="admin.activitymanage.modify.title" /> <strong>${activity.name}</strong> <small>${activity.idAct}</small></h6>
	
	<form:form method="post" modelAttribute="activity" role="form">
		<div class="form-group row">
			<form:label path="idAct" for="id" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.modify.id" /></form:label>
			<div class="col-lg-10">
				<form:hidden path="idAct" />
				<div class="form-control" id="id"><c:out value="${activity.idAct}" /></div>
				<form:errors path="idAct" id="iderr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="name" for="name" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.name" /> <em>(EN)</em></form:label>
			<div class="col-lg-10">
				<form:input path="name" id="name" class="form-control" />
				<form:errors path="name" id="nameerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="nombre" for="nom" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.name" /> <em>(ES)</em></form:label>
			<div class="col-lg-10">
				<form:input path="nombre" id="nom" class="form-control" />
				<form:errors path="nombre" id="nomerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="level" for="lvl" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.level" /></form:label>
			<div class="col-lg-10">
				<form:input path="level" id="lvl" type="number" class="form-control" />
				<form:errors path="level" id="lvlerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="schedule" for="sch" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.schedule" /></form:label>
			<div class="col-lg-10">
				<form:input path="schedule" id="sch" class="form-control" />
				<form:errors path="schedule" id="scherr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="price" for="price" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.price" /></form:label>
			<div class="col-lg-10">
				<form:input path="price" id="price" type="number" class="form-control" />
				<form:errors path="price" id="priceerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="place" for="place" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.place" /></form:label>
			<div class="col-lg-10">
				<form:input path="place" id="place" type="text" class="form-control" />
				<form:errors path="place" id="placeerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="minimumGroup" for="min" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.ming" /></form:label>
			<div class="col-lg-10">
				<form:input path="minimumGroup" id="min" class="form-control" />
				<form:errors path="minimumGroup" id="minerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="maximumGroup" for="max" class="control-label col-lg-2"><fmt:message key="admin.activitymanage.addactivity.form.maxg" /></form:label>
			<div class="col-lg-10">
				<form:input path="maximumGroup" id="max" class="form-control" />
				<form:errors path="maximumGroup" id="maxerr" class="text-danger" />
			</div>
		</div>
		<div class="row form-group">
			<div class="col-lg-12">
				<form:label path="description" for="description" class="control-label"><fmt:message key="admin.activitymanage.addactivity.form.description" /> <em>(EN)</em></form:label>
				<form:textarea path="description" class="form-control" rows="3" />
				<form:errors path="description" class="text-danger" />
			</div>
		</div>
		<div class="row form-group">
			<div class="col-lg-12">
				<form:label path="descripcion" for="descripcion" class="control-label"><fmt:message key="admin.activitymanage.addactivity.form.description" /> <em>(ES)</em></form:label>
				<form:textarea path="descripcion" class="form-control" rows="3" />
				<form:errors path="descripcion"  class="text-danger" />
			</div>
		</div>
		<div class="text-center">
			<div class="btn-group">
				<button type="submit" class="btn btn-success"><fmt:message key="admin.activitymanage.addactivity.form.submit" /></button>
				<button type="reset" class="btn btn-danger"><fmt:message key="admin.activitymanage.addactivity.form.clear" /></button>
				<c:if test="${activity.isActive()}"><a href="${pageContext.request.contextPath}/admin/activityManagement/disable/${activity.idAct}.html"><button type="button" class="btn btn-warning"><fmt:message key="admin.activitymanage.modify.btn.inact" /></button></a></c:if>
				<c:if test="${not activity.isActive()}"><a href="${pageContext.request.contextPath}/admin/activityManagement/enable/${activity.idAct}"><button type="button" class="btn btn-primary"><fmt:message key="admin.activitymanage.modify.btn.active" /></button></a></c:if>
			</div>
		</div>
	</form:form>
</jsp:body>
</t:template>