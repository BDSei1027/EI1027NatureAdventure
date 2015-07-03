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
        <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis accumsan nisl. Proin dui ipsum, facilisis maximus ex vitae, dapibus venenatis nisl. Ut finibus vitae arcu quis vestibulum. Cras finibus enim id magna dictum, quis ornare odio suscipit. Curabitur porttitor dui id tempor malesuada. Nullam sit amet gravida neque, nec placerat elit. Praesent auctor tempus magna, eu ornare nulla ornare non. Ut molestie volutpat condimentum. Nulla tristique risus sed feugiat scelerisque. Sed varius pulvinar lorem, nec tempor ipsum dapibus sit amet. In eget tincidunt mi, sit amet aliquam tortor. Fusce molestie eget est vel lobortis. Donec dui purus, sagittis hendrerit pulvinar mattis, rhoncus a sapien. In elementum, mi et malesuada pretium, massa arcu consectetur massa, et imperdiet erat ligula nec diam. Curabitur et purus nec magna luctus posuere. </p>
<p> Mauris sollicitudin tempor sem, eu tristique quam ultricies in. Nullam placerat ultrices sem eu aliquam. Quisque turpis enim, pretium et sodales et, consectetur et elit. Sed tincidunt lacus non ligula pharetra fringilla. Ut eget ante quis est rhoncus convallis nec id mauris. Suspendisse accumsan lorem et gravida dignissim. Donec ut nibh leo. Vivamus pulvinar congue ligula. Donec auctor vel ex id vehicula. Nulla pellentesque molestie velit in auctor. Etiam varius erat ante, id ornare tellus fermentum eu. Etiam egestas velit ut lacinia lacinia. </p>
<p> Integer sollicitudin dolor lacus, vel finibus tellus faucibus ut. Donec elementum eleifend tempor. Donec condimentum tempus risus placerat tincidunt. Proin non velit viverra, maximus felis et, porttitor massa. Nunc non sapien sem. Proin ultrices eu ex vitae venenatis. Vestibulum at congue nisi. In elementum est ut ipsum commodo porta. Etiam lacinia, nisl vitae ultricies cursus, ex ligula dignissim dolor, id placerat dolor ipsum vel felis. </p>
<p> Praesent quam dolor, posuere efficitur iaculis vel, varius nec nisi. Phasellus id purus nunc. Donec fringilla augue vel odio facilisis malesuada. Praesent pellentesque pharetra semper. Duis pharetra velit sed ligula imperdiet porta ut sed mauris. Sed sagittis nec est quis ullamcorper. Curabitur sit amet maximus tortor, et blandit nulla. Vestibulum nunc leo, dapibus eget porta eget, luctus sed ex. Aliquam ut dolor sem. Aenean vulputate dapibus tempus. Praesent sollicitudin id ligula eu suscipit. In dignissim tortor arcu, sed imperdiet tortor sagittis vel. </p>
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
	</jsp:body>
</t:template>