<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>

	<h4>Activity management</h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement.html">All</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/admin/activityManagement/onlyActive.html">Only active</a>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/onlyInactive.html">Only inactive</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/activityManagement/add.html">New activity</a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Help!</h3>
				</div>
				<div class="panel-body">
					<p>The following table lists only the active activities.</p>
					<p>To see the instructors which are assigned to the activity, click on <span class="label label-info">Edit</span>.</p>
					<p>In menu of the left, you can choose how do you want the data and add more activities.</p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle">List of the active activities</h6>
	<t:tablaActivity />

</jsp:body>
</t:template>