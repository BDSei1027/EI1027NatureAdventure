<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<t:template>
	<jsp:body>
	<h4><fmt:message key="instructor.details.title.1" /> <strong><c:out value="${booking.idBooking}" /></strong> <fmt:message key="instructor.details.title.2" /></h4>
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title"><fmt:message key="instructor.details.form.title.1" /> <c:out value="${booking.idBooking}" /></h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.bid" /></div>
							<div class="form-control"><c:out value="${booking.idBooking}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.aid" /></div>
							<div class="form-control"><c:out value="${booking.idAct}" /></div>
							<div class="input-group-addon"><a data-toggle="collapse" href="#collapseActivity" aria-expanded="false">
								<span class="caret"></span></a>
							</div>
						</div>
						<div class="collapse" id="collapseEActivity">
  							<div class="well well-sm">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon"><fmt:message key="instructor.details.form.activity" /></div>
										<div class="form-group"><c:out value="${activity.name}" /></div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon"><fmt:message key="instructor.details.form.level" /></div>
										<div class="form-group"><c:out value="${activity.level}" /></div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon"><fmt:message key="instructor.details.form.schedule" /></div>
										<div class="form-group"><c:out value="${activity.schedule}" /></div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon"><fmt:message key="instructor.details.form.place" /></div>
										<div class="form-group"><c:out value="${activity.place}" /></div>
									</div>
								</div>
  							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.date" /></div>
							<div class="form-control"><c:out value="${booking.dateActivity}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.group" /></div>
							<div class="form-control"><c:out value="${booking.groupSize}" /></div>
							<div class="input-group-addon">Pers.</div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.info" /></div>
							<div class="form-control" style="height:auto;">
								<c:choose>
									<c:when test="${empty booking.information}">
										<div class="text-muted"><fmt:message key="instructor.details.form.noinfo" /></div>
									</c:when>
									<c:otherwise>
										<fmt:message key="instructor.details.form.expand" />
									</c:otherwise>								
								</c:choose>
							</div>
							<c:if test="${empty booking.information}">
								<div class="input-group-addon"><a data-toggle="collapse" href="#collapseActivity" aria-expanded="false">
									<span class="caret"></span></a>
								</div>
								<div class="collapse" id="collapseActivity">
									<div class="well">
										${booking.information}
									</div>
								</div>
							</c:if>
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
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.cli.name" /></div>
							<div class="form-control"><c:out value="${client.name}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon"><fmt:message key="instructor.details.form.cli.ln" /></div>
							<div class="form-control"><c:out value="${client.lastName}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="form-control"><c:out value="${client.email}" /></div>
							<div class="input-group-addon"><a href="mailto:<c:out value="${client.email}" />"><span class=" glyphicon glyphicon-envelope"></span></a></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> <!-- row -->


</jsp:body>
</t:template>