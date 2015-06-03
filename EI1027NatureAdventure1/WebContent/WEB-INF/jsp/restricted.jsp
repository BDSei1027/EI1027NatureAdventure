<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
<jsp:body>
	<h4>WARNING: Restricted area!</h4>
	<div style="padding: 20px;">
		<p>Sorry boy, you are trying to enter an area you have not permission</p>
		
		<p>Go back to the main page, <a href="${pageContext.request.contextPath}/index.jsp">click here</a>.</p>
		<div class="hidden">
			<p class="text-danger"> If you are trying to access an restricted area and you will enter, please
			contact with us, with the following anonymous form, for solve those possible security problems. </p>
			<div style="padding-left: 40px; margin-bottom: 15px;"><button id="openModal" data-toggle="modal" data-target="#contactModal" type="button" class="btn btn-danger">Open contact</button></div>
			<p class="text-danger">Thank you <strong>little bastard!</strong></p>
		</div>
	</div>
	<!-- MODAL -->
	<div class="modal fade" id="contactModal" tabindex='-1' role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header modal-header-info" id="contactModalHead">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="fui-chat"></span> Contact</h4>
				</div>
				<div class="modal-body" id="ContactModalBody">
					<form:form role="form" action="${pageContext.request.contextPath}/contact.html" method="post" modelAttribute="email">
						<div class="form-group">
							<div class="input-group">
								<form:input path="to" type="text" class="form-control" placeholder="Enter the destination" />
								<div class="input-group-addon"><span class="fui-mail"></span></div>
							</div>
							<form:errors path="to" class="text-danger" />
						</div>
						<div class="form-group">
							<form:label path="message" for="message" class="control-label">Message</form:label>
							<form:textarea path="message" id="message" class="form-control" />
						</div>
						<button type="submit" class="btn btn-success" id="LoginButton"><span class="fui-mail"></span> Send</button>
					</form:form>
				</div>	
			</div>
		</div>
	</div>
</jsp:body>
</t:template>