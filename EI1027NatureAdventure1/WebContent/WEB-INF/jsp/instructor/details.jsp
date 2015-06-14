<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<t:template>
	<jsp:body>
		<div class="crumbs">
		<a href="${pageContext.request.contextPath}/instructor/details/${booking.idBooking}.html"><fmt:message key="breadcrumbs.instr.book" /> <em>${booking.idBooking}</em></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/instructor.html"><fmt:message key="breadcrumbs.instr.main" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a> 
	</div>
	
	<h4><fmt:message key="instructor.details.title.1" /> <strong><c:out value="${booking.idBooking}" /></strong> <fmt:message key="instructor.details.title.2" /></h4>
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="instructor.details.form.title" /> <c:out value="${booking.idBooking}" /></h3>
				</div>
				<div class="panel-body">
					<div class="form-group row">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.bid" /></div>
						<div class="col-lg-9">
							<div class="form-control"><c:out value="${booking.idBooking}" /></div>
						</div>
					</div>
					<div class="form-group row">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.aid" /></div>
							<div class="col-lg-9">
								<div class="input-group">
									<div class="form-control"><c:out value="${booking.idAct}" /></div>
									<div class="input-group-addon"><a data-toggle="collapse" href="#collapseActivity" aria-expanded="false">
										<span class="fui-triangle-down" style="color: #FFF;"></span></a>
									</div>
								</div>
								<div class="collapse" id="collapseActivity">
  							<div class="well well-sm" style="margin-top: 5px; margin-bottom: 0px; font-size: 14px;">
  								<dl style="margin-bottom: 0px;">
  								<dt><fmt:message key="instructor.details.form.activity" /></dt> <c:if test="${lang eq 'es'}"><dd>${activity.nombre}</dd></c:if><c:if test="${lang eq 'en'}"><dd>${activity.name}</dd></c:if>
  								<dt><fmt:message key="instructor.details.form.level" /></dt> <dd>${activity.level}</dd>
  								<dt><fmt:message key="instructor.details.form.schedule" /></dt> <dd>${activity.schedule}</dd>
  								<dt><fmt:message key="instructor.details.form.place" /></dt> <dd>${activity.place}</dd>
  								</dl>
  							</div>
						</div> <!-- collapse -->
							</div>
					</div> <!-- form group -->
					<div class="form-group row ">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.date" /></div>
						<div class="col-lg-9">
							<div class="form-control"><c:out value="${booking.dateActivity}" /></div>
						</div>
					</div>
					<div class="form-group row">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.group" /></div>
						<div class="col-lg-9">
							<div class="form-control"><c:out value="${booking.groupSize}" /></div>
						</div>
					</div>
					<div class="form-group row">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.info" /></div>
						<div class="col-lg-9">
							<c:choose>
								<c:when test="${empty booking.information}">
									<div class="form-control">
										<fmt:message key="instructor.details.form.noinfo" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="input-group">
										<div class="form-control">
											<fmt:message key="instructor.details.form.expand" />
										</div>
										<div class="input-group-addon"><a data-toggle="collapse" href="#collapseInfo" aria-expanded="false">
											<span class="fui-triangle-down" style="color: #FFF;"></span></a>
										</div>
									</div>
									<div class="collapse" id="collapseInfo">
										<div class="well well-sm" style="font-size: 14px; margin-top: 5px; margin-right: 42px; border-radius: 6px;">
											${booking.information}
										</div>
									</div>
								</c:otherwise>								
							</c:choose>
						</div>
					</div>
				</div>
			</div> <!-- panel -->
			
		</div> <!-- col -->
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="instructor.details.form.title.cli" /></h3>
				</div>
				<div class="panel-body">
					<div class="form-group row">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.cli.name" /></div>
						<div class="col-lg-9">
							<div class="form-control"><c:out value="${client.clientName}" /></div>
						</div>
					</div>
					<div class="form-group row">
						<div class="control-label col-lg-3"><fmt:message key="instructor.details.form.cli.ln" /></div>
						<div class="col-lg-9">
							<div class="form-control"><c:out value="${client.clientLastName}" /></div>
						</div>
					</div>
					<div class="form-group row">
						<div class="control-label col-lg-3">Email</div>
						<div class="col-lg-9">
						<div class="input-group">
							<div class="form-control"><c:out value="${client.clientEmail}" /></div>
							<div class="input-group-addon"><a href="mailto:<c:out value="${client.clientEmail}" />"><span class=" glyphicon glyphicon-envelope" style="color: #FFF;"></span></a></div>
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> <!-- row -->


</jsp:body>
</t:template>