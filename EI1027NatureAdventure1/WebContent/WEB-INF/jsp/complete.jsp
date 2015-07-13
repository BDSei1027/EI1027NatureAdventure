<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 


<t:template>
	<jsp:body>
		<h4><fmt:message key="complete.title" /></h4>
		<h6 class="subtitle"><fmt:message key="complete.subtitle" /></h6>
	
		<div style="padding-left: 3%;">
			<p><fmt:message key="complete.msg.1" /></p>
			<p><fmt:message key="complete.msg.2" />, <a href="${pageContext.request.contextPath}/customer.html"><fmt:message key="complete.a.cli" /></a>.</p>
			<p style="font-size: 15px;"><fmt:message key="complete.msg.3" /></p>
			<c:if test="${not empty anonymous}">
				<p><fmt:message key="complete.msg.register"/> <a href="${pageContext.request.contextPath}/register.html"><strong class="high"><fmt:message key="complete.register" /></strong></a></p>
			</c:if>
			<a href="${pageContext.request.contextPath}/"><fmt:message key="complete.a.back" /></a>
		</div>	
	</jsp:body>
</t:template>