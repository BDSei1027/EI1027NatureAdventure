<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:template>
<jsp:body>
	<div class="container" id="login">
		<div class="login">
			<h3>Sign in</h3>
			<form:form role="form" method="post" modelAttribute="user">
				<div class="form-group">
					<form:label path="user" for="user" class="control-label">Identificator: </form:label>
					<form:input path="user" type="text" class="form-control" id="user" placeholder="Your ID" />
				</div>
				<div class="form-group">
					<form:label path="password" for="pass" class="control-label">Password: </form:label>
					<form:input path="password" type="password" class="form-control" id="pass" placeholder="Password" />
				</div>
				<button type="submit" class="btn btn-default"> Submit</button>
			</form:form>
		</div>
	</div>
</jsp:body>
</t:template>