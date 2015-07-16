<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
<jsp:body>
	<div class="crumbs">
		<a href="${pageContext.request.contextPath}/activities.html"><fmt:message key="breadcrumbs.cust.activities" /></a> 
		<span class="fui-arrow-left"></span>
		<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a> 
	</div>
	
	<c:if test="${lang eq 'es'}">
		<c:set var="actName" value="${activity.nombre}" scope="page" />
		<c:set var="description" value="${activity.descripcion}" scope="page" />
		<c:choose>
			<c:when test="${activity.level eq 0}"><c:set var="lvl" value="Básico" /></c:when>
			<c:when test="${activity.level eq 1}"><c:set var="lvl" value="Fácil" /></c:when>
			<c:when test="${activity.level eq 2}"><c:set var="lvl" value="Medio" /></c:when>
			<c:when test="${activity.level eq 3}"><c:set var="lvl" value="Alto" /></c:when>
		</c:choose>
	</c:if>
	<c:if test="${lang eq 'en'}">
		<c:set var="actName" value="${activity.name}" scope="page" />
		<c:set var="description" value="${activity.description}" scope="page" />
		<c:choose>
			<c:when test="${activity.level eq 0}"><c:set var="lvl" value="Beginner" /></c:when>
			<c:when test="${activity.level eq 1}"><c:set var="lvl" value="Easy" /></c:when>
			<c:when test="${activity.level eq 2}"><c:set var="lvl" value="Medium" /></c:when>
			<c:when test="${activity.level eq 3}"><c:set var="lvl" value="High" /></c:when>
		</c:choose>
	</c:if>
	
	<h4><fmt:message key="opinion.activity" /> ${actName}</h4>
	
	
	<div class="row">
      <div class="col-lg-3" id="imagen">
        <div style="height: 300px; background-color: white;"></div>
      </div>
      <div class="col-lg-9">
        <h5>${actName}</h5>
          <div class="row">
            <div class="col-lg-6">
              <div class="row">
                <div class="col-lg-5">
                  <p><strong><fmt:message key="opinion.level" />:</strong></p>
                  <p><strong><fmt:message key="opinion.schedule" />:</strong></p>
                  <p><strong><fmt:message key="opinion.price" />:</strong></p>
                </div>
                <div class="col-lg-7">
                  <p>${lvl}</p>
                  <p>${activity.schedule}</p>
                  <p>${activity.price}&euro;</p>
                </div>
              </div>
            </div>
            <div class="col-lg-6">
              <div class="row">
                <div class="col-lg-5">
                  <p><strong><fmt:message key="opinion.place" />:</strong></p>
                  <p><strong><fmt:message key="opinion.maxgroup" />:</strong></p>
                  <p><strong><fmt:message key="opinion.mingroup" />:</strong></p>
                </div>
                <div class="col-lg-7">
                  <p>${activity.place}</p>
                  <p>${activity.maximumGroup}</p>
                  <p>${activity.minimumGroup}</p>
                </div>
              </div>
            </div>
          </div>
          <p>${description}</p>
          <button class="btn btn-primary btn-lg btn-block activity-btn" onClick="javascript:location.href = '${pageContext.request.contextPath}/activities/createBooking/${act.idAct}.html';"><fmt:message key="activities.btn" />!</button>
        </div>
      </div>
      
      <h6><fmt:message key="opinion.title.opinion" /></h6>
      <c:forEach items="${opinions}" var="op">
      <div class="opinion">
        <div class="opinion-date">${op.date}</div>
        <div class="opinion-score">
        	<c:choose>
        		<c:when test="${op.score eq 0}"><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span></c:when>
        		<c:when test="${op.score eq 1}"><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span></c:when>
        		<c:when test="${op.score eq 2}"><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span></c:when>
        		<c:when test="${op.score eq 3}"><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-disable"></span><span class="fui-star-2 star-disable"></span></c:when>
        		<c:when test="${op.score eq 4}"><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-active"></span><span class="fui-star-2 star-disable"></span></c:when>
        		<c:when test="${op.score eq 5}"><span class="fui-star-2 star-perfect"></span><span class="fui-star-2 star-perfect"></span><span class="fui-star-2 star-perfect"></span><span class="fui-star-2 star-perfect"></span><span class="fui-star-2 star-perfect"></span></c:when>
        	</c:choose>
        <div class="opinion-text">${op.opinion}</div>
        <div class="opinion-author">-- ${op.author} --</div>
      </div>
      </div>
      </c:forEach>
      
      <div class="form-op">
      	<form:form modelAttribute="opinion" action="${pageContext.request.contextPath}/activity/${activity.idAct}/newOpinion.html" method="post" role="form" class="width-50" style="margin-left:25%;">
      		<div class="form-group">
              <form:label path="author" class="control-label"><fmt:message key="opinion.op.author" /></form:label>
              <form:input path="author" type="text" class="form-control"></form:input>
            </div>
            <div class="form-group">
              <form:label path="opinion" class="control-label"><fmt:message key="opinion.op.opinion" /></form:label>
              <form:textarea path="opinion" class="form-control" />
            </div>
            <div class="form-group row">
              <form:label path="score" class="control-label col-lg-3"><fmt:message key="opinion.op.score" /></form:label>
              <div class="col-lg-3">
              <form:select path="score" data-toggle="select" class="form-control select select-primary mrs mbm">
              	<form:option value="0">0</form:option>
              	<form:option value="1">1</form:option>
              	<form:option value="2">2</form:option>
              	<form:option value="3">3</form:option>
              	<form:option value="4">4</form:option>
              	<form:option value="5">5</form:option>
              </form:select>
              </div>
              <form:hidden path="idAct" />
              <form:hidden path="date" />
              <div class="col-lg-6" style="text-align: right;"><button type="submit" class="btn btn-success"><fmt:message key="opinion.op.submit" /></button></div>
            </div>
      	</form:form>
      </div>
</jsp:body>
</t:template>