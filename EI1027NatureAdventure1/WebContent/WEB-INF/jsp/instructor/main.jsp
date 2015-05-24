<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
				<p> <span class="label label-warning">Report</span> is for report to the admin that you have some trouble with this
				booking. </p>
			</div>
		</div>
		
		<table id="assignedBookings">
			<thead>
				<tr>
					<th data-field="idbooking">ID</th>
					<th data-field="date">Date</th>
					<th data-field="schedule">Schedule</th>
					<th data-field="place">Place</th>
					<th data-field="activity">Activity</th>
					<th data-field="groupsize">Group size</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${item}" var="bdata">
					<!-- MODAL para report -->
					<div class="modal fade" id="reportModal" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Report booking</h4>
								</div>
								<div class="modal-body">
								<!-- proximamente cambiarlo por un select -->
									<p>Are you sure you want report the booking <c:out value="${bdata.idbooking}" /> to the administrator?</p>
									<form:form method="post" model-attribute="String" role="form">
										<div class="form-group">
											<div class="row">
												<form:label path="algo" for="excuse">Please write why you want to report this booking.</form:label>
											</div>
											<div class="row">
												<form:textarea path="algo" id="excuse"/>
											</div>
										</div>
										<button type="submit" class="btn btn-danger">Submit</button>										
									</form:form>
								</div>
							</div>
						</div>
					</div>
					<tr>
						<td><c:out value="${bdata.idbooking}" /></td>
						<td><c:out value="${bdata.date}" /></td>
						<td><c:out value="${bdata.schedule}" /></td>
						<td><c:out value="${bdata.place}" /><td>
						<td><c:out value="${bdata.activity}" /></td>
						<td><c:out value="${bdata.size}" /></td>
						<td><a href=""><span class="label label-info">Details</span></a></td>
						<td><a href="#reportModal" data-toggle="modal" data-target="#reportModal"><span class="label label-warning">Report</span></a></td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</jsp:body>
</t:template>