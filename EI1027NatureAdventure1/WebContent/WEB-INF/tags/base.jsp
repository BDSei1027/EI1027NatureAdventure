<%@ tag description="This tag contains the basic structure of the website, the header with the menu and the footer" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ attribute name="title" required="false"%>

<!DOCTYPE html>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src.languages.text" />

<html lang="${language}">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${title}</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.css"
    rel="stylesheet">
<link
    href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css"
    rel="stylesheet">
</head>

<body class="container">

	<!-- Selector de idioma -->
	<div class="selectorlgn"> 
		<a href=""><fmt:message key="index.languages.en" /></a> |
		<a href=""><fmt:message key="index.languages.es" /></a>
	</div>
	
	<div class="row">
		<div class="col-lg-4">
		<!-- Cabecera de la pagina -->
		    <header class="container page-header">
		        <h1>NatureAdventure</h1>
		    </header>
	    </div>
		    <div class="col-lg-8">
		    <!-- Menu de navegación 
		    <t:navegacio /> -->
    	</div>
    </div>
    
    <!-- Información del usuario 
    <div class="loggeduser"><t:logininfo /></div>
    -->
    <!-- Cuerpo -->
    <div class="container">
        <jsp:doBody />
    </div>
    
    <!-- Pie de pagina -->
    <footer>
    <hr>
    <p class="text-muted text-center">
    Website designed for NatureAdventure.
    EI1027 - Disseny i Implementació de Sistemes d'Informació - Universitat Jaume I - UJI
    WebApp developed by <a href="mailto:al259317@uji.es">Catalín Denis</a>, <a href="mailto:al259368@uji.es">David Serrano</a> &amp; <a href="mailto:al259292@uji.es">Borja Blasco</a>.
    </p>
    </footer>
</body>
</html>
