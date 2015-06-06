<%@ tag description="Estructura de la barra de navegacion"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src.languages.text" />

<nav class="navbar navbar-inverse" role="navigation">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
      <span class="sr-only">Desplegable administrador</span>
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
      <li><a href="${pageContext.request.contextPath}/admin.html" data-toggle="dropdown" role="button" aria-expanded="false">Admin</a> <span class="fui-triangle-down"></span>
         <ul class="dropdown-menu" role="menu">
           <li><a href="${pageContext.request.contextPath}/account.html">Main</a></li>
           <li class="divider"></li>
           <li><a href="${pageContext.request.contextPath}/admin/activityManagement.html">Activity management</a></li>
           <li><a href="${pageContext.request.contextPath}/admin/instructorManagement.html">Instructor management</a></li>
           <li><a href="${pageContext.request.contextPath}/admin/bookingManagement.html">Booking management</a></li>
           <li><a href="${pageContext.request.contextPath}/admin/clientManagement.html">Client management</a></li>
           <li><a href="${pageContext.request.contextPath}/admin/userManagement.html">Users management</a></li>
         </ul>
      </li>
      <li class="active"><a href="${pageContext.request.contextPath}/logout.html" class="btn-logout"><span class="glyphicon glyphicon-user" aria-hidden="true" style="padding-right: 8px;"></span>Log out</a></li>
    </ul>
  </div>
</nav>

<script type="text/javascript">
$('#dropdown').dropdown()
</script>

