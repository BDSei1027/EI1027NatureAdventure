<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>

	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/customer.html"><fmt:message key="breadcrumbs.cust.main" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a> 
	</div>

	<div class="clientMain">
		<h4><fmt:message key="client.activebookings.welcome" /> <c:out value="${name}" /></h4>
		<p class="subtitle"><fmt:message key="client.activebookings.info1" /> <strong><c:out value="${name}" /></strong><fmt:message key="client.activebookings.info2" /></p>
		
		<div id="actionsHelp" class="row">
			<div id="action" class="col-lg-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="${pageContext.request.contextPath}/customer/main.html"><fmt:message key="client.activebookings.menu.main" /></a></li>
					<li><a href="${pageContext.request.contextPath}/customer/history.html"><fmt:message key="client.activebookings.menu.history" /></a>
					<li class="activeBooking"><a><fmt:message key="client.activebookings.menu.bookings" /> <span class="mybadge"><c:out value="${numbookings}" /></span></a></li>
				</ul>
			</div>
			<div id="help" class="col-lg-10">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div class="panel-title"><fmt:message key="client.activebookings.helptitle" /></div>
					</div>
					<div class="panel-body">
						<p><fmt:message key="client.activebookings.helpcontent1" /></p>
						<p><fmt:message key="client.activebookings.helpcontent2" /> <span class="fui-triangle-down"></span> <fmt:message key="client.activebookings.helpcontent3" /></p>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${empty bookings}">
			<div class="nobooking" style="margin-top: 15px;">
				<h5 class="text-center text-muted"><fmt:message key="client.activebookings.nobooking1" /></h5>
				<h6 class="text-center text-muted"><fmt:message key="client.activebookings.nobooking2" /> <a href="${pageContext.request.contextPath}/activities.html" style="font-weight: bold"><fmt:message key="client.activebookings.nobooking3" /></a></h6>
			</div>
		</c:if>
		<c:forEach var="booking" items="${bookings}">
			<c:if test="${lang eq 'es'}">
				<c:choose>
					<c:when test="${booking.level eq 0}"><c:set var="lvl" value="Básico" /></c:when>
					<c:when test="${booking.level eq 1}"><c:set var="lvl" value="Fácil" /></c:when>
					<c:when test="${booking.level eq 2}"><c:set var="lvl" value="Medio" /></c:when>
					<c:when test="${booking.level eq 3}"><c:set var="lvl" value="Alto" /></c:when>
				</c:choose>
			</c:if>
			<c:if test="${lang eq 'en'}">
				<c:choose>
					<c:when test="${booking.level eq 0}"><c:set var="lvl" value="Begginer" /></c:when>
					<c:when test="${booking.level eq 1}"><c:set var="lvl" value="Easy" /></c:when>
					<c:when test="${booking.level eq 2}"><c:set var="lvl" value="Medium" /></c:when>
					<c:when test="${booking.level eq 3}"><c:set var="lvl" value="High" /></c:when>
				</c:choose>
			</c:if>
			    <div class="booking">
			    	<a href="#collapseAct${booking.idBooking}" data-toggle="collapse" aria-expanded="false" aria-controls="collapse">
			    	<div class="booking-title">
			    		<h5 class="pull-left"><c:out value="${booking.nameActivity}" /><small><c:out value="${booking.dateActivity}" /></small></h5>
			    		<div class="pull-right">
			    			<c:choose>
			    				<c:when test="${booking.status eq 0}"><h6 class="title-pending"><fmt:message key="client.activebookings.pending" /> <span class="fui-triangle-down" ></span></h6></c:when>
			    				<c:when test="${booking.status eq 1}"><h6 class="title-accepted"><fmt:message key="client.activebookings.accepted" /> <span class="fui-triangle-down" ></span></h6></c:when>
			    				<c:when test="${booking.status eq 2}"><h6 class="title-declined"><fmt:message key="client.activebookings.declined" /> <span class="fui-triangle-down" ></span></h6></c:when>
			    			</c:choose>
			    			
			    			
			    		</div>
			    		<div style="clear:both;"></div>
			    	</div>
			    	</a>
			    	<div class="collapse" id="collapseAct${booking.idBooking}">
				    	<div class="booking-body row">
				        	<div class="col-lg-5">
				        		<div class="booking-activity"><h4 style="margin-bottom: 0px;"><c:out value="${booking.nameActivity}" /> <small><c:out value="${lvl}" /></small></h4></div>
				        		<div class="booking-id"><fmt:message key="client.activebookings.form.id" /> <c:out value="${booking.idBooking}" /></div>
				        	</div>
				        	<div class="col-lg-3">
				          		<div class="booking-date">
				            		<h6><fmt:message key="client.activebookings.form.date" /> <small><c:out value="${booking.dateActivity}" /></small></h6>
				          		</div>
				          		<div class="booking-group">
				            		<h6><fmt:message key="client.activebookings.form.group" /> <small><c:out value="${booking.groupSize}" /></small></h6>
				          		</div>
				          		<div class="booking-place">
				           	 		<h6><fmt:message key="client.activebookings.form.place" /> <small><c:out value="${booking.place}" /></small></h6>
				          		</div>
				          		<div class="booking-place">
				           	 		<h6><fmt:message key="client.activebookings.form.price" /> <small><c:out value="${booking.price}" /> &euro;</small></h6>
				          		</div>
				        	</div>
				        	<div class="col-lg-4">
				          		<div class="booking-status">
				          			<c:choose>
				          				<c:when test="${booking.status eq 0}">
						            		<div class="pending"><fmt:message key="client.activebookings.pending" /></div>
				            			</c:when>
				          				<c:when test="${booking.status eq 1}">
						            		<div class="accepted"><fmt:message key="client.activebookings.accepted" /></div>
						            		<div class="date"><fmt:message key="client.activebookings.form.daterev" /> <c:out value="${booking.dateRevision}" /></div>
				            			</c:when>
				            			<c:when test="${booking.status eq 2}">
						            		<div class="declined"><fmt:message key="client.activebookings.declined" /></div>
						            		<div class="date"><fmt:message key="client.activebookings.form.daterev" /> <c:out value="${booking.dateRevision}" /></div>
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