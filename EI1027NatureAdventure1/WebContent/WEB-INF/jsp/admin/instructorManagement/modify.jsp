<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:body>
		<c:if test="${not empty error and (error eq 2)}">
		<div class="alert alert-warning alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.instructormanage.modify.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.instructormanage.modify.alert.warning.1" /></strong> <fmt:message key="admin.instructormanage.modify.alert.warning.2" /> <strong><c:out value="${instructor.name} ${instructor.lastName}" /></strong>.
			</div>
		</c:if>
		
		<c:if test="${not empty error and (error eq 0)}">
		<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.instructormanage.modify.alert.1" /> <strong><c:out value="${id}" /> <fmt:message key="admin.instructormanage.modify.alert.success.2" /></strong> <fmt:message key="admin.instructormanage.modify.alert.success.3" /> <strong><c:out value="${instructor.name} ${instructor.lastName}" /></strong>.
			</div>
		</c:if>
	
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/instructorManagement/modify/${instructor.ssNumber}.html"><fmt:message key="breadcrumbs.admin.instr.mod" /><em>${instructor.name}</em></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="breadcrumbs.admin.instr" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
	
	<h4><fmt:message key="admin.instructormanage.title" /></h4>
	<h6 class="subtitle"><fmt:message key="admin.instructormanage.modify.title" /> <strong>${instructor.name} ${instructor.lastName}</strong> <small>${instructor.idNumber}</small></h6>
	<div class="col-lg-5">
		<form:form method="post" modelAttribute="instructor" role="form">
			<div class="form-group">
				<form:label path="name" for="nam" class="control-label"><fmt:message key="admin.instructormanage.modify.form.name" /> </form:label>
				<form:input path="name" type="text" class="form-control" id="nam" />
				<form:errors path="name" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="lastName" for="lname" class="control-label"><fmt:message key="admin.instructormanage.modify.form.ln" /> </form:label>
				<form:input path="lastName" type="text" class="form-control" id="lname" />
				<form:errors path="lastName" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="ssNumber" for="ssn" class="control-label"><fmt:message key="admin.instructormanage.modify.form.ssn" /> </form:label>
				<form:hidden path="ssNumber" />
				<div class="form-control" id="ssn"><c:out value="${instructor.ssNumber}" /></div>
				<form:errors path="ssNumber" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="idNumber" for="idn" class="control-label"><fmt:message key="admin.instructormanage.modify.form.idn" /> </form:label>
				<form:hidden path="idNumber" />
				<div class="form-control" id="ssn"><c:out value="${instructor.idNumber}" /></div>
				<form:errors path="idNumber" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="email" for="email" class="control-label">Email: </form:label>
				<form:input path="email" type="email" class="form-control" id="email" />
				<form:errors path="email" class="text-danger" />
			</div>
			<div class="form-group">
				<form:label path="telephone" for="tlf" class="control-label"><fmt:message key="admin.instructormanage.modify.form.tlf" /> </form:label>
				<form:input path="telephone" type="tel" class="form-control" id="tlf" />
				<form:errors path="telephone" class="text-danger" />
			</div>
			<form:hidden path="expireDate" />
			
			<div class="text-center">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary"><fmt:message key="admin.instructormanage.modify.form.btn.submit" /></button>
					<button type="reset" class="btn btn-danger"><fmt:message key="admin.instructormanage.modify.form.btn.cancel" /></button>
					<c:choose>
			        	<c:when test="${instructor.isActive()}">
			        		<a href="${pageContext.request.contextPath}/admin/instructorManagement/disable/${instructor.ssNumber}.html"><button class="btn btn-warning"><fmt:message key="admin.instructormanage.modify.form.btn.inact" /></button></a>	
			        	</c:when>
			        	<c:otherwise>
			        		<a href="${pageContext.request.contextPath}/admin/instructorManagement/enable/${instructor.ssNumber}.html"><button class="btn btn-success"><fmt:message key="admin.instructormanage.modify.form.btn.act" /></button></a>
			        	</c:otherwise>
			        </c:choose>
			    </div>
	        </div>
		</form:form>
	</div>
		
	<div class="col-lg-1"> </div>
	<div class="col-lg-6">
			<h6><fmt:message key="admin.instructormanage.modify.tb.title" /></h6>
			<div class="row">
				<p><fmt:message key="admin.instructormanage.modify.tb.msg" /></p>
		
				<div class="text-center">
					<a href="${pageContext.request.contextPath}/admin/instructorManagement/addActivity/${instructor.ssNumber}.html"><button class="btn btn-primary"><fmt:message key="admin.instructormanage.modify.tb.btn" /></button></a>
				</div>
			</div>
		
		<h6 class="h7"><fmt:message key="admin.instructormanage.modify.tb.subtitle" /></h6>
		<div class="row">
		
			<table class="table table-striped">
		 	<thead>
		 		<tr>
		 			<th class="col-lg-2"><fmt:message key="admin.instructormanage.modify.tb.idact" /></th>
		 			<th class="col-lg-8"><fmt:message key="admin.instructormanage.modify.tb.name" /></th>
		 			<th class="col-lg-2"></th>
		 		</tr>
		 	</thead>
		 	<tbody>
		 		<c:forEach items="${activities}" var="act">
		 		<!-- Aviso MODAL -->
					<div class="modal fade" id="deleteActModal${act.idAct}" tabindex='-1' role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" id="delecteActivityModalHead">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title"><fmt:message key="admin.instructormanage.modify.modal.title" /> <c:out value="${act.name}" /></h4>
								</div>
								<div class="modal-body" id="deleteActivityModalBody">
									<div class="row">
										<p><fmt:message key="admin.instructormanage.modify.modal.msg" /></p>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="modalList">
												<dl>
									              <dt><fmt:message key="admin.instructormanage.modify.tb.name" /></dt><dd><c:out value="${instructor.name}" /> <c:out value="${instructor.lastName}" /></dd>
									              <dt><fmt:message key="admin.instructormanage.modify.modal.snn" /></dt><dd><c:out value="${instructor.ssNumber}" /></dd>
									            </dl>
								            </div>
										</div>
										<div class="col-lg-6" style="border-left:1px solid #ECF0F1;">
											<div class="modalList">
												<dl>
									              <dt><fmt:message key="admin.instructormanage.modify.tb.name" /></dt><dd><c:out value="${act.name}" /></dd>
									              <dt><fmt:message key="admin.instructormanage.modify.tb.idact" /></dt><dd><c:out value="${act.idAct}" /></dd>
									            </dl>
								            </div>

										</div>
									</div>
								</div>	
								<div class="modal-footer" id="deleteActivityModalFooter">
									<div style="font-size: 14px; float:left"><fmt:message key="admin.instructormanage.modify.modal.foot" /></div>
									<div class="pull-right">
										<a href="${pageContext.request.contextPath}/admin/instructorManagement/removeActivity/${instructor.ssNumber}&${act.idAct}.html">
											<button type="button" class="btn btn-danger" id="deleteActBtnModal"><fmt:message key="admin.instructormanage.modify.modal.btn.del" /></button>
										</a>
										<button type="button" class="btn btn-info" data-dismiss="modal"><fmt:message key="admin.instructormanage.modify.form.btn.cancel" /></button>
									</div>
								</div>
							</div>
						</div>
					</div>
		 		<tr>
		 			<td>${act.idAct}</td>
		 			<td>${act.name}</td>
		 			
		 			<td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteActModal${act.idAct}"><fmt:message key="admin.instructormanage.modify.modal.btn.del" /></button></td>
		 		</tr>
		 		</c:forEach>
		 		</tbody>
		 	</table>
	 	</div> <!-- row interno -->
	</div>
	<script type="text/javascript">
	    $('.datepicker').datepicker({
	    	format: 'dd/mm/yyyy',
	    	weekStart: 1,
	    })
	</script>
</jsp:body>
</t:template>