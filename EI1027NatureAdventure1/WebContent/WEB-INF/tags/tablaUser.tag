<%@ tag description="Tabla para mostrar a los usuarios"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!--<c:set scope="page" var="add" value="203203203203" /> -->
	<!-- TABLA -->
<table id="userTable"  
  data-toggle="table" 
     data-classes="table table-no-bordered"
     data-query-params="queryParams"
     data-pagination="true"
     data-search="true">
	<thead>
        <tr>
            <th data-field="user" data-sortable="true">Username</th>
            <th data-field="name" data-sortable="true">Name</th>
            <th data-field="type" data-sortable="true">Type</th>
            <th data-field="lang" data-sortable="true">Language</th>
            <th></th>
        </tr>
       </thead>
       <tbody>
        <c:forEach var="user" items="${userList}" >
        <tr>
        	<td><c:out value="${user.user}" /></td>
        	<td><c:out value="${user.name}" /></td>
        	<td><c:out value="${user.type}" /></td>
        	<td><c:out value="${user.language}" /></td>
        	<td><a href="#deleteUser" data-toggle="modal" data-target="#deleteUserModal"><span class="label label-danger">Delete</span></a></td>
        </tr>
				<div class="modal fade" id="deleteUserModal" tabindex='-1' role="dialog">
		  			<div class="modal-dialog modal-sm">
		  				<c:choose>
	        				<c:when test="${user.type gt 0 }">
				  				<div class="modal-content">
				  					<div class="modal-header modal-header-warning" id="delecteUserModalHead">
				  						<button type="button" class="close" data-dismiss="modal">&times;</button>
				  						<h4 class="modal-title"><span class="glyphicon glyphicon-alert"></span> Delete User <c:out value="${user.user}" /></h4>
				  					</div>
				  					<div class="modal-body" id="deleteUserModalBody">
				  					
				  						<div class="row">
				  							<p style="padding-left: 15px">Are you sure you want delete the following user?</p>
				  						</div>
				  						<div style="padding-left: 20px;">
				  							<p><strong>Username </strong> <c:out value="${user.user}" /></p>
				  							<p><strong>Name </strong> <c:out value="${user.name}" /></p>
				  							<p><strong>Type </strong> <c:out value="${user.type}" /></p>
				  						</div>
				  					</div>
				  					<div class="modal-footer" id="deleteUserModalFooter">
				  						<a href="${pageContext.request.contextPath}/admin/userManagement/remove/${user.user}.html"><button type="button" class="btn btn-danger" data-dismiss="modal" id="deleteActBtnModal">Delete</button></a>
				  						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				  					</div>
				  				</div>
		  					</c:when>
	  						<c:otherwise>
	  							<div class="modal-content">
				  					<div class="modal-header modal-header-warning" id="delecteUserModalHead">
				  						<button type="button" class="close" data-dismiss="modal">&times;</button>
				  						<h4 class="modal-title"><span class="glyphicon glyphicon-alert"></span> Delete User <c:out value="${user.user}" /></h4>
				  					</div>
				  					<div class="modal-body" id="deleteUserModalBody">
				  					
				  						<div class="row">
				  							<h3 style="padding-left: 15px;"> Impossible to remove <strong>admin</strong>.</h3>
				  						</div>
				  					</div>
				  					<div class="modal-footer" id="deleteUserModalFooter">
				  						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				  					</div>
				  				</div>
	  						</c:otherwise>
  						</c:choose>
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
    var $table = $('#userTable');
    $(function () {
    });
</script>