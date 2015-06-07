<%@ tag description="Tabla para mostrar a los usuarios"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<!-- TABLA -->
<table id="userTable"  
  data-toggle="table" 
     data-classes="table table-no-bordered"
     data-query-params="queryParams"
     data-pagination="true"
     data-search="true">
	<thead>
        <tr>
            <th data-field="user" data-sortable="true"><fmt:message key="admin.usermanage.list.username" /></th>
            <th data-field="name" data-sortable="true"><fmt:message key="admin.usermanage.list.name" /></th>
            <th data-field="type" data-sortable="true"><fmt:message key="admin.usermanage.list.type" /></th>
            <th data-field="lang" data-sortable="true"><fmt:message key="admin.usermanage.list.language" /></th>
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
        	<td><a href="#deleteUser" data-toggle="modal" data-target="#deleteUserModal${user.user}"><span class="label label-danger"><fmt:message key="admin.usermanage.list.delete" /></span></a></td>
        </tr>
				<div class="modal fade" id="deleteUserModal${user.user}" tabindex='-1' role="dialog">
		  			<div class="modal-dialog" <c:if test="${user.type eq 0}">style="width: 700px"</c:if>>
		  				<c:choose>
	        				<c:when test="${user.type gt 0 }">
				  				<div class="modal-content">
				  					<div class="modal-header" id="delecteUserModalHead">
				  						<button type="button" class="close" data-dismiss="modal">&times;</button>
				  						<h4 class="modal-title"><fmt:message key="admin.usermanage.modal.title" /> <c:out value="${user.user}" /></h4>
				  					</div>
				  					<div class="modal-body" id="deleteUserModalBody">
				  						<div style="font-size: 14px;"><fmt:message key="admin.usermanage.modal.msg.1" /> <strong><c:out value="${user.user}"/></strong>?</div>
				  						<div class="modalList">
											<div class="title"><fmt:message key="admin.usermanage.modal.dl.title" /></div>
											<dl>
											  <dt style="padding-left: 30%;"><fmt:message key="admin.usermanage.modal.dl.user" /></dt><dd style="padding-left: 10%;"><c:out value="${user.user}" /></dd>
								              <dt style="padding-left: 30%;"><fmt:message key="admin.usermanage.modal.dl.name" /></dt><dd style="padding-left: 10%;"><c:out value="${user.name}" /></dd>
								              <dt style="padding-left: 30%;"><fmt:message key="admin.usermanage.modal.dl.type" /></dt><dd style="padding-left: 10%;"><c:out value="${user.type}" /></dd>
								            </dl>
							            </div>
							        </div>
				  					<div class="modal-footer" id="deleteUserModalFooter">
										<p class="pull-left" style="font-size: 14px;"><fmt:message key="admin.usermanage.modal.foot" /></p>
				  						<div class="pull-right">
				  						<div class="btn-group">
					  						<button type="button" class="btn btn-danger" id="deleteActBtnModal" onclick="location.href='${pageContext.request.contextPath}/admin/userManagement/remove/${user.user}.html'"><fmt:message key="admin.usermanage.modal.btn.dlt" /></button>
					  						<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="admin.usermanage.modal.btn.cancel" /></button>
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
				  						<h4 class="modal-title"><fmt:message key="admin.usermanage.modal.title" /> <c:out value="${user.user}" /></h4>
				  					</div>
				  					<div class="modal-body" id="deleteUserModalBody">
				  							<h3> <fmt:message key="admin.usermanage.modal.msg.2" /></h3>
				  					</div>
				  					<div class="modal-footer" id="deleteUserModalFooter">
				  						<div class="text-right">
				  							<button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="admin.usermanage.modal.btn.accept" /></button>
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