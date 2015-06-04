<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 


<t:template>
<jsp:body>
	<div class="clientMain">
		<h4>Welcome <c:out value="${name}" /></h4>
		<p class="subtitle">Welcome our dear customer, <strong><c:out value="${name}" /></strong>, this is your main page.</p>
		
		<div id="actionsHelp" class="row">
			<div id="action" class="col-lg-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="${pageContext.request.contextPath}/customer/main.html">Main</a></li>
					<li><a href="${pageContext.request.contextPath}/customer/history.html">History</a>
					<li class="activeBooking"><a>Bookings <span class="mybadge"><c:out value="${numbookings}" /></span></a></li>
				</ul>
			</div>
			<div id="help" class="col-lg-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Help!</div>
					</div>
					<div class="panel-body">
						<p>This page shows you, your future activities to do.</p>
						<p>You can see them below. click on <span class="caret"></span> to expand the information.</p>
					</div>
				</div>
			</div>
		</div>
		
		<c:forEach var="booking" items="${bookings}">
			    <div class="booking">
			    	<div class="booking-title">
			    		<h5 class="pull-left"><c:out value="${booking.nameActivity}" /><small><c:out value="${booking.dateActivity}" /></small></h5>
			    		<a href="#collapseAct" data-toggle="collapse" aria-expanded="false" aria-controls="collapse" class="pull-right"><span class="caret"></span></a>
			    		<div style="clear:both;"></div>
			    	</div>
			    	<div class="collapse" id="collapseAct">
				    	<div class="booking-body row">
				        	<div class="col-lg-5">
				        		<div class="booking-activity"><h4 style="margin-bottom: 0px;"><c:out value="${booking.nameActivity}" /> <small><c:out value="${booking.level}" /></small></h4></div>
				        		<div class="booking-id">Identifier: <c:out value="${booking.idBooking}" /></div>
				        	</div>
				        	<div class="col-lg-3">
				          		<div class="booking-date">
				            		<h6>Date: <small><c:out value="${booking.dateActivity}" /></small></h6>
				          		</div>
				          		<div class="booking-group">
				            		<h6>Group Size: <small><c:out value="${booking.groupSize}" /></small></h6>
				          		</div>
				          		<div class="booking-place">
				           	 		<h6>Place: <small><c:out value="${booking.place}" /></small></h6>
				          		</div>
				        	</div>
				        	<div class="col-lg-4">
				          		<div class="booking-status">
				          			<c:choose>
				          				<c:when test="${booking.status eq 0}">
						            		<div class="pending">pending</div>
				            			</c:when>
				          				<c:when test="${booking.status eq 1}">
						            		<div class="accepted">accepted</div>
						            		<div class="date">Date of revision <c:out value="${booking.dateRevision}" /></div>
				            			</c:when>
				            			<c:when test="${booking.status eq 2}">
						            		<div class="declined">declined</div>
						            		<div class="date">Date of revision <c:out value="${booking.dateRevision}" /></div>
				            			</c:when>
				            		</c:choose>
				          		</div>
				        	</div>
				    	</div>
			    	</div>
			    </div>
		</c:forEach>
	</div>
</jsp:body>
</t:template>