<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>



<t:template>
<jsp:body>
	<div class="portada">
		<h1 id="titulo">NatureAdventure</h1>
		<h5 class="eslongan"><fmt:message key="index.eslogan" /></h5>
	</div>

	<div class="cuerpo-index">
		<h4><fmt:message key="index.title" /></h4>
			<div class="col-lg-6">
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/adrenalina.jpg); background-position: -80px -90px;" data-toggle="collapse" data-target="#offer-text-1" aria-expanded="false" aria-controls="collapse">
						<p><fmt:message key="index.content05.title" /></p>
					</button>
					<div class="collapse in" id="offer-text-1">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						<fmt:message key="index.content05.text" />
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/relax.jpg);" data-toggle="collapse" data-target="#offer-text-2" aria-expanded="false" aria-controls="collapse">
						<p><fmt:message key="index.content06.title" /></p>
					</button>
					<div class="collapse" id="offer-text-2">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						<fmt:message key="index.content06.text" />
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/aprende.jpg); background-position: -80px -100px;" data-toggle="collapse" data-target="#offer-text-3" aria-expanded="false" aria-controls="collapse">
						<p><fmt:message key="index.content01.title" /></p>
					</button>
					<div class="collapse in" id="offer-text-3">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						<fmt:message key="index.content01.text" />
					</div>
					</div>
				</div>
			</div> <!-- col -->
			
			<div class="col-lg-6">
			
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/belleza.jpg); background-position: -200px -120px;" data-toggle="collapse" data-target="#offer-text-4" aria-expanded="false" aria-controls="collapse">
						<p><fmt:message key="index.content02.title" /></p>
					</button>
					<div class="collapse" id="offer-text-4">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						<fmt:message key="index.content02.text" />
					</div>
					</div>
				</div>
				
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/riesgo.jpg);" data-toggle="collapse" data-target="#offer-text-5" aria-expanded="false" aria-controls="collapse">
						<p><fmt:message key="index.content03.title" /></p>
					</button>
					<div class="collapse in" id="offer-text-5">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						<fmt:message key="index.content03.text" />
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/diversion.jpg);" data-toggle="collapse" data-target="#offer-text-6" aria-expanded="false" aria-controls="collapse">
						<p><fmt:message key="index.content04.title" /></p>
					</button>
					<div class="collapse" id="offer-text-6">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						<fmt:message key="index.content04.text" />
					</div>
					</div>
				</div>
			</div> <!-- col -->
	</div>

</jsp:body>
</t:template>