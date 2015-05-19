<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
	<div class="container" id="restricted-area">
	<h3>WARNING: Restricted area!</h3>
	
	<p>Sorry boy, you are trying to enter an area you have not permission</p>
	
	<p>Go back to the main page, <a href="${pageContext.request.contextPath}/index.jsp">click here</a>.</p>
	
	<p class="hidden text-danger"> If you are trying to access an restricted area and you will enter, please
	contact with us, with the following anonymous form <a href="#"><span class="label label-danger">here</span></a>, for solve those possible security problems.
	<br /> Thank you <strong>little bastard!</strong></p>
	</div>
</jsp:body>
</t:template>