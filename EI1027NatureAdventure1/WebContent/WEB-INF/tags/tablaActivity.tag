<%@ tag description="Tabla para mostrar instructores"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty error and (error eq 0)}"><c:set scope="page" var="type" value="success" /></c:if>
<c:if test="${not empty error and (error eq 1)}"><c:set scope="page" var="type" value="info" /></c:if>
	<!-- TABLA -->
		<table id="activitiesTable"  
		  data-toggle="table" 
	      data-classes="table table-no-bordered"
	      data-query-params="queryParams"
	      data-pagination="true"
	      data-search="true">
			<thead>
		        <tr>
		            <th data-field="id" data-sortable="true">ID</th>
		            <th data-field="name" data-sortable="true">Name</th>
		            <th data-field="level" data-sortable="true">Level</th>
		            <th data-field="schedule" data-sortable="true">Schedule</th>
		            <th data-field="price" data-sortable="true">Price</th>
		            <th data-field="min" data-sortable="true">Min G</th>
		            <th data-field="max" data-sortable="true">Max G</th>
		            <th data-field="active" data-sortable="true">Is active</th>
		            <th></th>
		            <th></th>
		        </tr>
	        </thead>
	        <tbody>
		        <c:forEach var="activity" items="${activityList}" >
		        <tr <c:if test="${not empty id and (id eq activity.idAct)}">class="${type}"</c:if>> 
		        	<td><c:out value="${activity.idAct}" /></td>
		        	<td><c:out value="${activity.name}" /></td>
		        	<td><c:out value="${activity.level}" /></td>
		        	<td style="text-transform: capitalize;"><c:out value="${activity.schedule}" /></td>
		        	<td><c:out value="${activity.price}" /></td>
		        	<td><c:out value="${activity.minimumGroup}" /></td>
		        	<td><c:out value="${activity.maximumGroup}" /></td>
		        	<td><c:choose>
		        			<c:when test="${activity.isActive()}">
		        				Yes	
		        			</c:when>
		        			<c:otherwise>
		        				No
		        			</c:otherwise>
		        		</c:choose></td>
		        	<td><a href="${pageContext.request.contextPath}/admin/activityManagement/modify/${activity.idAct}.html"><span class="label label-info">Details</span></a></td>
		        	<td>
		        		<c:choose>
		        			<c:when test="${activity.isActive()}">
		        				<a href="${pageContext.request.contextPath}/admin/activityManagement/disable/${activity.idAct}.html"><span class="label label-warning">Inactivate</span></a>	
		        			</c:when>
		        			<c:otherwise>
		        				<a href="${pageContext.request.contextPath}/admin/activityManagement/enable/${activity.idAct}.html"><span class="label label-success">Activate</span></a>
		        			</c:otherwise>
		        		</c:choose>
		        	</td>
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
    var $table = $('#activitiesTable');
    $(function () {
    });
</script>