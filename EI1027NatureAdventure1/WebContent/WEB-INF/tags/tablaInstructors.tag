<%@ tag description="Tabla para mostrar instructores"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:if test="${not empty error and (error eq 0)}"><c:set scope="page" var="type" value="success" /></c:if>
<c:if test="${not empty error and (error eq 1)}"><c:set scope="page" var="type" value="info" /></c:if>
	<!-- TABLA -->
<table id="instructorsTable"  
  data-toggle="table" 
     data-classes="table table-no-bordered"
     data-query-params="queryParams"
     data-pagination="true"
     data-search="true">
	<thead>
        <tr>
            <th data-field="name" data-sortable="true"><fmt:message key="admin.instructormanage.list.name" /></th>
            <th data-field="lname" data-sortable="true"><fmt:message key="admin.instructormanage.list.lastname" /></th>
            <th data-field="ssn" data-sortable="true"><fmt:message key="admin.instructormanage.list.ssnumber" /></th>
            <th data-field="idn" data-sortable="true"><fmt:message key="admin.instructormanage.list.idnumber" /></th>
            <th data-field="email" data-sortable="true"><fmt:message key="admin.instructormanage.list.email" /></th>
            <!-- <th data-field="tlf" data-sortable="true">Telephone</th> -->
            <th data-field="expire" data-sortable="true">E<fmt:message key="admin.instructormanage.list.expire" /></th>
            <th data-field="activities" data-sortable="true"><fmt:message key="admin.instructormanage.list.activities" /></th>
            <th data-field="active" data-sortable="true"><fmt:message key="admin.instructormanage.list.isactive" /></th>
            <th></th>
            <th></th>
        </tr>
       </thead>
       <tbody>
        <c:forEach var="instructor" items="${instructorList}" >
        <tr <c:if test="${not empty id and (id eq instructor.ssNumber)}">class="${type}"</c:if>> 
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
        	<td><a href="${pageContext.request.contextPath}/admin/instructorManagement/modify/${instructor.ssNumber}.html"><span class="label label-info"><fmt:message key="admin.instructormanage.list.details" /></span></a></td>
        	<td>
        		<c:choose>
        			<c:when test="${instructor.isActive()}">
        				<a href="${pageContext.request.contextPath}/admin/instructorManagement/disable/${instructor.ssNumber}.html"><span class="label label-warning"><fmt:message key="admin.instructormanage.list.activate" /></span></a>	
        			</c:when>
        			<c:otherwise>
        				<a href="${pageContext.request.contextPath}/admin/instructorManagement/enable/${instructor.ssNumber}.html"><span class="label label-success"><fmt:message key="admin.instructormanage.list.inactive" /></span></a>
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