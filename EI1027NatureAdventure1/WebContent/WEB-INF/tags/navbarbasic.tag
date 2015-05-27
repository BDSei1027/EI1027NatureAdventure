<%@ tag description="Estructura de la barra de navegacion"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src.languages.text" />

  	  	<!-- Login MODAL -->
	<div class="modal fade" id="LoginModal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header modal-header-success" id="LoginModalHead">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4><span class="glyphicon glyphicon-lock"></span> Login</h4>
				</div>
				<div class="modal-body" id="LoginModalBody">
					<form role="form" action="${pageContext.request.contextPath}/login.html" method="post" modelAttribute="user">
						<div class="form-group">
							<label for="user" class="control-label"><span class="glyphicon glyphicon-user"></span> Username </label>
							<input type="text" class="form-control" id="user" placeholder="Username" />
						</div>
						<div class="form-group">
							<label for="pass" class="control-label">Password: </label>
							<input type="password" class="form-control" id="pass" placeholder="Password" />
						</div>
						<div class="checkbox">
							<label for="remem">
								<input type="checkbox" id="remem" /> Remember me:
							</label>
						</div>
						<button type="submit" class="btn btn-success" id="LoginButton"><span class="glyphicon glyphicon-off"></span> Sign in</button>
					</form>
				</div>	
				<div class="modal-footer" id="LoginModalFooter">
					<button class="btn btn-danger pull-left"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
					<p>Not a member? <a href="${pageContext.request.contextPath}/register.html">Sign Up</a></p>
          			<p>Forgot <a href="#">Password?</a></p>
				</div>
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
        <li><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/activities">Activities</a></li>
				<li><a href="${pageContext.request.contextPath}/help">Help</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="#loginModal" data-toggle="modal" data-target="#LoginModal">Sign in</a></li>
      <li class="active"><a href="${pageContext.request.contextPath}/register.html">Sign up!  
					<span class="glyphicon glyphicon-user" aria-hidden="true"></span> </a></li>
    </ul>
  </div>
</nav>
