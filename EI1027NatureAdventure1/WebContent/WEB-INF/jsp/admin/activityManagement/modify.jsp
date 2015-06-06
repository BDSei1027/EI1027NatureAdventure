<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
	<jsp:body>
	<h4>Activity management</h4>
	<h6 class="subtitle">Details of the activity <strong>${activity.name}</strong> <small>${activity.idAct}</small></h6>
	
	<form:form method="post" modelAttribute="activity" role="form">
		<div class="form-group row">
			<form:label path="idAct" for="id" class="control-label col-lg-2">Identifier</form:label>
			<div class="col-lg-10">
				<form:hidden path="idAct" />
				<div class="form-control" id="id"><c:out value="${activity.idAct}" /></div>
				<form:errors path="idAct" id="iderr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="name" for="name" class="control-label col-lg-2">Name</form:label>
			<div class="col-lg-10">
				<form:input path="name" id="name" class="form-control" />
				<form:errors path="name" id="nameerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="level" for="lvl" class="control-label col-lg-2">Level</form:label>
			<div class="col-lg-10">
				<form:input path="level" id="lvl" type="number" class="form-control" />
				<form:errors path="level" id="lvlerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="schedule" for="sch" class="control-label col-lg-2">Schedule</form:label>
			<div class="col-lg-10">
				<form:input path="schedule" id="sch" class="form-control" />
				<form:errors path="schedule" id="scherr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="price" for="price" class="control-label col-lg-2">Price</form:label>
			<div class="col-lg-10">
				<form:input path="price" id="price" type="number" class="form-control" />
				<form:errors path="price" id="priceerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="place" for="place" class="control-label col-lg-2">Place</form:label>
			<div class="col-lg-10">
				<form:input path="place" id="place" type="text" class="form-control" />
				<form:errors path="place" id="placeerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="minimumGroup" for="min" class="control-label col-lg-2">Minimum Group</form:label>
			<div class="col-lg-10">
				<form:input path="minimumGroup" id="min" class="form-control" />
				<form:errors path="minimumGroup" id="minerr" class="text-danger" />
			</div>
		</div>
		<div class="form-group row">
			<form:label path="maximumGroup" for="max" class="control-label col-lg-2">Maximum Group</form:label>
			<div class="col-lg-10">
				<form:input path="maximumGroup" id="max" class="form-control" />
				<form:errors path="maximumGroup" id="maxerr" class="text-danger" />
			</div>
		</div>
		<div class="text-center">
			<div class="btn-group">
				<button type="submit" class="btn btn-success">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
				<c:if test="${activity.isActive()}"><a href="${pageContext.request.contextPath}/admin/activityManagement/disable/${activity.idAct}.html"><button type="button" class="btn btn-warning">Set inactive</button></a></c:if>
				<c:if test="${not activity.isActive()}"><a href="${pageContext.request.contextPath}/admin/activityManagement/enable/${activity.idAct}"><button type="button" class="btn btn-primary">Set active</button></a></c:if>
			</div>
		</div>
	</form:form>
</jsp:body>
</t:template>