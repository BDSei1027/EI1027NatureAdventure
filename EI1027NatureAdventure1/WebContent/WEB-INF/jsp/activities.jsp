<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:template>
<jsp:body>
	<h4>List of the activities</h4>
	<c:forEach var="act" items="${activityList}">
	<c:choose>
		<c:when test="${act.level eq 0}"><c:set var="lvl" value="Begginer" /></c:when>
		<c:when test="${act.level eq 1}"><c:set var="lvl" value="Easy" /></c:when>
		<c:when test="${act.level eq 2}"><c:set var="lvl" value="Medium" /></c:when>
		<c:when test="${act.level eq 3}"><c:set var="lvl" value="High" /></c:when>
	</c:choose>
	<div class="activity">
	<a href="#collapseAct${act.idAct}" data-toggle="collapse" aria-expanded="false" aria-controls="collapse">
		<div class="activity-title">
	    		<c:if test="${lang eq 'EN'}"><c:out value="${act.name}" /></c:if>
	        	<c:if test="${lang eq 'ES'}"><c:out value="${act.nombre}" /></c:if>
	    		<small><c:out value="${lvl}" /></small>
    	</div>
    	</a>
    	<div class="collapse" id="collapseAct${act.idAct}">
	    	<div class="activity-body row">
	        	<div class="col-lg-5">
	        		<div class="activity-name">
					<c:if test="${lang eq 'EN'}"><h4><c:out value="${act.name}" /></h4></c:if>
	        		<c:if test="${lang eq 'ES'}"><h4><c:out value="${act.nombre}" /></h4></c:if>
	        		</div>
	        		<div class="activity-info">
	        			<div class="activity-level">
	        				<h6>Level: <small><c:out value="${lvl}" /></small></h6>
	        			</div>
		          		<div class="activity-schedule">
		            		<h6>Schedule: <small><c:out value="${act.schedule}" /></small></h6>
		          		</div>
		          		<div class="activity-place">
		            		<h6>Place: <small><c:out value="${act.place}" /></small></h6>
		          		</div>
		          		<div class="activity-group">
		          			<h6>Minimum group: <small class="min-g"><c:out value="${act.minimumGroup}" /></small></h6>
		          			<h6>Maximum group: <small class="max-g"><c:out value="${act.maximumGroup}" /></small></h6>
		          		</div>
		          		<div class="activity-price">
		          			<h5><c:out value="${act.price}" /> &euro;<small>/person</small></h5>
		          		</div>
	          		</div>
	    		</div> <!-- col -->
	    		<div class="col-lg-7">
	    			<div class="activity-description">
	    				<h6>Description</h6>
	    				<c:if test="${lang eq 'EN'}"><p><c:out value="${act.description}" /></p></c:if>
	    				<c:if test="${lang eq 'ES'}"><p><c:out value="${act.descripcion}" /></p></c:if>
	    			</div>
	    		</div> <!-- col -->
	    		<button class="btn btn-primary btn-lg btn-block activity-btn" onClick="javascript:location.href = '${pageContext.request.contextPath}/activities/newBooking/${act.idAct}.html';">Book it!</button>
	  		</div> <!-- body -->
  		</div>
   	</div> <!-- acti -->
   	</c:forEach>

</jsp:body>
</t:template>