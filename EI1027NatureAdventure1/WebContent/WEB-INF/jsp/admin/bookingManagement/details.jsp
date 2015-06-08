<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:body>
		
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/admin/BookingManagement/details/${booking.innerIdBooking}.html"><fmt:message key="breadcrumbs.admin.book.details"  /> <em>${booking.innerIdBooking}</em></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin/BookingManagement.html"><fmt:message key="breadcrumbs.admin.book" /></a>
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a>
	</div>
		
    <h4><fmt:message key="admin.book.details.title" /></h4>
	<h6 class="subtitle"><fmt:message key="admin.book.details.details" /> <strong style="padding-left: 10px;">${booking.innerIdBooking}</strong></h6>
  <div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#bookinginfo" aria-controls="bookinginfo" role="tab" data-toggle="tab"><fmt:message key="admin.book.details.bookingTab" /></a></li>
    <li role="presentation"><a href="#clientinfo" aria-controls="clientinfo" role="tab" data-toggle="tab"><fmt:message key="admin.book.details.clientInfo" /></a></li>
    <li role="presentation"><a href="#activityinfo" aria-controls="activityinfo" role="tab" data-toggle="tab"><fmt:message key="admin.book.details.activityInfo" /></a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel"  class="tab-pane active" id="bookinginfo">
      <div class="container-fluid">
        <h5><fmt:message key="admin.book.details.bookingInfo.title" /></h5>
        <hr>
        <form method="post" role="form">
          <div class="row form-group">
            <label for="innerid" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.innerid" /></label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.innerIdBooking}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="idbook" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.idBook" /></label>
            <div class="col-lg-10">
              <div class="form-control">
              	<c:if test="${empty booking.idBooking}"><fmt:message key="admin.book.details.bookingInfo.idBook.not" /></c:if>
              	<c:if test="${not empty booking.idBooking}"><c:out value="${booking.idBooking}" /></c:if>
              </div>
            </div>
          </div>
          <div class="row form-group">
            <label for="act" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.idAct" /></label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.idAct}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="cli" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.clientID" /></label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.clientId}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="dateC" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.datecreation" /></label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.dateCreation}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="dateA" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.dateactivity" /></label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.dateActivity}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="group" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.groupsize" /></label>
            <div class="col-lg-10">
              <div class="input-group">
                <div class="form-control"><c:out value="${booking.groupSize}" /></div>
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
              </div>
            </div>
          </div>
          <div class="row form-group">
            <label for="price" class="col-lg-2"><fmt:message key="admin.book.details.bookingInfo.price" /></label>
            <div class="col-lg-10">
              <div class="input-group">
                <div class="form-control"> <c:out value="${booking.price}" /></div>
                <div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label"><fmt:message key="admin.book.details.bookingInfo.extra" /></label>
            <textarea class="form-control" rows="3" placeholder="Extra information" disabled>${booking.information}</textarea>
          </div>
        </form>

          <div class="panel panel-default">
            <c:choose>
            	<c:when test="${empty status or status.status eq 'pending'}">
            		<c:set scope="page" var="modals" value="1" />
            		<a data-toggle="collapse" data-parent="#accordion" href="#collapse" aria-expanded="true" aria-controls="collapse">
	            	<div class="panel-heading panel-header-warning" role="tab" id="heading">
	              		<h4 class="panel-title"><fmt:message key="admin.book.details.status.title" /></h4>
	            	</div>
			          </a> <!-- Arreglar CSS -->
			          <div id="collapse" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="heading">
			            <div class="panel-body">
			              <div class="form-group row">
			                <label for="dateR" class="col-lg-2"><fmt:message key="admin.book.details.status.pending.date" /></label>
			                <div class="col-lg-10">
			                  <div class="form-control"><c:out value="${dateToday}" /></div>
			                </div>
			              </div>
			              <div class="row">
			                <div class="col-lg-6">
			                  <div class="panel panel-info">
			                    <div class="panel-heading">
			                      <h3 class="panel-title"><fmt:message key="admin.book.details.status.pending.helptitle" /></h3>
			                    </div>
			                    <div class="panel-body">
			                      <p><fmt:message key="admin.book.details.status.pending.helpcontent1" /></p>
			                      <p><fmt:message key="admin.book.details.status.pending.helpcontent2" /> <strong style="padding-left: 10px; font-size: 17px;"><c:out value="${nummaxbooking}" /></strong></p>
			                      <br>
			                      <a href="#declineModal" data-toggle="modal" data-target="#declineModal"><button type="button" class="btn btn-danger center-block"><fmt:message key="admin.book.details.status.pending.declineBook" /></button></a>
			                    </div>
			                  </div>
			                </div>
			                <div class="col-lg-6">
			                  <table class="table table-hover">
			                    <thead>
			                      <th><fmt:message key="admin.book.details.status.pending.list.instid" /></th>
			                      <th><fmt:message key="admin.book.details.status.pending.list.name" /></th>
			                      <th><fmt:message key="admin.book.details.status.pending.list.lastname" /></th>
			                      <th></th>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="instructor" items="${instructorsAvailable}">
				                      <tr>
				                        <td><c:out value="${instructor.ssNumber}" /></td>
				                        <td><c:out value="${instructor.name}" /></td>
				                        <td><c:out value="${instructor.lastName}" /></td>
				                        <td><a href="#assignModal" data-toggle="modal" data-target="#assignModal${instructor.ssNumber}"><button type="button" class="btn btn-success"><fmt:message key="admin.bookingmanage.status.pending.list.assign" /></button></a></td>
				                      </tr>
				                      <div class="modal fade" id="assignModal${instructor.ssNumber}" tabindex='-1' role="dialog">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header modal-header-success" id="assignModalHead">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4><span class="glyphicon glyphicon-user"></span> Assign instructor</h4>
												</div>
												<div class="modal-body" id="assignModalBody">
									         		<p>Are you sure you want assign the following instructor for this booking?</p>
									         		<div class="row">
									         			<div class="col-lg-6">
									         				<p class="text-center"><strong>Inner ID Booking</strong> <c:out value="${booking.innerIdBooking}" /></p>
									           				<p class="text-center"><strong>ID Booking</strong> <c:out value="${booking.idBooking}" /></p>
									         			</div>
									         			<div class="col-lg-6">
									         				<p class="text-center"><strong>Instructor ID</strong> <c:out value="${instructor.ssNumber}" /></p>
									           				<p class="text-center"><strong>ID Booking</strong> <c:out value="${booking.idBooking}" /></p>
									         			</div>
									         		</div>
									           		
												</div>
									       		<div class="modal-footer" id="assignModalFooter">
									         		<div class="pull-right">
									           			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/assignInstructor/${booking.innerIdBooking}&${instructor.ssNumber}.html"><button class="btn btn-success"><fmt:message key="admin.bookingmanage.status.pending.list.assign" /></button></a>
									           			<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
									         		</div>
									       		</div>
											</div>
										</div>
									</div>
			                      	</c:forEach>
			                    </tbody>
			                  </table>
			                </div> <!-- col-lg-6 -->
			              </div> <!-- row -->
			            </div> <!-- panel body -->
			          </div> <!-- panel collapse -->
            	</c:when>
            	<c:when test="${status.status eq 'accepted'}">
            		<c:set scope="page" var="modals" value="3" />
            		<a data-toggle="collapse" data-parent="#accordion" href="#collapse" aria-expanded="true" aria-controls="collapse">
			          <div class="panel-heading panel-header-success" role="tab" id="heading">
			            <h4 class="panel-title"><fmt:message key="admin.bookingmanage.status.declined.title" /></h4>
			          </div>
			      	</a> <!-- Arreglar CSS -->
			      	<div id="collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading">
			        	<div class="panel-body">
				          	<div class="form-group row">
				            	<label for="dateR" class="col-lg-2"><fmt:message key="admin.bookingmanage.status.declined.date" /></label>
				            	<div class="col-lg-10">
				              		<div class="form-control"><c:out value="${status.dateRevision}" /></div>
				            	</div>
				          	</div>
				          	<div class="form-group row">
				              	<label for="status" class="col-lg-2"><fmt:message key="admin.bookingmanage.status.declined.title" /></label>
				              	<div class="col-lg-10">
				                	<div class="form-control-static text-success" style="font-weight: bold;"><fmt:message key="admin.bookingmanage.status.accepted.accepted" /></div>
				              	</div>
				          	</div>
				
				          	<div class="panel panel-warning">
				            	<div class="panel-heading">
				              		<h3 class="panel-title"><fmt:message key="admin.bookingmanage.status.accepted.warningtitle" /></h3>
				            	</div>
				            	<div class="panel-body">
					              <p><fmt:message key="admin.bookingmanage.status.accepted.warningcontent1" /></p>
					              <p><fmt:message key="admin.bookingmanage.status.accepted.warningcontent2" /> <strong><fmt:message key="admin.bookingmanage.status.accepted.warningcontent3" /></strong> <fmt:message key="admin.bookingmanage.status.accepted.warningcontent4" /> <strong><fmt:message key="admin.bookingmanage.status.accepted.warningcontent5" /></strong>.</p>
					              <br>
					              <div class="text-center">
					                <a href="#changePendingModal" data-toggle="modal" data-target="#changePendingModal"><button type="button" class="btn btn-warning"><fmt:message key="admin.bookingmanage.status.accepted.changepen" /></button></a>
					                <a href="#changeDeclineModal" data-toggle="modal" data-target="#changeDeclineModal"><button type="button" class="btn btn-danger"><fmt:message key="admin.bookingmanage.status.accepted.changedec" /></button></a>
					              </div>
				            	</div>
				          	</div>
						</div> <!-- panel body -->
			          </div> <!-- panel collapse -->
            	</c:when>
            	<c:when test="${status.status eq 'declined'}">
            		<c:set scope="page" var="modals" value="2" />
            		<a data-toggle="collapse" data-parent="#accordion" href="#collapse" aria-expanded="true" aria-controls="collapse">
			            <div class="panel-heading panel-header-danger" role="tab" id="heading">
			              <h4 class="panel-title"><fmt:message key="admin.bookingmanage.status.declined.title" /></h4>
			            </div>
			        </a>
			        <div id="collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading">
			          <div class="panel-body">
			            <div class="form-group row">
			              <label for="dateR" class="col-lg-2"><fmt:message key="admin.bookingmanage.status.accepted.date" /></label>
			              <div class="col-lg-10">
			                <div class="form-control"><c:out value="${status.dateRevision}" /></div>
			              </div>
			            </div>
			            <div class="form-group row">
			                <label for="status" class="col-lg-2"><fmt:message key="admin.bookingmanage.status.accepted.status" /></label>
			                <div class="col-lg-10">
			                  <div class="form-control-static text-danger" style="font-weight: bold;"><fmt:message key="admin.bookingmanage.status.declined.declined" /></div>
			                </div>
			            </div>
			
			            <div class="panel panel-warning">
			              <div class="panel-heading">
			                <h3 class="panel-title"><fmt:message key="admin.bookingmanage.status.accepted.warningtitle" /></h3>
			              </div>
			              <div class="panel-body">
			                <p><fmt:message key="admin.bookingmanage.status.declined.warningcontent1" /></p>
			                <p><fmt:message key="admin.bookingmanage.status.declined.warningcontent2" /></p>
			                <br>
			                <a href="#changePendingModal" data-toggle="modal" data-target="#changePendingModal"><button type="button" class="btn btn-warning center-block"><fmt:message key="admin.bookingmanage.status.declined.change" /></button></a>
			              </div>
			            </div>
					  </div> <!-- panel body -->
			        </div> <!-- panel collapse -->
            	</c:when>
            </c:choose>
    	</div> <!-- Panel -->
      </div>
    </div><!-- tab -->
    <div role="tabpane2" class="tab-pane" id="clientinfo">
      <div class="container-fluid">
        <h4><fmt:message key="admin.book.details.clientInfo.title" /></h4>
        <hr>
        <form method="post" role="form">
          <div class="form-group row">
            <label for="cliId" class="col-lg-2"><fmt:message key="admin.book.details.clientInfo.identifier" /></label>
            <div class="col-lg-10">
              <div id="cliId" class="form-control"><c:out value="${client.clientId}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="cliname" class="col-lg-2"><fmt:message key="admin.book.details.clientInfo.name" /></label>
            <div id="cliname" class="col-lg-10">
              <div class="form-control"><c:out value="${client.clientName}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="clilast" class="col-lg-2"><fmt:message key="admin.book.details.clientInfo.lastName" /></label>
            <div class="col-lg-10">
              <div id="clilast" class="form-control"><c:out value="${client.clientLastName}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="cliemail" class="col-lg-2">Email</label>
            <div class="col-lg-10">
              <div class="input-group">
                <div class="form-control"><c:out value="${client.clientEmail}" /></div>
                <div class="input-group-addon"><a href="mailto:${client.clientEmail}">@</a></div>
              </div>
            </div>
          </div>
        </form>
      </div> <!-- container -->
    </div> <!-- tab -->
    <div role="tabpane3" class="tab-pane" id="activityinfo">
      <div class="container-fluid">
        <h5><fmt:message key="admin.book.details.activityinfo.title" /></h5>
        <hr>
        <form method="post" role="form">
          <div class="form-group row">
            <label for="actId" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.title" /></label>
            <div class="col-lg-10">
              <div id="actId" class="form-control"><c:out value="${activity.idAct}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actName" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.name" /></label>
            <div class="col-lg-10">
              <div id="actName" class="form-control"><c:out value="${activity.name}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actlevel" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.level" /></label>
            <div class="col-lg-10">
              <div class="form-control" id="actlevel"><c:out value="${activity.level}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actsche" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.schedule" /></label>
            <div class="col-lg-10">
              <div id="actsche" class="form-control"><c:out value="${activity.schedule}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actpla" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.place" /></label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${activity.place}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actmin" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.mingr" /></label>
            <div class="col-lg-10">
              <div class="form-control" id="actmin"><c:out value="${activity.minimumGroup}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actmax" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.maxgr" /></label>
            <div class="col-lg-10">
              <div class="form-control" id="actmax"><c:out value="${activity.maximumGroup}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actinstr" class="col-lg-2"><fmt:message key="admin.book.details.activityinfo.instructors" /></label>
            <div class="col-lg-10">
              <div class="form-control" id="actinstr"><c:out value="${activity.getNInstructors()}" /></div>
            </div>
          </div>
        </form>
      </div>
    </div> <!-- tab -->
  </div>
  </div>
  
  	<!-- Modals para pending -->
	<c:if test="${modals eq 1 }">
		<div class="modal fade" id="declineModal" tabindex='-1' role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header modal-header-danger" id="declineModalHead">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4><fmt:message key="admin.bookingmanage.status.modal.1.title" /></h4>
					</div>
					<div class="modal-body" id="declineModalBody">
		         		<p><fmt:message key="admin.bookingmanage.status.modal.1.msg" /></p>
		           		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.iid" /></strong> <c:out value="${booking.innerIdBooking}" /></p>
		           		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.id" /></strong> <c:out value="${booking.idBooking}" /></p>
					</div>
		       		<div class="modal-footer" id="declineModalFooter">
		         		<div class="pull-right">
		           			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/decline/${booking.innerIdBooking}.html"><button class="btn btn-danger"><fmt:message key="admin.bookingmanage.status.modal.btn.accept" /></button></a>
		           			<button class="btn btn-primary" data-dismiss="modal"><fmt:message key="admin.bookingmanage.status.modal.btn.cancel" /></button>
		         		</div>
		       		</div>
				</div>
			</div>
		</div>
	</c:if>
	<!-- Modals para status declined -->
	<c:if test="${modals eq 2}">
	  	<div class="modal fade" id="changePendingModal" tabindex='-1' role="dialog">
	  		<div class="modal-dialog">
	  			<div class="modal-content">
	  				<div class="modal-header modal-header-warning" id="changePendingModalHead">
	  					<button type="button" class="close" data-dismiss="modal">&times;</button>
	  					<h4><fmt:message key="admin.bookingmanage.status.modal.2.title" /></h4>
	  				</div>
	  				<div class="modal-body" id="changePendingModalBody">
	            		<p><fmt:message key="admin.bookingmanage.status.modal.2.msg" /></p>
	              		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.iid" /></strong> <c:out value="${booking.innerIdBooking}" /></p>
	              		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.id" /></strong> <c:out value="${booking.idBooking}" /></p>
	              		<p class="text-center"><strong class="text-danger"><fmt:message key="admin.bookingmanage.status.modal.declined" /></strong> <span class="glyphicon glyphicon-chevron-right"></span> <strong class="text-warning"><fmt:message key="admin.bookingmanage.status.modal.pending" /></strong></p>
	  				</div>
	          		<div class="modal-footer" id="changePendingModalFooter">
	            		<div class="pull-right">
	              			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/toPending/${booking.innerIdBooking}.html"><button class="btn btn-danger"><fmt:message key="admin.bookingmanage.status.modal.btn.accept" /></button></a>
	              			<button class="btn btn-primary" data-dismiss="modal"><fmt:message key="admin.bookingmanage.status.modal.btn.cancel" /></button>
	            		</div>
	          		</div>
	  			</div>
	  		</div>
	  	</div>
	</c:if>
	<!-- Modals para status accepted -->
	<c:if test="${modals eq 3}">
	  	<div class="modal fade" id="changePendingModal" tabindex='-1' role="dialog">
	  		<div class="modal-dialog">
	  			<div class="modal-content">
	  				<div class="modal-header modal-header-warning" id="changePendingModalHead">
	  					<button type="button" class="close" data-dismiss="modal">&times;</button>
	  					<h4><fmt:message key="admin.bookingmanage.status.modal.2.title" /></h4>
	  				</div>
	  				<div class="modal-body" id="changePendingModalBody">
	            		<p><fmt:message key="admin.bookingmanage.status.modal.3.msg" /></p>
	              		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.iid" /></strong> <c:out value="${booking.innerIdBooking}" /></p>
	              		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.id" /></strong> <c:out value="${booking.idBooking}" /></p>
	              		<p class="text-center"><strong class="text-success"><fmt:message key="admin.bookingmanage.status.modal.accepted" /></strong> <span class="glyphicon glyphicon-chevron-right"></span> <strong class="text-warning"><fmt:message key="admin.bookingmanage.status.modal.pending" /></strong></p>
	  				</div>
	          		<div class="modal-footer" id="changePendingModalFooter">
	            		<div class="pull-right">
	              			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/toPending/${booking.innerIdBooking}.html"><button class="btn btn-danger"><fmt:message key="admin.bookingmanage.status.modal.btn.accept" /></button></a>
	              			<button class="btn btn-primary" data-dismiss="modal"><fmt:message key="admin.bookingmanage.status.modal.btn.cancel" /></button>
	            		</div>
	          		</div>
	  			</div>
	  		</div>
	  	</div>
	  	<div class="modal fade" id="changeDeclineModal" tabindex='-1' role="dialog">
	  		<div class="modal-dialog">
	  			<div class="modal-content">
	  				<div class="modal-header modal-header-danger" id="changeDeclineModalHead">
	  					<button type="button" class="close" data-dismiss="modal">&times;</button>
	  					<h4><fmt:message key="admin.bookingmanage.status.accepted.changedec" /></h4>
	  				</div>
	  				<div class="modal-body" id="changeDeclineModalBody">
	            		<p><fmt:message key="admin.bookingmanage.status.modal.4.msg" /></p>
	              		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.iid" /></strong> <c:out value="${booking.innerIdBooking}" /></p>
	              		<p class="text-center"><strong><fmt:message key="admin.bookingmanage.status.modal.id" /></strong> <c:out value="${booking.idBooking}" /></p>
	              		<p class="text-center"><strong class="text-success"><fmt:message key="admin.bookingmanage.status.modal.accepted" /></strong> <span class="glyphicon glyphicon-chevron-right"></span> <strong class="text-danger"><fmt:message key="admin.bookingmanage.status.modal.declined" /></strong></p>
	  				</div>
	          		<div class="modal-footer" id="changePendingModalFooter">
	            		<div class="pull-right">
	              			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/decline/${booking.innerIdBooking}.html"><button class="btn btn-danger"><fmt:message key="admin.bookingmanage.status.modal.btn.accept" /></button></a>
	              			<button class="btn btn-primary" data-dismiss="modal"><fmt:message key="admin.bookingmanage.status.modal.btn.cancel" /></button>
	            		</div>
	          		</div>
	  			</div>
	  		</div>
	  	</div>
	</c:if>
</jsp:body>
</t:template>