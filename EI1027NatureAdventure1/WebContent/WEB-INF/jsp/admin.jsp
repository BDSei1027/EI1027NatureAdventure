<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
		<c:choose>
		<c:when test="${not empty error and error eq 0 }">
			<div class="alert alert-success alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Success!</strong> Your changes have done correctly.
			</div>
		</c:when>
		<c:when test="${not empty error and error eq 1 }">
			<div class="alert alert-danger alert-dismissible" role="alert">
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			  <strong>Danger!</strong> Check your new password, it has some errors. The password does not change.
			</div>
		</c:when>
		</c:choose>
	  <div class="row">
      <div class="col-lg-6">
        <h3>Welcome <c:out value="${user.name}" /></h3>
  			<h5 class="subtitle">This is your admin page. <br>
          <small class="subtitle" style="color: #95a5a6;">Here you can manage your tasks and take notes.</small>
        </h5>
  		</div>
      <div class="col-lg-6">
        <h6 class="text-right">Today is <strong><c:out value="${dateToday}" /></strong></h6>
      </div>

  	</div>
    <hr>
    <div class="row">
      <div class="col-lg-2" style="width: 22%;">
    	<ul id="menu" class="nav nav-pills nav-stacked">
            <li><h4>Menu</h4></li>
    				<li class="active"><a href="${pageContext.request.contextPath}/admin/">Activty management</a></li>
    				<li class="active"><a href="${pageContext.request.contextPath}/admin/">Instructor management</a>
    				<li class="active"><a href="${pageContext.request.contextPath}/admin/">Booking management</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/admin/">Client management</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/admin/">User management</a></li>
            <li class="active danger"><a href="#changeModal" data-toggle="modal" data-target="#changeModal">Change password</a></li>
    	</ul>
      </div> <!-- col -->
      <div class="col-lg-9">
        <div class="row">
          <div class="stadistics">
            <h6 class="subtitle">Stadistics</h6>
            <dl>
              <dt>Pending bookings</dt><dd><c:out value="${numbookings}" /></dd>
              <dt>Number of clients</dt>
              <dd><c:out value="${numclients}" /></dd>
              <dt>Algo register</dt>
              <dd>15</dd>
            </dl>
          </div>
        </div>

        <h6 class="subtitle">News</h6>
        <p style="padding-left: 10%;"><em class="high">Info read from a file</em></p>
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
						
						<h4 class="pull-left">Change password</h4>
					</div>
				</div>
        		<form:form modelAttribute="doublepassword" role="form" action="${pageContext.request.contextPath}/account/updateAuth.html" method="post">
					<div class="modal-body" id="changeModalBody">
						<div style="font-size: 14px;">The password must be a strong password because is for an admin, remember:</div>
						<ul>
							<li style="font-size: 14px;">At least 8 characters.</li>
							<li style="font-size: 14px;">With numbers, symbols, letters upper-case and lower-case.</li>
							<li style="font-size: 14px;">Avoid patterns and do not enter personal information.</li>
							<li style="font-size: 14px;">Forbidden passwords like <strong>NatureAdventure</strong> or <strong>admin</strong></li>
						</ul>
						<div class="form-group">
							<form:label path="password" for="pass1" class="control-label">Password </form:label>
							<form:input path="password" type="password" id="pass1" class="form-control" placeholder="Password" />
							<form:errors path="password" />
						</div>
						<div class="form-group">
							<form:label path="confirmation" for="pass2" class="control-label">Confirm password </form:label>
							<form:input path="confirmation" type="password" class="form-control" id="pass2" placeholder="Confirm password" />
							<form:errors path="confirmation" />
						</div>
						<div class="form-group">
							<label class="checkbox" for="sure">
								<input class="custom-checkbox" type="checkbox" id="sure" required="required"/> Are you sure?
							</label>
						</div>
					</div>
					<div class="modal-footer" id="changeModalFooter">
	          			<p class="pull-left" style="font-size: 14px;"> <em>Be care and remember the password</em></p>
	          			<div class="pull-right">
	          				<div class="btn-group">
		            			<button type="submit" class="btn btn-danger">Change password</button>
		            			<button type="button" class="btn btn-primary" data-dismiss="modal">Back</button>
	            			</div>
						</div>
						<div style="clear:both;"></div>
					</div>
      			</form:form>
			</div>
		</div>
	</div><!-- modal -->
	</jsp:body>
</t:template>