<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>

	<!-- Estan los campos de Form:form preparados no se porque falla -->
	<h4>Booking management</h4>
	<div id="actionsHelp" class="row">
		<div id="action" class="col-lg-2">
			<ul class="nav nav-pills nav-stacked">
				<li><a href="${pageContext.request.contextPath}/admin/bookingManagement.html">All</a></li>
				<li class="active"><a href="${pageContext.request.contextPath}/admin/bookingManagement/pendings.html">Pendings <span class="badge"><c:out value="${pendings}" /></span></a>
				<li><a href="${pageContext.request.contextPath}/admin/bookingManagement/accepted.html">Accepted</a></li>
				<li><a href="${pageContext.request.contextPath}/admin/bookingManagement/declined.html">Declined</a></li>
			</ul>
		</div>
		<div id="help" class="col-lg-10">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">Help!</h3>
				</div>
				<div class="panel-body">
					<p>The following table lists only the pending bookings.</p>
					<p>To see more information of the booking click on <span class="label label-info">Details</span></p>
					<p>In menu of the left, you can choose which booking will be listed, the bookins are pending to revise, the accepted bookings
					or the declined bookings.</p>
				</div>
			</div>
		</div>
	</div>
	<h6 class="subtitle">List of the bookings</h6>
	<t:tablaBooking />

</jsp:body>
</t:template>