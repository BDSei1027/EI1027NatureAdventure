<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:template>
<jsp:body>
	<c:if test="${lang eq 'es'}">
		<c:set var="actName" value="${activity.nombre}" scope="page" />
		<c:choose>
			<c:when test="${activity.level eq 0}"><c:set var="lvl" value="Básico" /></c:when>
			<c:when test="${activity.level eq 1}"><c:set var="lvl" value="Fácil" /></c:when>
			<c:when test="${activity.level eq 2}"><c:set var="lvl" value="Medio" /></c:when>
			<c:when test="${activity.level eq 3}"><c:set var="lvl" value="Alto" /></c:when>
		</c:choose>
	</c:if>
	<c:if test="${lang eq 'en'}">
		<c:set var="actName" value="${activity.name}" scope="page" />
		<c:choose>
			<c:when test="${activity.level eq 0}"><c:set var="lvl" value="Beginner" /></c:when>
			<c:when test="${activity.level eq 1}"><c:set var="lvl" value="Easy" /></c:when>
			<c:when test="${activity.level eq 2}"><c:set var="lvl" value="Medium" /></c:when>
			<c:when test="${activity.level eq 3}"><c:set var="lvl" value="High" /></c:when>
		</c:choose>
	</c:if>	
	<h4><fmt:message key="booking.title" /></h4>
	<h6 class="subtitle"><fmt:message key="booking.subtitle" /></h6>
	
	<div class="row">
		<div class="col-lg-3">
			<div class="container-fluid help">
				<div class="help-heading"><fmt:message key="booking.panel.help.title" /></div>
				<div class="help-body">
					<fmt:message key="booking.panel.help" />
				</div>
			</div>
		</div> <!-- col -->
		<div class="col-lg-9">
			<div class="panel panel-warning">
				<div class="panel-heading"><h5 class="panel-title"><fmt:message key="booking.panel.act" /></h5></div>
				<div class="panel-body">
					<p><fmt:message key="booking.panel.act.msg" /></p>
					<div class="modalList" style="padding-left: 20px;">
					<dl>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.act" /></dt><dd><c:out value="${actName}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.lvl" /></dt><dd><c:out value="${lvl}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.sche" /></dt><dd style="text-transform: capitalize;"><c:out value="${activity.schedule}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.max" /></dt><dd><c:out value="${activity.maximumGroup}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.min" /></dt><dd><c:out value="${activity.minimumGroup}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.price" /></dt><dd><c:out value="${activity.price}" /> &euro;</dd>
					</dl>
					</div>
				</div>
			</div> <!-- panel -->

			<div class="panel panel-info">
	<div class="panel-heading"><h5 class="panel-title"><fmt:message key="booking.book" /></h5></div>
	<div class="panel-body">
		<form:form modelAttribute="booking" action="${pageContext.request.contextPath}/activities/createBookingRegistered/${activity.idAct}.html" method="post" role="form">
			<h5 class="text-center"><fmt:message key="booking.book.act" /> <c:out value="${actName}" /></h5>
			<div class="form-group row">
				<form:label path="dateActivity" class="col-lg-3 control-label"><fmt:message key="booking.book.date" /></form:label>
				<div class="col-lg-9">
					<form:input path="dateActivity" class="form-control datepicker" type="text" readonly="true" cssStyle="background-color: #FFF; opacity: 1; border-color: #BDC3C7; color: inherit;" />
					<form:errors path="dateActivity" class="text-danger" />
				</div>
			</div>
			<div class="form-group row">
				<form:label path="groupSize" class="col-lg-3 control-label"><fmt:message key="booking.book.size" /></form:label>
				<div class="col-lg-9">
				<form:input path="groupSize" type="number" step="1" class="form-control" id="grSz" oninput="javascript:updatePrice();"/>
				<form:errors path="groupSize" class="text-danger" />
				</div>
			</div>

			<div class="form-group row">
				<div class="col-lg-12">
					<form:label path="information" class="control-label"><fmt:message key="booking.book.information" /></form:label>
					<form:textarea path="information" class="form-control" />
					<form:errors path="information" class="text-danger" />
				</div>
			</div>
			<form:hidden path="innerIdBooking" />
			<form:hidden path="idBooking" />
		
		<div class="form-group row">
			<div class="control-label col-lg-2"><fmt:message key="booking.book.price" />: </div>
			<div class="col-lg-10">
				<div class="input-group">
					<div class="form-control" id="prc"></div>
					<div class="input-group-addon">&euro;</div>
				</div>
			</div>
		</div>
		
		<div class="text-center" style="margin-top: 20px;">
			<div class="btn-group">
				<button type="submit" class="btn btn-primary"><fmt:message key="booking.btn.submit" /></button>
				<button type="reset" class="btn btn-danger" onClick="javascript:clearField();"><fmt:message key="booking.btn.clear" /></button>
			</div>
		</div>
		</form:form>
	</div>
		</div> <!-- col -->
	</div> <!-- row -->
	</div>

<script type="text/javascript">
var price = ${activity.price}
function updatePrice()
{
    var groupSize = document.getElementById("grSz").value;
    precioTotal = price * groupSize
    document.getElementById("prc").innerHTML = precioTotal.toFixed(2);
}
function clearField()
{
	document.getElementById("prc").innerHTML = '';
}
</script>
<c:if test="${lang eq 'es'}">
	<script>
	    $('.datepicker').datepicker({
	    	format: 'dd/mm/yyyy',
	    	weekStart: 1,
	    	todayHighlight: true,
	    	language: 'es'
	    })
	</script>
</c:if>
<c:if test="${lang eq 'en'}">
	<script>
	    $('.datepicker').datepicker({
	    	format: 'mm/dd/yyyy',
	    	weekStart: 1,
	    	todayHighlight: true,
	    	language: 'en'
	    })
	</script>
</c:if>

</jsp:body>
</t:template>
