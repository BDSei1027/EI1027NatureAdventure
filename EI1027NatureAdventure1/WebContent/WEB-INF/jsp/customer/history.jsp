<%@page contentType="text/html; charset=iso-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 


<t:template>
<jsp:body>
	<div class="clientMain">
		<h4>Welcome <c:out value="${client.clientName}" /></h4>
		<p>Welcome our dear customer, <strong><c:out value="${client.clientName}" /></strong>, this is your main page.</p>
		
		<div id="actionsHelp" class="row">
			<div id="action" class="col-lg-2">
				<ul class="nav nav-pills nav-stacked">
					<li><a href="${pageContext.request.contextPath}/customer/main.html">Main</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/customer/history.html">History</a>
					<li class="active"><span class="badge"><c:out value="${numbookings}" /> bookings</span></li>
				</ul>
			</div>
			<div id="help" class="col-lg-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title">Help!</div>
					</div>
					<div class="panel-body">
						<p>This page shows you, the activities you did.</p>
						<p>You can see them below. click on the <strong class="high">arrow</strong> to see all the information.</p>
					</div>
				</div>
			</div>
		</div>
		
		<c:forEach var="booking" items="${pastBookings}">
			    <div class="booking">
			    	<div class="booking-title">
			    		<h5 class="pull-left"><c:out value="${booking.name}" /><small><c:out value="${booking.date}" /></small></h5>
			    		<a href="#collapseAct<c:out value='${booking.id}' />" class="pull-right" data-toggle="collapse" aria-expanded="false" aria-controls="collapse"><span class="caret"></span></a>
			    		<div style="clear:both;"></div>
			    	</div>
			    	<div class="collapse" id="collapseAct<c:out value='${booking.id}' />">
				    	<div class="booking-body row">
				        	<div class="col-lg-4">
				        		<div class="booking-activity"><c:out value="${booking.name}" /></div>
				        		<div class="booking-id">Identifier: <c:out value="${booking.id}" /></div>
				        	</div>
				        	<div class="col-lg-6">
				          		<div class="booking-date">
				            		<h6>Date: <small><c:out value="${booking.date}" /></small></h6>
				          		</div>
				          		<div class="booking-group">
				            		<h6>Group Size: <small><c:out value="${booking.groupSize}" /></small></h6>
				          		</div>
				          		<div class="booking-place">
				           	 		<h6>Place: <small><c:out value="${booking.place}" /></small></h6>
				          		</div>
				        	</div>
				        	<div class="col-lg-2">
				          		<div class="booking-status">
				          			<c:choose>
				          				<c:when test="${booking.status eq 0}">
						            		<div class="pending">pending</div>
				            			</c:when>
				          				<c:when test="${booking.status eq 1}">
						            		<div class="accepted">accepted</div>
						            		<div class="date"><c:out value="${booking.dateRevision}" /></div>
				            			</c:when>
				            			<c:when test="${booking.status eq 2}">
						            		<div class="declined">declined</div>
						            		<div class="date"><c:out value="${booking.dateRevision}" /></div>
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