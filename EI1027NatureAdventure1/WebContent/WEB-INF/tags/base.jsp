<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ tag description="This tag contains the basic structure of the website, the header with the menu and the footer" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="false"%>
<!DOCTYPE html>
<html lang="en">
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
<body>
	<!-- Cabecera de la pagina -->
    <header class="container page-header">
        <h1>NatureAdventure</h1>
    </header>
    
    <!-- Menu de navegación 
    <t:navegacio /> -->
    
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