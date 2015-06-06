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
            <th></th>
        </tr>
       </thead>
       <tbody>
        <c:forEach var="cli" items="${clientList}" >
        <tr <c:if test="${not empty id and (id eq cli.clientId)}">class="info"</c:if>> 
        	<td><c:out value="${cli.clientId}" /></td>
        	<td><c:out value="${cli.clientName}" /></td>
        	<td><c:out value="${cli.clientLastName}" /></td>
        	<td><c:out value="${cli.clientEmail}" /></td>
        	<td><c:out value="${cli.numberBooking}" /></td>
        	<td><a href="${pageContext.request.contextPath}/admin/clientManagement/details/${cli.clientId}.html"><span class="label label-info">Details</span></a></td>
        	<td><a href="#delectClientModal"  data-toggle="modal" data-target="#deleteClientModal${cli.clientId}"><span class="label label-danger">Delete</span></a>
        </tr>
	        <div class="modal fade" id="deleteClientModal${cli.clientId}" tabindex='-1' role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" id="deleteClientModalHead">
						<div class="row">
							<button type="button" class="close pull-right" data-dismiss="modal" style="padding-right: 4%; margin-top: 2px;">&times;</button>
							<h4 class="pull-left">Delete client <strong><c:out value="${cli.clientId}" /></strong></h4>
						</div>
					</div>
						<div class="modal-body" id="changeModalBody">
							<div style="font-size: 14px;">Are you sure, you want remove the client <strong><c:out value="${client.clientId}"/></strong>?</div>
							<div class="modalList">
								<div class="title">Client</div>
								<dl>
					              <dt>Name</dt><dd><c:out value="${cli.clientName}" /> <c:out value="${cli.clientLastName}" /></dd>
					              <dt>ID</dt><dd><c:out value="${cli.clientId}" /></dd>
					            </dl>
				            </div>
						</div>
						<div class="modal-footer" id="changeModalFooter">
							<p class="pull-left" style="font-size: 14px;"><strong class="high">Warning!</strong> <em>This action can not be undone.</em></p>
	          				<div class="pull-right">
		          				<div class="btn-group">
			            			<a href="${pageContext.request.contextPath}/admin/clientManagement/deleteClient/${cli.clientId}" class="btn btn-danger">Delete client</a>
			            			<button type="button" class="btn btn-primary" data-dismiss="modal">Cancel</button>
		            			</div>
							</div>
							<div style="clear:both;"></div>
						</div>
				</div>
			</div>
		</div>
	        
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