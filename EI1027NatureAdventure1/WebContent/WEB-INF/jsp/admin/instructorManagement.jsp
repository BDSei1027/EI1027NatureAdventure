<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>
	<h2>Instructor management</h2>
	<p>The following table lists all the instructor, which are in the data base.
	<br>
	To see the activities of the instructor, click on <span class="label label-info">Edit</span></p>
	<div class="text-center"><a href="instructorManagement/add.html"><button class="btn btn-primary">Add instructor</button></a></div>
	<h3>List of instructors</h3>
	<table class="table table-striped">
		<thead>
	        <tr>
	            <th>Name</th>
	            <th>Last name</th>
	            <th>ssNumber</th>
	            <th>idNumber</th>
	            <th>Email</th>
	            <th>Telephone</th>
	            <th>Expire date</th>
	            <th>is Active?</th>
	            <th></th>
	            <th></th>
	            <th></th>
	        </tr>
        </thead>
        <tbody>
	        <c:forEach var="instructor" items="${instructorList}" >
	        <tr> 
	        	<td><c:out value="${instructor.name}" /></td>
	        	<td><c:out value="${instructor.lastName}" /></td>
	        	<td><c:out value="${instructor.ssNumber}" /></td>
	        	<td><c:out value="${instructor.idNumber}" /></td>
	        	<td><c:out value="${instructor.email}" /></td>
	        	<td><c:out value="${instructor.telephone}" /></td>
	        	<td><c:out value="${instructor.expireDate}" /></td>
	        	<td><c:choose>
	        			<c:when test="${instructor.isActive()}">
	        				Yes	
	        			</c:when>
	        			<c:otherwise>
	        				No
	        			</c:otherwise>
	        		</c:choose></td>
	        	<td><a href="instructorManagement/modify/${instructor.ssNumber}.html"><span class="label label-info">Edit</span></a></td>
	        	<td>
	        		<c:choose>
	        			<c:when test="${instructor.isActive()}">
	        				<a href="instructorManagement/disable/${instructor.ssNumber}.html"><span class="label label-warning">Inactivate</span></a>	
	        			</c:when>
	        			<c:otherwise>
	        				<a href="instructorManagement/enable/${instructor.ssNumber}.html"><span class="label label-success">Activate</span></a>
	        			</c:otherwise>
	        		</c:choose>
	        	<td><a href="instructorManagement/delete/${instructor.ssNumber}.html"><span class="label label-danger">Delete</span></a></td>
	        </tr>
	        </c:forEach>
		</tbody>
 	</table>
</jsp:body>
</t:template>