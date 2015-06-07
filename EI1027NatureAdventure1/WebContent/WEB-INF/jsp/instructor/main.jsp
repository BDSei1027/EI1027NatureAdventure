<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>



<t:template>
<jsp:body>
		<h4><fmt:message key="instructor.assignedbookings.welcome1" /> <strong><c:out value="${name}" /></strong></h4>
		<p><fmt:message key="instructor.assignedbookings.welcome2" /></p>
		
		<div class="panel panel-info">
			<div class="panel-heading">
				<div class="panel-title"><fmt:message key="instructor.assignedbookings.helptitle" /></div>
			</div>
			<div class="panel-body">
				<p><fmt:message key="instructor.assignedbookings.helpcontent1" /></p>
				<p>  <span class="label label-info"><fmt:message key="instructor.assignedbookings.details" /></span> <fmt:message key="instructor.assignedbookings.helpcontent2" /></p>
			</div>
		</div>
		<p class="lead text-center"><fmt:message key="instructor.assignedbookings.counter1" /> <strong><c:out value="${numbookings}" /></strong> <fmt:message key="instructor.assignedbookings.counter2" /> </p>
		<h6 class="subtitle"><fmt:message key="instructor.assignedbookings.list.title" /></h6>
		<t:tablaBookingsInstructor />
</jsp:body>
</t:template>
