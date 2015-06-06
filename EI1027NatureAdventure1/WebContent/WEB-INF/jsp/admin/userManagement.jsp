<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
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