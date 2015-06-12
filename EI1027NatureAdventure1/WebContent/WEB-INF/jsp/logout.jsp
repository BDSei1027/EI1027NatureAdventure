<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
<c:set var="lang" value="${pageContext.response.locale}" scope="page"/>
	<h4><fmt:message key="logout.thank0" /></h4>
	
	<p><fmt:message key="logout.thank1" /></p>
	<p><fmt:message key="logout.thank2" /></p>
	<br>
	<p><fmt:message key="logout.thank3" /> <a href="#"><fmt:message key="logout.thank4" /></a>
	<fmt:message key="logout.thank5" /> <a href="#"><fmt:message key="logout.thank6" /></a>.</p>
	
	<h5 class="text-right">NatureAdventure</h5>
</jsp:body>
</t:template>