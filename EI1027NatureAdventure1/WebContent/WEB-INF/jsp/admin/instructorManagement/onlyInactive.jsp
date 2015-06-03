<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>

	<h4>Instructor management</h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement.html">All</a></li>
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyActive.html">Only active</a>
				<li role="presentation" class="active"><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html">Only inactive</a></li>
				<li role="presentation"><a href="${pageContext.request.contextPath}/admin/instructorManagement/add.html">New instructor</a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Help!</h3>
				</div>
				<div class="panel-body">
					<p>The following table lists all the instructor, which are in the data base.</p>
					<p>To see the activities of the instructor, click on <span class="label label-info">Edit</span></p>
					<p>In menu of the left, you can choose how do you want the data and add more instructors</p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle">List of the inactive instructors</h6>
	<t:tablaInstructors />

</jsp:body>
</t:template>