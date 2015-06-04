<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
<!-- Quizas me excedi con la funcionalidad lo podemos hacer mas basico al principio -->
	<div class="intructorList">
		<h4>Bookings history</h4>
		<p>Your past bookings, <strong><c:out value="${name}" /></strong></p>
		<div id="help&action" class="row">
			<div id="menu" class="col-lg-2">
				<ul class="nav nav-pills nav-stacked">
					<li><a href="${pageContext.request.contextPath}/instructor/main.html">All</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/instructor/history.html">History</a>
					<li><a href="mailto:admin@natureadventure.uji.es">Contact boss</a></li>
				</ul>
			</div>
			<div id="help" class="col-lg-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Help!</div>
					</div>
					<div class="panel-body">
						<p>The following table shows the bookings you already teached.</p>
						<p>With the button the <span class="label label-info">Details</span>, you can see the full information of the booking and the client.</p>
					</div>
				</div>
			</div>
		</div>
		<h6 class="subtitle">List of the done bookings</h6>
		<t:tablaBookingsInstructor />
	</div>
</jsp:body>
</t:template>