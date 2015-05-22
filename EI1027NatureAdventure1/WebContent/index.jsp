<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:template>
<jsp:body>
<!--  Breadcrumbs y logeado -->

	<div class="row">
		<!-- breadcrumb -->
		<div class="col-lg-6">
			<ol class="breadcrumbs">
				<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
			</ol>
		</div>
		
	</div>

	<div class="row">
	<div class="col-lg-6">
	<!-- BreadCrumbs -->
	<ol class="breadcrumb">
		<li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
		<li>Hola</li>
	</ol>
	</div>
	<div class="col-lg-6 text-right">
	<p> Logeado como X </p>
	</div>
	</div>
	
	<h2>Example</h2>
	<p>This is the example page to go to the links</p>
	
	<u>Interest Links:</u>
	<ul>
		<li><a href="test.html">LINK</a></li>
	</ul>
</jsp:body>
</t:template>