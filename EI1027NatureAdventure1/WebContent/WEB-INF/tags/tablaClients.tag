<%@ tag description="Tabla para mostrar clientes"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<!--<c:set scope="page" var="add" value="203203203203" /> -->
	<!-- TABLA -->
<table id="clientTable"  
  data-toggle="table" 
     data-classes="table table-no-bordered"
     data-query-params="queryParams"
     data-pagination="true"
     data-search="true">
	<thead>
        <tr>
            <th data-field="id" data-sortable="true">ID Number</th>
            <th data-field="name" data-sortable="true">Name</th>
            <th data-field="lname" data-sortable="true">Last name</th>
            <th data-field="email" data-sortable="true">Email</th>
            <th data-field="bookings" data-sortable="true">Bookings</th>
            <th></th>
        </tr>
       </thead>
       <tbody>
        <c:forEach var="cli" items="${clientList}" >
        <tr <c:if test="${not empty add and (add eq cli.clientId)}">class="success"</c:if>> 
        	<td><c:out value="${cli.clientId}" /></td>
        	<td><c:out value="${cli.clientName}" /></td>
        	<td><c:out value="${cli.clientLastName}" /></td>
        	<td><c:out value="${cli.clientEmail}" /></td>
        	<td><c:out value="${cli.getNumberBookings()}" /></td>
        	<td><a href="${pageContext.request.contextPath}/admin/clientManagement/details/${cli.clientId}.html"><span class="label label-info">Details</span></a></td>
        </tr>
        </c:forEach>
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
    var $table = $('#clientTable');
    $(function () {
    });
</script>