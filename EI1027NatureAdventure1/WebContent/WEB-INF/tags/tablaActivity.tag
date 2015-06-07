<%@ tag description="Tabla para mostrar instructores"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message key="" />

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
		            <th data-field="id" data-sortable="true"><fmt:message key="admin.activitymanage.list.id" /></th>
		            <th data-field="name" data-sortable="true"><fmt:message key="admin.activitymanage.list.name" /></th>
		            <th data-field="level" data-sortable="true"><fmt:message key="admin.activitymanage.list.level" /></th>
		            <th data-field="schedule" data-sortable="true"><fmt:message key="admin.activitymanage.list.schedule" /></th>
		            <th data-field="price" data-sortable="true"><fmt:message key="admin.activitymanage.list.price" /></th>
		            <th data-field="min" data-sortable="true"><fmt:message key="admin.activitymanage.list.ming" /></th>
		            <th data-field="max" data-sortable="true"><fmt:message key="admin.activitymanage.list.maxg" /></th>
		            <th data-field="active" data-sortable="true"><fmt:message key="admin.activitymanage.list.isactive" /></th>
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
		        				<fmt:message key="admin.activitymanage.list.yes" />	
		        			</c:when>
		        			<c:otherwise>
		        				<fmt:message key="admin.activitymanage.list.no" />
		        			</c:otherwise>
		        		</c:choose></td>
		        	<td><a href="${pageContext.request.contextPath}/admin/activityManagement/modify/${activity.idAct}.html"><span class="label label-info"><fmt:message key="admin.activitymanage.list.details" /></span></a></td>
		        	<td>
		        		<c:choose>
		        			<c:when test="${activity.isActive()}">
		        				<a href="${pageContext.request.contextPath}/admin/activityManagement/disable/${activity.idAct}.html"><span class="label label-warning"><fmt:message key="admin.activitymanage.list.activate" /></span></a>	
		        			</c:when>
		        			<c:otherwise>
		        				<a href="${pageContext.request.contextPath}/admin/activityManagement/enable/${activity.idAct}.html"><span class="label label-success"><fmt:message key="admin.activitymanage.list.inactivate" /></span></a>
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