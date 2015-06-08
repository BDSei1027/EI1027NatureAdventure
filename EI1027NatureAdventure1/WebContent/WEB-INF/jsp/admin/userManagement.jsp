<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>

	<c:if test="${not empty error and (error eq 2)}">
		<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.usermanage.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.usermanage.alert.2" /></strong>.
			</div>
	</c:if>
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/userManagement.html"><fmt:message key="breadcrumbs.admin.usermanage" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	
	<h4><fmt:message key="admin.usermanage.title" /></h4>
	<div id="help" class="row">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title"><fmt:message key="admin.usermanage.helptitle" /></h3>
			</div>
			<div class="panel-body">
				<p><fmt:message key="admin.usermanage.helpcontent1" /></p>
				<p><fmt:message key="admin.usermanage.helpcontent2" /></p>
			</div>
		</div>
	</div>
	<h6 class="subtitle"><fmt:message key="admin.usermanage.list.title" /></h6>
	<t:tablaUser />
</jsp:body>
</t:template>