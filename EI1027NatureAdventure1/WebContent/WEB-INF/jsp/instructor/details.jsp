<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- HAY Q REVISARLA -->
<t:template>
	<jsp:body>
	<h2>Booking's details</h2>
	<div class="row">
		<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Booking <c:out value="${booking.idBooking}" /></h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Booking ID</div>
							<div class="form-control"><c:out value="${booking.idBooking}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Activity ID</div>
							<div class="form-control"><c:out value="${booking.idAct}" /></div>
							<div class="input-group-addon"><a data-toggle="collapse" href="#collapseActivity" aria-expanded="false">
								<span class="caret"></span></a>
							</div>
						</div>
						<div class="collapse" id="collapseEActivity">
  							<div class="well well-sm">
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">Activity</div>
										<div class="form-group"><c:out value="${activity.name}" /></div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">Level</div>
										<div class="form-group"><c:out value="${activity.level}" /></div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">Schedule</div>
										<div class="form-group"><c:out value="${activity.schedule}" /></div>
									</div>
								</div>
								<div class="form-group">
									<div class="input-group">
										<div class="input-group-addon">Place</div>
										<div class="form-group"><c:out value="${activity.place}" /></div>
									</div>
								</div>
  							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Date</div>
							<div class="form-control"><c:out value="${booking.dateActivity}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Group</div>
							<div class="form-control"><c:out value="${booking.groupSize}" /></div>
							<div class="input-group-addon">Pers.</div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Information</div>
							<div class="form-control" style="height:auto;">
								<c:choose>
									<c:when test="${empty booking.information}">
										<div class="text-muted">No information</div>
									</c:when>
									<c:otherwise>
										Expand...
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
					<h3 class="panel-title">Client's information</h3>
				</div>
				<div class="panel-body">
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Name</div>
							<div class="form-control"><c:out value="${client.name}" /></div>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group">
							<div class="input-group-addon">Last name</div>
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