<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 


<t:template>
<jsp:body>
<!-- Quizas me excedi con la funcionalidad lo podemos hacer mas basico al principio -->
	<div class="intructorList">
		<h2>Welcome <c:out value="${instructor.name}" /></h2>
		<p>Welcome instructor, this is your section, where you can see the bookings that
		the admin assign you.</p>
		
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title">Help!</div>
			</div>
			<div class="panel-body">
				<p>With the buttons in the table you can</p>
				<p>  <span class="label label-info">Details</span> shows you the full information of the booking and the client.</p>
				<p class="lead text-center">You have <span class="badge"><c:out value="${numbookings}" /> bookings</span> assigned</p>
			</div>
		</div>
		
		<t:tablaBookingsInstructor />
	</div>
</jsp:body>
</t:template>