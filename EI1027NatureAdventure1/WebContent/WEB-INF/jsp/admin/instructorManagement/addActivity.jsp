<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	<h2>Instructor management</h2>
	<hr>
	<h3>Add activity to the instructor <strong>${instructor.name} ${instructor.lastName}</strong> (${instructor.idNumber})</h3>
	
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">Help!</h3>
		</div>
		<div class="panel-body">
			<p>This page is to add an activity to the choosen instructor, in this case <strong><c:out value="${instructor.name}" /> 
			<c:out value="${instructor.lastName}" /></strong>.</p>
			<p>The left column shows with activities that the instructor can teach now and the right column show with activities that you can add it to the instructor.</p>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<h4>Activities that actually teach</h4>
			<table id="instruidasTable"  
  			data-toggle="table" 
     		data-classes="table table-no-bordered"
     		data-query-params="queryParams"
     		data-pagination="true">
				<thead>
        			<tr>
            			<th data-field="idact" data-sortable="true">ID activity</th>
			            <th data-field="name" data-sortable="true">Name</th>
			            <th data-field="level" data-sortable="level">level</th>
			            <th data-field="schedule" data-sortable="true">Schedule</th>
	    			</tr>
       			</thead>
        		<tbody>
        			<c:forEach var="act" items="${activitiesInstructor}">
        				<tr>
        					<td><c:out value="${act.idAct}" /></td>
        					<td><c:out value="${act.name}" /></td>
        					<td><c:out value="${act.level}" /></td>
        					<td><c:out value="${act.schedule}" /></td>
        				</tr>
        			</c:forEach>
        		</tbody>
        	</table>
		</div> <!-- Col -->
		<div class="col-lg-6">
			<h4>Possible activities</h4>
			<table id="possiblesTable"  
  			data-toggle="table" 
     		data-classes="table table-no-bordered"
     		data-query-params="queryParams"
     		data-pagination="true">
				<thead>
        			<tr>
            			<th data-field="idact" data-sortable="true">ID activity</th>
			            <th data-field="name" data-sortable="true">Name</th>
			            <th data-field="level" data-sortable="true">level</th>
			            <th data-field="schedule" data-sortable="true">Schedule</th>
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
        						<form:form method="post" modelAttribute="msg" role="form">
        							<input type="hidden" name="inte" value="${actPos.idAct}" />
        							<button type="submit" class="btn btn-success">Add</button>
        						</form:form>
        						
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
	