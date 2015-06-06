<%@ tag description="Tabla para mostrar reservas a las reservas"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty error and (error eq 0)}"><c:set scope="page" var="type" value="success" /></c:if>
<c:if test="${not empty error and (error eq 1)}"><c:set scope="page" var="type" value="info" /></c:if>
<c:if test="${not empty error and (error eq 2)}"><c:set scope="page" var="type" value="warning" /></c:if>

<table id="Bookings"
  data-toggle="table" 
     data-classes="table table-no-bordered"
     data-query-params="queryParams"
     data-pagination="true"
     data-search="true">
	<thead>
		<tr>
			<th data-field="innerid" data-sortable="true">Inner ID</th>
			<th data-field="id" data-sortable="true">Booking ID</th>
			<th data-field="act" data-sortable="true">Activity code</th>
			<th data-field="client" data-sortable="true">Client</th>
			<th data-field="group" data-sortable="true">Group size</th>
			<th data-field="date" data-sortable="true">Creation date</th>
			<th data-field="status" data-sortable="true">Status</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${bookingList}" var="bookings">
			<tr class=<c:if test="${not empty id and (id eq activity.idAct)}">"${type}"</c:if>>
				<td><c:out value="${bookings.innerIdBooking}" /></td>
				<td>
					<c:choose>
					<c:when test="${bookings.idBooking ne 0}"><c:out value="${bookings.idBooking}" /></c:when>
					<c:otherwise>...</c:otherwise>
					</c:choose>
				</td>
				<td><c:out value="${bookings.idAct}" /></td>
				<td><c:out value="${bookings.clientId}" /></td>
				<td><c:out value="${bookings.groupSize}" /></td>
				<td><c:out value="${bookings.dateCreation}" /></td>
				<td>
					<c:choose>
						<c:when test="${bookings.status eq 0}"><span class="label label-warning">Pending</span></c:when>
						<c:when test="${bookings.status eq 1}"><span class="label label-success">Accepted</span></c:when>
						<c:when test="${bookings.status eq 2}"><span class="label label-danger">Declined</span></c:when>
					</c:choose>
				</td>
				<td><a href="${pageContext.request.contextPath}/admin/bookingManagement/details/${bookings.innerIdBooking}.html"><span class="label label-info">Details</span></a></td>
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