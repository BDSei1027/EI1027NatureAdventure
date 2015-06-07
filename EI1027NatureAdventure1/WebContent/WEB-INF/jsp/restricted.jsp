<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
	<h4><fmt:message key="restricted.title" /></h4>
	<div style="padding: 20px;">
		<p><fmt:message key="restricted.msg1" /></p>
		
		<p><fmt:message key="restricted.back" /> <a href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="restricted.back.link" /></a>.</p>
		<div class="hidden">
			<p class="text-danger"><fmt:message key="restricted.msg2" /> <fmt:message key="restricted.msg3" /> </p>
			<div style="padding-left: 40px; margin-bottom: 15px;"><button id="openModal" data-toggle="modal" data-target="#contactModal" type="button" class="btn btn-danger">Open contact</button></div>
			<p class="text-danger"><fmt:message key="restricted.thank.1" /> <strong><fmt:message key="restricted.thank.2" /></strong></p>
		</div>
	</div>
	<!-- MODAL -->
	<div class="modal fade" id="contactModal" tabindex='-1' role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="contactModalHead">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4>Contact</h4>
				</div>
				<form:form role="form" action="${pageContext.request.contextPath}/contact.html" method="post" modelAttribute="email">
					<div class="modal-body" id="ContactModalBody">
							<div class="form-group">
								<div class="input-group">
									<form:input path="to" type="text" class="form-control" placeholder="Enter the destination" />
									<div class="input-group-addon"><span class="fui-mail"></span></div>
								</div>
								<form:errors path="to" class="text-danger" />
							</div>
							<div class="form-group">
								<form:label path="message" for="message" class="control-label"><fmt:message key="restricted.message" /></form:label>
								<form:textarea path="message" id="message" class="form-control" />
							</div>
					</div>
					<div class="modal-footer">
						<div class="text-right">
							<button type="submit" class="btn btn-primary" id="LoginButton"><fmt:message key="restricted.send" /></button>
						</div>
					</div>
				</form:form>	
			</div>
		</div>
	</div>
</jsp:body>
</t:template>