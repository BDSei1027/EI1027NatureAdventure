<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<t:template>
<jsp:body>


<div class="loginCustom">
		<form:form method="post" role="form" modelAttribute="user" class="login-form">
		<h4>Sign in!</h4>
			<div class="form-group">
    			<form:input path="user" id="login-name" class="form-control login-field" type="text" placeholder="Enter your name" />
    			<form:label path="user" class="login-field-icon fui-user" for="login-name"></form:label>
    			<form:errors path="user" class="text-danger" />
    		</div>
    		<div class="form-group">
		    	<form:input path="password" id="login-pass" class="form-control login-field" type="password" placeholder="Password" />
		    	<form:label path="password" class="login-field-icon fui-lock" for="login-pass"></form:label>
		    	<form:errors path="password" class="text-danger" />
			</div>
			<button type="submit" class="btn btn-primary btn-lg btn-block">Sign in!</button>
            <a class="login-link" href="#">Lost your password?</a>
            <div class="logoLogin">NatureAdventure</div>
        </form:form>
</div>	
</jsp:body>
</t:template>