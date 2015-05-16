<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:base>
<jsp:body>
	<h2>Instructor management</h2>
	<pre>The following table lists all the instructor, which are in the data base.
	<br>
	To see the activities of the instructor, click on <span class="label label-info">Edit</span></pre>
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
	        </tr>
        </thead>
        <tbody>
	        <c:forEach items="${instructors}" var="instructor">
	        <tr> 
	        	<td>${instructor.name}</td>
	        	<td>${instructor.lastname}</td>
	        	<td>${instructor.ssnumber}</td>
	        	<td>${instructor.idnumber}</td>
	        	<td>${instructor.email}</td>
	        	<td>${instructor.telephone}</td>
	        	<td>${instructor.expiredate}</td>
	        	<td>${instructor.isactive}</td>
	        	<td><a href="modify/${instructor.ssnumber}.html"><span class="label label-info">Edit</span></a></td>
	        	<td><a href="delete/${instructor.ssnumber}.html"><span class="label label-danger">Delete</span></a></td>
	        </tr>
	        </c:forEach>
		</tbody>
 	</table>
</jsp:body>
</t:base>