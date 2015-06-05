<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	<h4>Welcome <c:out value="${user.name}" /></h4>
	<div class="row">
		<div class="col-lg-6">
			<h6 class="subtitle">This is your main page.</h6>
		</div>
		<div class="col-lg-6">
			<h6 class="text-right">Today is <strong><c:out value="${date}" /></strong></h6>
		</div>
	</div>
	
	</jsp:body>
</t:template>