<%@ tag description="Tabla para mostrar reservas a los instructores"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<table id="assignedBookings"
		  data-toggle="table" 
	      data-classes="table table-no-bordered"
	      data-query-params="queryParams"
	      data-pagination="true"
	      data-search="true">
			<thead>
				<tr>
					<th data-field="idbooking" data-sortable="true">ID</th>
					<th data-field="date" data-sortable="true">Date</th>
					<th data-field="schedule" data-sortable="true">Schedule</th>
					<th data-field="place" data-sortable="true">Place</th>
					<th data-field="activity" data-sortable="true">Activity</th>
					<th data-field="level" data-sortable="true">Level</th>
					<th data-field="groupsize" data-sortable="true">Group size</th>
					<th></th>
					<!--<th></th> -->
				</tr>
			</thead>
			<tbody>
				<!-- Solo tiene que haber bookins para hacer no las pasadas -->
				<c:forEach items="${bookingList}" var="bookings">
					<tr>
						<td><c:out value="${bookings.idBooking}" /></td>
						<td><c:out value="${bookings.dateActivity}" /></td>
						<td><c:out value="${bookings.schedule}" /></td>
						<td><c:out value="${bookings.place}" /></td>
						<td><c:out value="${bookings.nameActivity}" /></td>
						<td><c:out value="${bookings.level}" /></td>
						<td><c:out value="${bookings.groupSize}" /></td>
						<td><a href="${pageContext.request.contextPath}/instructor/details/${bookings.idBooking}.html"><span class="label label-info">Details</span></a></td>
						<!-- <td><a href="#reportModal" data-toggle="modal" data-target="#reportModal"><span class="label label-warning">Report</span></a></td>-->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	
	<!-- Scripts para tabla -->
	<script>
		function queryParams() {
		    return {
		        type: 'owner',
		        sort: 'updated',
		        direction: 'desc',
		        per_page: 10,
		        page: 1
		    };
		}
	</script>
	
	<script>
	    var $table = $('#assignedBookings');
	    $(function () {
	    });
	</script>