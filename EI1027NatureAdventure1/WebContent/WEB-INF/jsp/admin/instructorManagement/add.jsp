<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>


	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/instructorManagement/add.html"><fmt:message key="breadcrumbs.admin.instr.add" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="breadcrumbs.admin.instr" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>

<h4><fmt:message key="admin.instructormanage.title" /></h4>
<h6 class="subtitle"><fmt:message key="admin.addinstructor.title" /></h6>
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title"><fmt:message key="admin.addinstructor.form.title" /></div>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
		<form:form method="post" modelAttribute="instructor" role="form">
			<div class="row form-group">
				<form:label path="name" for="name" class="col-lg-2 control-label"><fmt:message key="admin.addinstructor.form.name" /> </form:label>
				<div class="col-lg-10">
					<form:input path="name" type="text" class="form-control" id="name" />
					<form:errors path="name" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="lastName" for="lname" class="col-lg-2 control-label"><fmt:message key="admin.addinstructor.form.lastname" /> </form:label>
				<div class="col-lg-10">
					<form:input path="lastName" type="text" class="form-control" id="lname" />
					<form:errors path="name" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="ssNumber" for="ssn" class="col-lg-2 control-label"><fmt:message key="admin.addinstructor.form.ssnumber" /> </form:label>
				<div class="col-lg-10">
					<form:input path="ssNumber" type="text" class="form-control" id="ssn" />
					<form:errors path="ssNumber" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="idNumber" for="idn" class="col-lg-2 control-label"><fmt:message key="admin.addinstructor.form.idnumber" /> </form:label>
				<div class="col-lg-10">
					<form:input path="idNumber" type="text" class="form-control" id="idn" />
					<form:errors path="idNumber" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="email" for="email" class="col-lg-2 control-label"><fmt:message key="admin.addinstructor.form.email" /> </form:label>
				<div class="col-lg-10">
					<div class="input-group">
						<form:input path="email" type="email" class="form-control" id="email" />
						<div class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></div>
					</div>
					<form:errors path="email" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="telephone" for="tlf" class="col-lg-2 control-label"><fmt:message key="admin.addinstructor.form.Telephone" /> </form:label>
				<div class="col-lg-10">
				<div class="input-group">
					<form:input path="telephone" type="tel" class="form-control" id="tlf" />
					<div class="input-group-addon"><span class="glyphicon glyphicon-earphone"></span></div>
					</div>
					<form:errors path="telephone" class="text-danger" />
				</div>
			</div>
			
			<p class="text-info"><fmt:message key="admin.addinstructor.form.info1" /></p>
			<p><fmt:message key="admin.addinstructor.form.info2" /></p>
			<div class="text-center">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary"><fmt:message key="admin.addinstructor.form.submit" /></button>
					<button type="reset" class="btn btn-danger"><fmt:message key="admin.addinstructor.form.clear" /></button>
				</div>
			</div>
		</form:form>
		</div>
 	</div>
 </div>
</jsp:body>
</t:template>