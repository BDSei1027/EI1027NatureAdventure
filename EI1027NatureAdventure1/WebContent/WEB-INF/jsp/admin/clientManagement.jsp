<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>
	<c:if test="${not empty error}">
		<c:choose>
			<c:when test="${error eq 0}">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.clientmanage.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.clientmanage.alert.mod" /></strong> <fmt:message key="admin.clientmanage.alert.success" />.
			</div>
			</c:when>
			<c:when test="${error eq 2}">
			<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.clientmanage.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.clientmanage.alert.del" /></strong>.
			</div>
			</c:when>
		</c:choose>
	</c:if>
	<h4><fmt:message key="admin.clientmanage.title" /></h4>
	<div id="action&help" class="row">

			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="admin.clientmanage.helptitle" /></h3>
				</div>
				<div class="panel-body">
					<p><fmt:message key="admin.clientmanage.helpcontent1" /></p>
					<p><fmt:message key="admin.clientmanage.helpcontent2" /> <span class="label label-info"><fmt:message key="admin.clientmanage.helpcontent.details" /></span>.</p>
					<p><fmt:message key="admin.clientmanage.helpcontent3" /> <span class="label label-info"><fmt:message key="admin.clientmanage.helpcontent.details" /></span> <fmt:message key="admin.clientmanage.helpcontent4" /> <strong class="high"><fmt:message key="admin.clientmanage.helpcontent5" /></strong>.</p>
					<p><fmt:message key="admin.clientmanage.helpcontent6" /> <span class="label label-danger"><fmt:message key="admin.clientmanage.helpcontent.delete" /></span>. <strong class="high"> <fmt:message key="admin.clientmanage.helpcontent7" /></strong></p>
				</div>
			</div>
		<!-- </div> -->
	</div>
	<h6 class="subtitle"><fmt:message key="admin.clientmanage.list.title" /></h6>
	<t:tablaClients />
</jsp:body>
</t:template>