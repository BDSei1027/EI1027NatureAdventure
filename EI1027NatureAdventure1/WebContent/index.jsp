<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:template>
<jsp:body>

	<h4>Usuarios y contrasenyas</h4>
	<ul>
		<li><strong>admin</strong> 123456</li>
		<li><STRONG>73401117</STRONG> 666666666 <em>Instructor</em></li>
		<li><strong>33366633A</strong> 12345678 <em>Cliente</em></li>
	</ul>
	<h3>Example</h3>
	<p><c:out value="${pageContext.request.requestURI}" /></p>
	<p>This is the example page to go to the links</p>
	
	<u>Interest Links:</u>
	<ul>
		<li><a href="login.html">LINK</a></li>
	</ul>
</jsp:body>
</t:template>