<?xml version="1.0" encoding="UTF-8" ?>
<%@ tag description="Estructura del pie de pagina"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="src.languages.text" />

<footer>
	<p class="text-muted text-center"><fmt:message key="footer.one" /></p>
   	<p class="text-muted text-center">EI1027 - Disseny i Implementació de Sistemes d'Informació - Universitat Jaume I - UJI</p>
   	<p class="text-muted text-center"><fmt:message key="footer.develop" /></p>
   	<p class="text-muted text-center"><a href="mailto:al259317@uji.es">Catalín Denis</a>, <a href="mailto:al259368@uji.es">David Serrano</a> &amp; <a href="mailto:al259292@uji.es">Borja Blasco</a>.</p>
</footer>