<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:body>
	
	
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/instructorManagement/modify/addActivity/${instructor.ssNumber}.html"><fmt:message key="breadcrumbs.admin.instr.addact" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/instructorManagement/modify/${instructor.ssNumber}.html"><fmt:message key="breadcrumbs.admin.instr.mod" /><em>${instructor.name}</em></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="breadcrumbs.admin.instr" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	
	
	<h4><fmt:message key="admin.instructormanage.title" /></h4>
	<h6 class="subtile"><fmt:message key="admin.instructor.addActivity.title" /> <strong>${instructor.name} ${instructor.lastName}</strong> (<em>${instructor.idNumber}</em>)</h6>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title"><fmt:message key="admin.instructormanage.helptitle" /></h3>
		</div>
		<div class="panel-body">
			<p><fmt:message key="admin.instructor.addActivity.panel.msg.1" /> <strong><c:out value="${instructor.name}" /> 
			<c:out value="${instructor.lastName}" /></strong>.</p>
			<p><fmt:message key="admin.instructor.addActivity.panel.msg.2" /></p>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<h6 class="h7"><fmt:message key="admin.instructor.addActivity.table.1.title" /></h6>
			<table id="instruidasTable"  
  			data-toggle="table" 
     		data-classes="table table-no-bordered"
     		data-query-params="queryParams"
     		data-pagination="true">
				<thead>
        			<tr>
            			<th data-field="idact" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.id" /></th>
			            <th data-field="name" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.name" /></th>
			            <th data-field="level" data-sortable="level"><fmt:message key="admin.instructor.addActivity.table.1.level" /></th>
			            <th data-field="schedule" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.schedule" /></th>
			            <th></th>
	    			</tr>
       			</thead>
        		<tbody>
        			<c:forEach var="act" items="${activitiesInstructor}">
        				<tr>
        					<td><c:out value="${act.idAct}" /></td>
        					<td><c:out value="${act.name}" /></td>
        					<td><c:out value="${act.level}" /></td>
        					<td><c:out value="${act.schedule}" /></td>
        					<td>
        						<a href="#deleteModal" data-toggle="modal" data-target="#deleteActModal${act.idAct}">
        							<span class="label label-danger" style="font-size: 15px;"><fmt:message key="admin.instructor.addActivity.modal.btn.delete" /></span>
        						</a>
        					</td>
        				</tr>
        				
        				<!-- MODAL -->
        				<div class="modal fade" id="deleteActModal${act.idAct}" tabindex='-1' role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" id="delecteActivityModalHead">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title"><fmt:message key="admin.instructor.addActivity.modal.title" /> <c:out value="${act.name}" /></h4>
								</div>
								<div class="modal-body" id="deleteActivityModalBody">
									<div class="row">
										<p><fmt:message key="admin.instructor.addActivity.modal.body" /></p>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="modalList">
												<dl>
									              <dt><fmt:message key="admin.instructor.addActivity.modal.t.1" /></dt><dd><c:out value="${instructor.name}" /> <c:out value="${instructor.lastName}" /></dd>
									              <dt><fmt:message key="admin.instructor.addActivity.modal.t.2" /></dt><dd><c:out value="${instructor.ssNumber}" /></dd>
									            </dl>
								            </div>
										</div>
										<div class="col-lg-6" style="border-left:1px solid #ECF0F1;">
											<div class="modalList">
												<dl>
									              <dt><fmt:message key="admin.instructor.addActivity.modal.t.1" /></dt><dd><c:out value="${act.name}" /></dd>
									              <dt><fmt:message key="admin.instructor.addActivity.modal.t.3" /></dt><dd><c:out value="${act.idAct}" /></dd>
									            </dl>
								            </div>

										</div>
									</div>
								</div>	
								<div class="modal-footer" id="deleteActivityModalFooter">
									<div style="font-size: 14px; float:left"><fmt:message key="admin.instructor.addActivity.modal.footer" /></div>
									<div class="pull-right">
										<a href="${pageContext.request.contextPath}/admin/instructorManagement/removeActivity/${instructor.ssNumber}&${act.idAct}.html">
											<button type="button" class="btn btn-danger" id="deleteActBtnModal"><fmt:message key="admin.instructor.addActivity.modal.btn.delete" /></button>
										</a>
										<button type="button" class="btn btn-info" data-dismiss="modal"><fmt:message key="admin.instructor.addActivity.modal.btn.cancel" /></button>
									</div>
								</div>
							</div>
						</div>
					</div>
        				<!-- /MODAL -->
        			</c:forEach>
        		</tbody>
        	</table>
		</div> <!-- Col -->
		<div class="col-lg-6">
			<h6 class="h7"><fmt:message key="admin.instructor.addActivity.table.2.title" /></h6>
			<table id="possiblesTable"  
  			data-toggle="table" 
     		data-classes="table table-no-bordered"
     		data-query-params="queryParams"
     		data-pagination="true">
				<thead>
        			<tr>
            			<th data-field="idact" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.id" /></th>
			            <th data-field="name" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.name" /></th>
			            <th data-field="level" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.level" /></th>
			            <th data-field="schedule" data-sortable="true"><fmt:message key="admin.instructor.addActivity.table.1.schedule" /></th>
			            <th data-sortable="false"></th>
	    			</tr>
       			</thead>
        		<tbody>
        			<c:forEach var="actPos" items="${possibleActivities}">
        				<tr>
        					<td><c:out value="${actPos.idAct}" /></td>
        					<td><c:out value="${actPos.name}" /></td>
        					<td><c:out value="${actPos.level}" /></td>
        					<td><c:out value="${actPos.schedule}" /></td>
        					<td>
        						<form id="addform${actPos.idAct}" method="post" action="../addActivity/${instructor.ssNumber}.html">
								  <input type="hidden" name="newAct" value="${actPos.idAct}" /> 
								  <a onclick="document.getElementById('addform${actPos.idAct}').submit();">
								  	<span class="label label-success" style="font-size: 15px; cursor: pointer;"><fmt:message key="admin.instructor.addActivity.table.2.add" /></span>
								  	</a>
								</form>
        					</td>
        				</tr>
        			</c:forEach>
        		</tbody>
        	</table>
		</div>
	</div> <!-- ROW -->
	
<!-- Scripts para tabla -->
<script>
	function queryParams() {
	    return {
	        type: 'owner',
	        sort: 'updated',
	        direction: 'desc',
	        per_page: 100,
	        page: 1
	    };
	}
</script>

<script>
    var $table = $('#instruidasTable');
    $(function () {
    });
</script>
<script>
    var $table = $('#possiblesTable');
    $(function () {
    });
</script>
	</jsp:body>
</t:template>	
	