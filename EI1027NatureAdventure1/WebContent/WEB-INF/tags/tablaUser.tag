<%@ tag description="Tabla para mostrar a los usuarios"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



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
        	<td><a href="#deleteUser" data-toggle="modal" data-target="#deleteUserModal${user.user}"><span class="label label-danger">Delete</span></a></td>
        </tr>
				<div class="modal fade" id="deleteUserModal${user.user}" tabindex='-1' role="dialog">
		  			<div class="modal-dialog" <c:if test="${user.type eq 0}">style="width: 700px"</c:if>>
		  				<c:choose>
	        				<c:when test="${user.type gt 0 }">
				  				<div class="modal-content">
				  					<div class="modal-header" id="delecteUserModalHead">
				  						<button type="button" class="close" data-dismiss="modal">&times;</button>
				  						<h4 class="modal-title">Delete User <c:out value="${user.user}" /></h4>
				  					</div>
				  					<div class="modal-body" id="deleteUserModalBody">
				  						<div style="font-size: 14px;">Are you sure, you want remove the user <strong><c:out value="${user.user}"/></strong>?</div>
				  						<div class="modalList">
											<div class="title">User</div>
											<dl>
											  <dt style="padding-left: 30%;">Username</dt><dd style="padding-left: 10%;"><c:out value="${user.user}" /></dd>
								              <dt style="padding-left: 30%;">Name</dt><dd style="padding-left: 10%;"><c:out value="${user.name}" /></dd>
								              <dt style="padding-left: 30%;">Type</dt><dd style="padding-left: 10%;"><c:out value="${user.type}" /></dd>
								            </dl>
							            </div>
							        </div>
				  					<div class="modal-footer" id="deleteUserModalFooter">
										<p class="pull-left" style="font-size: 14px;"><strong class="high">Warning!</strong> <em>This action can not be undone.</em></p>
				  						<div class="pull-right">
				  						<div class="btn-group">
					  						<button type="button" class="btn btn-danger" id="deleteActBtnModal" onclick="location.href='${pageContext.request.contextPath}/admin/userManagement/remove/${user.user}.html'">Delete</button>
					  						<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
				  						</div>
				  						</div>
				  						<div style="clear:both;"></div>
				  					</div>
				  				</div>
		  					</c:when>
	  						<c:otherwise>
	  							<div class="modal-content">
				  					<div class="modal-header" id="delecteUserModalHead">
				  						<button type="button" class="close" data-dismiss="modal">&times;</button>
				  						<h4 class="modal-title">Delete User <c:out value="${user.user}" /></h4>
				  					</div>
				  					<div class="modal-body" id="deleteUserModalBody">
				  							<h3> Impossible to remove an <strong>admin</strong>.</h3>
				  					</div>
				  					<div class="modal-footer" id="deleteUserModalFooter">
				  						<div class="text-right">
				  							<button type="button" class="btn btn-default" data-dismiss="modal">Accept</button>
				  						</div>
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