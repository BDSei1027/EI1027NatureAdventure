<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:template>
	<jsp:body>
		<c:choose>
		<c:when test="${not empty error and error eq 0 }">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.adminpage.alert.success" />
			</div>
		</c:when>
		<c:when test="${not empty error and error eq 2 }">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <fmt:message key="admin.adminpage.alert.danger" />
			</div>
		</c:when>
		</c:choose>
		
		<div class="crumbs">
			<a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="breadcrumbs.admin.main" /></a> 
			<span class="fui-arrow-left"></span>
			<a href="${pageContext.request.contextPath}/"><span class="fui-home"></span></a> 
		</div>
	  <div class="row">
      <div class="col-lg-6">
        <h3><fmt:message key="admin.adminpage.welcome" /> <c:out value="${user.name}" /></h3>
  			<h5 class="subtitle"><fmt:message key="admin.adminpage.welcome2" /> <br>
          <small class="subtitle" style="color: #95a5a6;"><fmt:message key="admin.adminpage.welcome3" /></small>
        </h5>
  		</div>
      <div class="col-lg-6">
        <h6 class="text-right"><fmt:message key="admin.adminpage.today" /> <strong><c:out value="${dateToday}" /></strong></h6>
      </div>

  	</div>
    <hr>
    <div class="row">
      <div class="col-lg-2" style="width: 22%;">
    	<ul id="menu" class="nav nav-pills nav-stacked">
            <li><h4>Menu</h4></li>
            <li><a href="${pageContext.request.contextPath}/admin/activityManagement.html"><fmt:message key="admin.adminpage.menu.activitymanage" /></a></li>
      		<li><a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="admin.adminpage.menu.instructormanage" /></a>
      		<li><a href="${pageContext.request.contextPath}/admin/bookingManagement.html"><fmt:message key="admin.adminpage.menu.bookingmanage" /></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/clientManagement.html"><fmt:message key="admin.adminpage.menu.clientmanage" /></a></li>
            <li><a href="${pageContext.request.contextPath}/admin/userManagement.html"><fmt:message key="admin.adminpage.menu.usermanage" /></a></li>
            <li><a href="#newNoteModal" data-toggle="modal" data-target="#newNoteModal"><fmt:message key="admin.adminpage.menu.newnote" /></a></li>
            <li class="danger"><a href="#changeModal" data-toggle="modal" data-target="#changeModal"><fmt:message key="admin.adminpage.menu.changepass" /></a></li>
    	</ul>
      </div> <!-- col -->
      <div class="col-lg-9">
        <div class="row">
          <div class="stadistics">
            <h6 class="subtitle"><fmt:message key="admin.adminpage.menu.stadistics.title" /></h6>
            <dl>
              <dt><fmt:message key="admin.adminpage.menu.stadistics.pendingbookins" /></dt><dd><c:out value="${numbookings}" /></dd>
              <dt><fmt:message key="admin.adminpage.menu.stadistics.numberclient" /></dt>
              <dd><c:out value="${numclients}" /></dd>
            </dl>
          </div>
        </div>

        <h6 class="subtitle"><fmt:message key="admin.adminpage.menu.news.title" /></h6>
        <!-- Muestra de las notas -->
        <c:forEach items="${notas}" var="nota">
          <div class="note">
            <div class="note-header"><c:out value="${nota.title}" /> <a class="pull-right" href="${pageContext.request.contextPath}/admin/deleteNote/${nota.id}.html">&times;</a> </div>
            <div class="note-body">
              <c:out value="${nota.note}" />
            </div>
            <div class="note-footer">
              <c:out value="${nota.dateCreation}" />
            </div>
          </div> <!-- class note -->
        </c:forEach>
      </div>
    </div> <!-- row -->

    <div class="modal fade" id="changeModal" tabindex='-1' role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="changeModalHead">
					<div class="row">
						<button type="button" class="close pull-right" data-dismiss="modal" style="padding-right: 4%; margin-top: 2px;">&times;</button>
						
						<h4 class="pull-left"><fmt:message key="admin.adminpage.modal.title" /></h4>
					</div>
				</div>
        		<form:form modelAttribute="doublepassword" role="form" action="${pageContext.request.contextPath}/admin/authAdmin.html" method="post">
					<div class="modal-body" id="changeModalBody">
						<div style="font-size: 14px;"><fmt:message key="admin.adminpage.modal.msg" /></div>
						<ul>
							<li style="font-size: 14px;"><fmt:message key="admin.adminpage.modal.list.1" /></li>
							<li style="font-size: 14px;"><fmt:message key="admin.adminpage.modal.list.2" /></li>
							<li style="font-size: 14px;"><fmt:message key="admin.adminpage.modal.list.3" /></li>
							<li style="font-size: 14px;"><fmt:message key="admin.adminpage.modal.list.4" /></li>
						</ul>
						<div class="form-group">
							<form:label path="password" for="pass1" class="control-label"><fmt:message key="admin.adminpage.modal.pass" /> </form:label>
							<form:input path="password" type="password" id="pass1" class="form-control" placeholder="Password" />
							<form:errors path="password" />
						</div>
						<div class="form-group">
							<form:label path="confirmation" for="pass2" class="control-label"><fmt:message key="admin.adminpage.modal.confirm" /> </form:label>
							<form:input path="confirmation" type="password" class="form-control" id="pass2" placeholder="Confirm password" />
							<form:errors path="confirmation" />
						</div>
						<div class="form-group">
							<label class="checkbox" for="sure">
								<input class="custom-checkbox" type="checkbox" id="sure" required="required"/> <fmt:message key="admin.adminpage.modal.sure" />
							</label>
						</div>
					</div>
					<div class="modal-footer" id="changeModalFooter">
	          			<p class="pull-left" style="font-size: 14px;"> <em><fmt:message key="admin.adminpage.modal.careful" /></em></p>
	          			<div class="pull-right">
	          				<div class="btn-group">
		            			<button type="submit" class="btn btn-danger"><fmt:message key="admin.adminpage.modal.btn.1" /></button>
		            			<button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="admin.adminpage.modal.btn.2" /></button>
	            			</div>
						</div>
						<div style="clear:both;"></div>
					</div>
      			</form:form>
			</div> <!-- modal content -->
		</div>
	</div><!-- modal -->
	    <div class="modal fade" id="newNoteModal" tabindex='-1' role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" id="newModalHead">
					<div class="row">
						<button type="button" class="close pull-right" data-dismiss="modal" style="padding-right: 4%; margin-top: 2px;">&times;</button>
						
						<h4 class="pull-left"><fmt:message key="admin.adminpage.modal.note.title" /></h4>
					</div>
				</div>
        		<form:form modelAttribute="note" role="form" action="${pageContext.request.contextPath}/admin/newNote.html" method="post">
					<div class="modal-body" id="changeModalBody">
						<div style="font-size: 14px;"><fmt:message key="admin.adminpage.modal.note.msg" /></div>
						<form:hidden path="id" />
						<form:hidden path="dateCreation" />
						<div class="form-group">
							<form:label path="title" for="title" class="control-label"><fmt:message key="admin.adminpage.modal.note.notetitle" /> </form:label>
							<form:input path="title" type="text" id="title" class="form-control" placeholder="Title" />
							<form:errors path="title" />
						</div>
						<div class="form-group">
							<form:label path="note" for="msg" class="control-label"><fmt:message key="admin.adminpage.modal.note.notemsg" /> </form:label>
							<form:input path="note" type="text" class="form-control" id="msg" placeholder="Write your note..." />
							<form:errors path="note" />
						</div>
					</div>
					<div class="modal-footer" id="newModalFooter">
	          			<div class="pull-right">
	          				<div class="btn-group">
		            			<button type="submit" class="btn btn-primary"><fmt:message key="admin.adminpage.modal.btn.3" /></button>
		            			<button type="button" class="btn btn-primary" data-dismiss="modal"><fmt:message key="admin.adminpage.modal.btn.2" /></button>
	            			</div>
						</div>
						<div style="clear:both;"></div>
					</div>
      			</form:form>
			</div> <!-- modal content -->
		</div>
	</div><!-- modal -->
	</jsp:body>
</t:template>