<%@ tag description="Estructura de la barra de navegacion"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <li><a href="${pageContext.request.contextPath}/"><fmt:message key="nav.index"/></a></li>
        <li><a href="${pageContext.request.contextPath}/activities.html"><fmt:message key="nav.activities"/><</a></li>
		<!-- <li><a href="${pageContext.request.contextPath}/help.html"><fmt:message key="nav.help"/></a></li> -->
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><p class="navbar-text" id="navtext"><fmt:message key="nav.welcome"/> <strong><c:out value="${user.name}" /></strong></p></li>
      <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><fmt:message key="nav.actions"/> <span class="caret"></span></a>
         <ul class="dropdown-menu" role="menu">
           <li><a href="${pageContext.request.contextPath}/instructor.html"><fmt:message key="nav.instr.List"/></a></li>
		   <li><a href="${pageContext.request.contextPath}/instructor/history.html"><fmt:message key="nav.instr.PastList"/></a></li>
           <li class="divider"></li>
           <li><a href="${pageContext.request.contextPath}/account.html"><fmt:message key="nav.account"/></a></li>
         </ul>
      <li class="active"><a href="${pageContext.request.contextPath}/logout.html" class="btn-logout"><span class="glyphicon glyphicon-user" aria-hidden="true" style="padding-right: 8px;"></span><fmt:message key="nav.logout"/></a></li>
    </ul>
  </div>
</nav>