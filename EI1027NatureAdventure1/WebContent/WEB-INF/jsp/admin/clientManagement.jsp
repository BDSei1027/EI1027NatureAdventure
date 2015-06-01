<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
	<h2>Client management</h2>
	<div id="action&help" class="row">
		<!-- <div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="${pageContext.request.contextPath}/admin/instructorManagement.html">All</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyActive.html">Only active</a>
				<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/onlyInactive.html">Only inactive</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/add.html">New instructor</a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10"> -->
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Help!</h3>
				</div>
				<div class="panel-body">
					<p>The following table lists all the clients, which are in the data base.</p>
					<p>To see details of the client, click on <span class="label label-info">Details</span>.</p>
					<p>If you need to contact with the client, go to <span class="label label-info">Details</span> and then click on Contact.</p>
					<!-- <p>If you need contact with a client, click on <span class="label label-success">Contact</span> to send an email</p> -->
					<!-- <p>In menu of the left, you can choose how do you want the data and add more instructors</p> -->
				</div>
			</div>
		<!-- </div> -->
	</div>
	<h3>List of the clients</h3>
	<t:tablaClients />
</jsp:body>
</t:template>