<%@ tag description="Estructura de la barra de navegacion"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
        <li><a href="${pageContext.request.contextPath}/"><fmt:message key="nav.index"/></a></li>
        <li><a href="${pageContext.request.contextPath}/activities.html"><fmt:message key="nav.activities"/></a></li>
		<li><a href="${pageContext.request.contextPath}/help.html"><fmt:message key="nav.help"/></a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><p class="navbar-text" id="navtext"><fmt:message key="nav.welcome"/> <strong><c:out value="${user.name}" /></strong></p></li>
      <li><a href="${pageContext.request.contextPath}/admin.html" data-toggle="dropdown" role="button" aria-expanded="false">Admin <span class="fui-triangle-down" style="padding-left: 2px;"></span></a>
         <ul class="dropdown-menu" role="menu">
           <li><a href="${pageContext.request.contextPath}/admin.html"><fmt:message key="nav.admin.main"/></a></li>
           <li class="divider"></li>
           <li><a href="${pageContext.request.contextPath}/admin/activityManagement.html"><fmt:message key="nav.admin.Act"/></a></li>
           <li><a href="${pageContext.request.contextPath}/admin/instructorManagement.html"><fmt:message key="nav.admin.Instr"/></a></li>
           <li><a href="${pageContext.request.contextPath}/admin/bookingManagement.html"><fmt:message key="nav.admin.Book"/></a></li>
           <li><a href="${pageContext.request.contextPath}/admin/clientManagement.html"><fmt:message key="nav.admin.Cli"/></a></li>
           <li><a href="${pageContext.request.contextPath}/admin/userManagement.html"><fmt:message key="nav.admin.User"/></a></li>
         </ul>
      </li>
      <li class="active"><a href="${pageContext.request.contextPath}/logout.html" class="btn-logout"><span class="glyphicon glyphicon-user" aria-hidden="true" style="padding-right: 8px;"></span><fmt:message key="nav.logout"/></a></li>
    </ul>
  </div>
</nav>

<script type="text/javascript">
$('#dropdown').dropdown()
</script>

