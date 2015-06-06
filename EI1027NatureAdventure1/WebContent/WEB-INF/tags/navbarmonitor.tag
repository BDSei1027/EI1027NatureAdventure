<%@ tag description="Estructura de la barra de navegacion"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src.languages.text" />

<nav class="navbar navbar-inverse" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Desplegable monitor</span>
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
      <li><p class="navbar-text" id="navtext">Welcome <strong><c:out value="${user.name}" /></strong></p></li>
      <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Actions <span class="caret"></span></a>
         <ul class="dropdown-menu" role="menu">
           <li><a href="${pageContext.request.contextPath}/instructor.html">Assigned Bookings</a></li>
		   <li><a href="${pageContext.request.contextPath}/instructor/history.html">History Bookings</a></li>
           <li class="divider"></li>
           <li><a href="${pageContext.request.contextPath}/account.html">Account</a></li>
         </ul>
      <li class="active"><a href="${pageContext.request.contextPath}/logout.html" class="btn-logout"><span class="glyphicon glyphicon-user" aria-hidden="true" style="padding-right: 8px;"></span>Log out</a></li>
    </ul>
  </div>
</nav>