<%@ tag description="Estructura de la barra de navegacion"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src.languages.text" />

  	  	<!-- Login MODAL -->
	<div class="modal fade" id="LoginModal" tabindex='-1' role="dialog">
		<div class="modal-dialog modal-dialog-wide">
			<div class="modal-content">
				<div class="modal-header" id="LoginModalHead">
					<div class="row">
							<button type="button" class="close pull-right" data-dismiss="modal" style="padding-right: 4%; margin-top: 2px;">&times;</button>
							<h4 class="pull-left">Sign in!</h4>
						</div>
				</div>
				<form role="form" action="${pageContext.request.contextPath}/login.html" method="post">
					<div class="modal-body" id="LoginModalBody">
							<div class="form-group row">
								<label for="user" class="control-label col-lg-2">Username </label>
								<div class="col-lg-10">
									<input type="text" name="user" class="form-control" id="user" placeholder="Username" />
								</div>
							</div>
							<div class="form-group row">
								<label for="pass" class="control-label col-lg-2">Password </label>
								<div class="col-lg-10">
									<input type="password" name="password" class="form-control" id="pass" placeholder="Password" />
								</div>
							</div>
							<div class="form-group">
							<label class="checkbox" for="remem">
								<input class="custom-checkbox" type="checkbox" id="remem" name="remem" value="true"/> Remember me:
							</label>
							</div>
					</div>	
					<div class="modal-footer" id="LoginModalFooter">
						<div class="pull-left">
						<p>Not a member? <a href="${pageContext.request.contextPath}/register.html">Sign Up</a></p>
	          			<p>Forgot <a href="#">Password?</a></p>
	          			</div>
	          			<div class="pull-right">
		          			<div class="btn-group" style="margin-top: 5px;">
								<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-off"></span> Sign in</button>
								<button class="btn btn-danger pull-left" data-dismiss="modal"><span class="fui-cross"></span> Cancel</button>
							</div>
	          			</div>
	          			<div style="clear:both;"></div>
					</div>
				</form>
			</div>
		</div>
	</div>

<nav class="navbar navbar-inverse" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Desplegable no login</span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
      <span class="icon-bar"></span>
    </button>
  </div>
  <div class="navbar-collapse collapse">
    <ul class="nav navbar-nav navbar-left">
        <li><a href="${pageContext.request.contextPath}/">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/activities.html">Activities</a></li>
		<li><a href="${pageContext.request.contextPath}/help.html">Help</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#loginModal" data-toggle="modal" data-target="#LoginModal">Sign in</a></li>
      <li class="active"><a href="${pageContext.request.contextPath}/register.html" class="btn-logout">Sign up!  
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span> </a></li>
    </ul>
  </div>
</nav>
