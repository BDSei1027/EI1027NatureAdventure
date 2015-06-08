<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:template>
<jsp:body>

	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/instructor/history.html"><fmt:message key="breadcrumbs.instr.history" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/instructor.html"><fmt:message key="breadcrumbs.instr.main" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a> 
	</div>
	
<!-- Quizas me excedi con la funcionalidad lo podemos hacer mas basico al principio -->
	<div class="intructorList">
		<h4><fmt:message key="instructor.bookhistory.title" /></h4>
		<p><fmt:message key="instructor.bookhistory.subtitle" /> <strong><c:out value="${name}" /></strong></p>
		<div id="help&action" class="row">
			<div id="menu" class="col-lg-2">
				<ul class="nav nav-pills nav-stacked">
					<li><a href="${pageContext.request.contextPath}/instructor/main.html"><fmt:message key="instructor.bookhistory.menu.all" /></a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/instructor/history.html"><fmt:message key="instructor.bookhistory.menu.history" /></a>
					<li><a href="mailto:admin@natureadventure.uji.es"><fmt:message key="instructor.bookhistory.menu.contactboss" /></a></li>
				</ul>
			</div>
			<div id="help" class="col-lg-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title"><fmt:message key="instructor.bookhistory.helptitle" /></div>
					</div>
					<div class="panel-body">
						<p><fmt:message key="instructor.bookhistory.helpcontent1" /></p>
						<p><fmt:message key="instructor.bookhistory.helpcontent2" /> <span class="label label-info"><fmt:message key="instructor.bookhistory.helpcontent.details" /></span><fmt:message key="instructor.bookhistory.helpcontent3" /></p>
					</div>
				</div>
			</div>
		</div>
		<h6 class="subtitle"><fmt:message key="instructor.bookhistory.list.title" /></h6>
		<t:tablaBookingsInstructor />
	</div>
</jsp:body>
</t:template>