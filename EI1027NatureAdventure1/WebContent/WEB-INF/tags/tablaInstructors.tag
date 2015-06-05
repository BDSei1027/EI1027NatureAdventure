<%@ tag description="Tabla para mostrar instructores"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<!-- TABLA -->
<table id="instructorsTable"  
  data-toggle="table" 
     data-classes="table table-no-bordered"
     data-query-params="queryParams"
     data-pagination="true"
     data-search="true">
	<thead>
        <tr>
            <th data-field="name" data-sortable="true">Name</th>
            <th data-field="lname" data-sortable="true">Last name</th>
            <th data-field="ssn" data-sortable="true">ssNumber</th>
            <th data-field="idn" data-sortable="true">idNumber</th>
            <th data-field="email" data-sortable="true">Email</th>
            <!-- <th data-field="tlf" data-sortable="true">Telephone</th> -->
            <th data-field="expire" data-sortable="true">Expire date</th>
            <th data-field="activities" data-sortable="true">Activities</th>
            <th data-field="active" data-sortable="true">is Active?</th>
            <th></th>
            <th></th>
        </tr>
       </thead>
       <tbody>
        <c:forEach var="instructor" items="${instructorList}" >
        <tr <c:if test="${not empty alert and (alert eq instructor.ssNumber)}">class="success"</c:if>> 
        	<td><c:out value="${instructor.name}" /></td>
        	<td><c:out value="${instructor.lastName}" /></td>
        	<td><c:out value="${instructor.ssNumber}" /></td>
        	<td><c:out value="${instructor.idNumber}" /></td>
        	<td><c:out value="${instructor.email}" /></td>
        	<!-- <td><c:out value="${instructor.telephone}" /></td> -->
        	<td><c:out value="${instructor.expireDate}" /></td>
        	<td><c:out value="${instructor.getNActivities()}" /></td>
        	<td><c:choose>
        			<c:when test="${instructor.isActive()}">
        				Yes	
        			</c:when>
        			<c:otherwise>
        				No
        			</c:otherwise>
        		</c:choose></td> 
        	<td><a href="${pageContext.request.contextPath}/admin/instructorManagement/modify/${instructor.ssNumber}.html"><span class="label label-info">Edit</span></a></td>
        	<td>
        		<c:choose>
        			<c:when test="${instructor.isActive()}">
        				<a href="${pageContext.request.contextPath}/admin/instructorManagement/disable/${instructor.ssNumber}.html"><span class="label label-warning">Inactivate</span></a>	
        			</c:when>
        			<c:otherwise>
        				<a href="${pageContext.request.contextPath}/admin/instructorManagement/enable/${instructor.ssNumber}.html"><span class="label label-success">Activate</span></a>
        			</c:otherwise>
        		</c:choose>
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
    var $table = $('#instructorsTable');
    $(function () {
    });
</script>