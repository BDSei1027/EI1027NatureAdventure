<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>

	<c:if test="${not empty error and (error eq 2)}">
		<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  User <strong><c:out value="${id}" /> removed</strong>.
			</div>
	</c:if>
	<h4>User management</h4>
	<div id="help" class="row">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Help!</h3>
			</div>
			<div class="panel-body">
				<p>The following table lists all the user registrated, including admin and monitos.</p>
				<p>You only can delete the accounts and all their information is in the below table.</p>
			</div>
		</div>
	</div>
	<h6 class="subtitle">List of the users</h6>
	<t:tablaUser />
</jsp:body>
</t:template>