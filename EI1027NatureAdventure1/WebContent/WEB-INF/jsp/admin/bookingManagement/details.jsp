<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
		
    <h2>Booking management</h2>
	<hr>
	<h3>Details of booking <strong>${booking.innerIdBooking}</strong></h3>
  <div role="tabpanel">

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a href="#bookinginfo" aria-controls="bookinginfo" role="tab" data-toggle="tab">Booking information</a></li>
    <li role="presentation"><a href="#clientinfo" aria-controls="clientinfo" role="tab" data-toggle="tab">Client information</a></li>
    <li role="presentation"><a href="#activityinfo" aria-controls="activityinfo" role="tab" data-toggle="tab">Activity information</a></li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel"  class="tab-pane active" id="bookinginfo">
      <div class="container-fluid">
        <h4>Information about the booking</h4>
        <hr>
        <form:form method="post" action="#" modelAttribute="booking">
          <div class="row form-group">
            <label for="innerid" class="col-lg-2">InnerID Booking</label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.innerIdBooking}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="idbook" class="col-lg-2">ID Booking</label>
            <div class="col-lg-10">
              <div class="form-control">
              	<c:if test="${empty booking.idBooking}">No assigned yet</c:if>
              	<c:if test="${not empty booking.idBooking}"><c:out value="${booking.idBooking}" /></c:if>
              </div>
            </div>
          </div>
          <div class="row form-group">
            <label for="act" class="col-lg-2">Activity ID</label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.idAct}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="cli" class="col-lg-2">Client ID</label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.clientId}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="dateC" class="col-lg-2">Date of creation</label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.dateCreation}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="dateA" class="col-lg-2">Date of the activity</label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${booking.dateActivity}" /></div>
            </div>
          </div>
          <div class="row form-group">
            <label for="group" class="col-lg-2">Group size</label>
            <div class="col-lg-10">
              <div class="input-group">
                <div class="form-control"><c:out value="${booking.groupSize}" /></div>
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
              </div>
            </div>
          </div>
          <div class="row form-group">
            <label for="price" class="col-lg-2">Price of the booking</label>
            <div class="col-lg-10">
              <div class="input-group">
                <div class="form-control"> <c:out value="${booking.price}" /></div>
                <div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <form:label for="infor" path="information">Extra information</form:label>
            <form:textarea class="form-control" rows="3" path="information" placeholder="Extra information" />
          </div>
        </form:form>

          <div class="panel panel-default">
            <c:choose>
            	<c:when test="${empty status}">
            		<c:set scope="page" var="modals" value="1" />
            		<a data-toggle="collapse" data-parent="#accordion" href="#collapse" aria-expanded="true" aria-controls="collapse">
	            	<div class="panel-heading panel-header-warning" role="tab" id="heading">
	              		<h4 class="panel-title">Status</h4>
	            	</div>
			          </a> <!-- Arreglar CSS -->
			          <div id="collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading">
			            <div class="panel-body">
			              <div class="form-group row">
			                <label for="dateR" class="col-lg-2">Date  of revision</label>
			                <div class="col-lg-10">
			                  <div class="form-control"><c:out value="${dateToday}" /></div>
			                </div>
			              </div>
			              <div class="row">
			                <div class="col-lg-6">
			                  <div class="panel panel-info">
			                    <div class="panel-heading">
			                      <h3 class="panel-title">Help!</h3>
			                    </div>
			                    <div class="panel-body">
			                      <p>In the table in the right you have to select an instructor for
			                        this booking or decline the booking with the button below here.</p>
			                      <p>The identification of the booking will be <strong style="padding-left: 10px; font-size: 17px;"><c:out value="${nummaxbooking}" /></strong></p>
			                      <br>
			                      <a href="#declineModal" data-toggle="modal" data-target="#declineModal"><button type="button" class="btn btn-danger center-block">Decline booking</button></a>
			                    </div>
			                  </div>
			                </div>
			                <div class="col-lg-6">
			                  <table class="table table-hover">
			                    <thead>
			                      <th>Instructor ID</th>
			                      <th>Name</th>
			                      <th>Last name</th>
			                      <th></th>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="instructor" items="${instructorsAvaliable}">
				                      <tr>
				                        <td><c:out value="${instructor.ssNumber}" /></td>
				                        <td><c:out value="${instructor.name}" /></td>
				                        <td><c:out value="${instructor.lastName}" /></td>
				                        <td><a href="#assignModal" data-toggle="modal" data-target="#assignModal${instructor.ssNumber}"><button type="button" class="btn btn-success">Assign</button></a></td>
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
									           			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/assignInstructor?idb=${booking.innerIdBooking}&instr=${instructor.ssNumber}.html"><button class="btn btn-success">Assign</button></a>
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
			            <h4 class="panel-title">Status</h4>
			          </div>
			      	</a> <!-- Arreglar CSS -->
			      	<div id="collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading">
			        	<div class="panel-body">
				          	<div class="form-group row">
				            	<label for="dateR" class="col-lg-2">Date  of revision</label>
				            	<div class="col-lg-10">
				              		<div class="form-control"><c:out value="${status.dateRevision}" /></div>
				            	</div>
				          	</div>
				          	<div class="form-group row">
				              	<label for="status" class="col-lg-2">Status</label>
				              	<div class="col-lg-10">
				                	<div class="form-control-static text-success" style="font-weight: bold;">Accepted</div>
				              	</div>
				          	</div>
				
				          	<div class="panel panel-warning">
				            	<div class="panel-heading">
				              		<h3 class="panel-title">Warning!</h3>
				            	</div>
				            	<div class="panel-body">
					              <p>This booking has been accepted before.</p>
					              <p>If there is some necessity to change the status of this booking click on any botton below here.
					                One is for change it to <strong>Pending</strong> and other to <strong>Declined</strong>.</p>
					              <br>
					              <div class="text-center">
					                <a href="#changePendingModal" data-toggle="modal" data-target="#changePendingModal"><button type="button" class="btn btn-warning">Change to pending</button></a>
					                <a href="#changeDeclineModal" data-toggle="modal" data-target="#changeDeclineModal"><button type="button" class="btn btn-danger">Change to decline</button></a>
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
			              <h4 class="panel-title">Status</h4>
			            </div>
			        </a>
			        <div id="collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading">
			          <div class="panel-body">
			            <div class="form-group row">
			              <label for="dateR" class="col-lg-2">Date  of revision</label>
			              <div class="col-lg-10">
			                <div class="form-control"><c:out value="${status.dateRevision}" /></div>
			              </div>
			            </div>
			            <div class="form-group row">
			                <label for="status" class="col-lg-2">Status</label>
			                <div class="col-lg-10">
			                  <div class="form-control-static text-danger" style="font-weight: bold;">Declined</div>
			                </div>
			            </div>
			
			            <div class="panel panel-warning">
			              <div class="panel-heading">
			                <h3 class="panel-title">Warning!</h3>
			              </div>
			              <div class="panel-body">
			                <p>This booking has been rejected before.</p>
			                <p>If you need to change the status of this booking click on the botton below here.</p>
			                <br>
			                <a href="#changePendingModal" data-toggle="modal" data-target="#changePendingModal"><button type="button" class="btn btn-warning center-block">Change to pending</button></a>
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
        <h4>Information about the client of the booking</h4>
        <hr>
        <form method="post" role="form">
          <div class="form-group row">
            <label for="cliId" class="col-lg-2">Identifier</label>
            <div class="col-lg-10">
              <div id="cliId" class="form-control"><c:out value="${client.clientId}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="cliname" class="col-lg-2">Name</label>
            <div id="cliname" class="col-lg-10">
              <div class="form-control"><c:out value="${client.clienName}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="clilast" class="col-lg-2">Last name</label>
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
        <h4>Information about the activity of the booking</h4>
        <hr>
        <form method="post" role="form">
          <div class="form-group row">
            <label for="actId" class="col-lg-2">Identifier</label>
            <div class="col-lg-10">
              <div id="actId" class="form-control"><c:out value="${activity.idAct}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actName" class="col-lg-2">Name</label>
            <div class="col-lg-10">
              <div id="actName" class="form-control"><c:out value="${activity.name}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actlevel" class="col-lg-2">Level</label>
            <div class="col-lg-10">
              <div class="form-control" id="actlevel"><c:out value="${activity.level}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actsche" class="col-lg-2">Schedule</label>
            <div class="col-lg-10">
              <div id="actsche" class="form-control"><c:out value="${activity.schedule}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actpla" class="col-lg-2">Place</label>
            <div class="col-lg-10">
              <div class="form-control"><c:out value="${activity.place}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actmin" class="col-lg-2">Minimum group</label>
            <div class="col-lg-10">
              <div class="form-control" id="actmin"><c:out value="${activity.minimumGroup}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actmax" class="col-lg-2">Maximum group</label>
            <div class="col-lg-10">
              <div class="form-control" id="actmax"><c:out value="${activity.maximumGroup}" /></div>
            </div>
          </div>
          <div class="form-group row">
            <label for="actinstr" class="col-lg-2">Instructors</label>
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
						<h4><span class="glyphicon glyphicon-warning-sign"></span> Decline booking</h4>
					</div>
					<div class="modal-body" id="declineModalBody">
		         		<p>Are you sure you want decline the following booking?</p>
		           		<p class="text-center"><strong>Inner ID Booking</strong> <c:out value="${booking.innerIdBooking}" /></p>
		           		<p class="text-center"><strong>ID Booking</strong> <c:out value="${booking.idBooking}" /></p>
					</div>
		       		<div class="modal-footer" id="declineModalFooter">
		         		<div class="pull-right">
		           			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/decline?idb=${booking.innerIdBooking}.html"><button class="btn btn-danger">Decline</button></a>
		           			<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
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
	  					<h4><span class="glyphicon glyphicon-warning-sign"></span> Change to pending</h4>
	  				</div>
	  				<div class="modal-body" id="changePendingModalBody">
	            		<p>Are you sure you want to change the status of the following booking from <strong>declined</strong>
	              		  to <strong>pending</strong>?</p>
	              		<p class="text-center"><strong>Inner ID Booking</strong> <c:out value="${booking.innerIdBooking}" /></p>
	              		<p class="text-center"><strong>ID Booking</strong> <c:out value="${booking.idBooking}" /></p>
	              		<p class="text-center"><strong class="text-danger">Declined</strong> <span class="glyphicon glyphicon-chevron-right"></span> <strong class="text-warning">Pending</strong></p>
	  				</div>
	          		<div class="modal-footer" id="changePendingModalFooter">
	            		<div class="pull-right">
	              			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/toPending?idb=${booking.innerIdBooking}.html"><button class="btn btn-danger">Accept</button></a>
	              			<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
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
	  					<h4><span class="glyphicon glyphicon-warning-sign"></span> Change to pending</h4>
	  				</div>
	  				<div class="modal-body" id="changePendingModalBody">
	            		<p>Are you sure you want to change the status of the following booking from <strong>accepted</strong>
	              		  to <strong>pending</strong>?</p>
	              		<p class="text-center"><strong>Inner ID Booking</strong> <c:out value="${booking.innerIdBooking}" /></p>
	              		<p class="text-center"><strong>ID Booking</strong> <c:out value="${booking.idBooking}" /></p>
	              		<p class="text-center"><strong class="text-success">Accepted</strong> <span class="glyphicon glyphicon-chevron-right"></span> <strong class="text-warning">Pending</strong></p>
	  				</div>
	          		<div class="modal-footer" id="changePendingModalFooter">
	            		<div class="pull-right">
	              			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/accept2Pending?idb=${booking.innerIdBooking}.html"><button class="btn btn-danger">Accept</button></a>
	              			<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
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
	  					<h4><span class="glyphicon glyphicon-warning-sign"></span> Change to decline</h4>
	  				</div>
	  				<div class="modal-body" id="changeDeclineModalBody">
	            		<p>Are you sure you want to change the status of the following booking from <strong>accepted</strong>
	              		  to <strong>declined</strong>?</p>
	              		<p class="text-center"><strong>Inner ID Booking</strong> <c:out value="${booking.innerIdBooking}" /></p>
	              		<p class="text-center"><strong>ID Booking</strong> <c:out value="${booking.idBooking}" /></p>
	              		<p class="text-center"><strong class="text-success">Accepted</strong> <span class="glyphicon glyphicon-chevron-right"></span> <strong class="text-danger">Declined</strong></p>
	  				</div>
	          		<div class="modal-footer" id="changePendingModalFooter">
	            		<div class="pull-right">
	              			<a href="${pageContext.request.contextPath}/admin/bookingManagement/details/accept2Decline?idb=${booking.innerIdBooking}.html"><button class="btn btn-danger">Accept</button></a>
	              			<button class="btn btn-primary" data-dismiss="modal">Cancel</button>
	            		</div>
	          		</div>
	  			</div>
	  		</div>
	  	</div>
	</c:if>
	
</jsp:body>
</t:template>