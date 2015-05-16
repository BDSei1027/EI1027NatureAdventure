<?xml version="1.0" encoding="UTF-8" ?>
<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" version="2.0">
	<jsp:directive.page contentType="text/html; charset=UTF-8" 
		pageEncoding="UTF-8" session="false"/>
	<jsp:output doctype-root-element="html"
		doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN"
		doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"
		omit-xml-declaration="true" />
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
<link
    href="${pageContext.request.contextPath}/css/custom.css"
    rel="stylesheet">
<script 
	src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script 
  src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>

<body class="container">
	<!-- Idiomas -->
	<div class="col-xs-5 col-sm-4 col-md-3 col-lg-3 pull-right" style="padding-right: 0px">	
		<div class="languages text-right">
			<div class="container-fluid">
				<div class="col-xs-5"><a href="">English</a></div>
				<div class="col-xs-2"> | </div>
				<div class="col-xs-5"><a href="">Spanish</a></div>
			</div>
		</div>
	</div>
	<!-- Fin idioma -->
	
	<!-- Cabecera -->
	<!-- Titulo -->
	<div class="page-header">
		<div class="media">
			<div class="media-left">
				<a href="#"><img src="" alt="" width="" height=""></a>
			</div>
		</div>
		<h1>Nature Adventure</h1>
	</div>
	<!-- Fin titulo -->
    
    <!-- NavBar -->
    <t:navbarbasic />
    
    
    <!-- Información del usuario 
    <div class="loggeduser"><t:logininfo /></div>
    -->
    <!-- Cuerpo -->
    <div class="container">
        <jsp:doBody />
    </div>
    
    <!-- Pie de pagina -->
	<t:footer />
	
</body>
</html>
</jsp:root>
