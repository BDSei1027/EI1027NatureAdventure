<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:template>
<jsp:body>
	<div class="portada">
		<h1 id="titulo">NatureAdventure</h1>
		<h5 class="eslongan">Cuando riesgo y diversión se juntan una nueva aventura comienza</h5>
	</div>

	<div class="cuerpo-index">
		<h4>The popular offers</h4>
			<div class="col-lg-6">
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/adrenalina.jpg); background-position: -80px -90px;" data-toggle="collapse" data-target="#offer-text-1" aria-expanded="false" aria-controls="collapse">
						<p>Adrenalina</p>
					</button>
					<div class="collapse in" id="offer-text-1">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Emociones fuertes, endorfinas, adrenalina y riesgo te harán volver a repetir con nosotros y nuestras épicas aventuras junto a nuestros monitores.
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/relax.jpg);" data-toggle="collapse" data-target="#offer-text-2" aria-expanded="false" aria-controls="collapse">
						<p>Relax</p>
					</button>
					<div class="collapse" id="offer-text-2">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						No todo es riesgo y adrenalina, con nosotros también te puedes relajar y disfrutar de actividades para personas de todos los tipos, con emociones fuertes o sin.
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/aprende.jpg); background-position: -80px -100px;" data-toggle="collapse" data-target="#offer-text-3" aria-expanded="false" aria-controls="collapse">
						<p>Aprende</p>
					</button>
					<div class="collapse in" id="offer-text-3">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Nunca es tarde para aprender, con nuestras actividades y gracias a nuestros monitores aprenderás de la activadad y como vivir una vida más emocionante.
					</div>
					</div>
				</div>
			</div> <!-- col -->
			
			<div class="col-lg-6">
			
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/belleza.jpg); background-position: -200px -120px;" data-toggle="collapse" data-target="#offer-text-4" aria-expanded="false" aria-controls="collapse">
						<p>Belleza</p>
					</button>
					<div class="collapse" id="offer-text-4">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Disfruta de los mejores paisajes, por descubrir y descubiertos, sorprendete, vive la belleza de la naturaleza en estado puro.
					</div>
					</div>
				</div>
				
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/riesgo.jpg);" data-toggle="collapse" data-target="#offer-text-5" aria-expanded="false" aria-controls="collapse">
						<p>Riesgo</p>
					</button>
					<div class="collapse in" id="offer-text-5">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Riesgo <em>controlado</em> a raudales en nuestras aventuras, donde nuestras actividades de riesgo te podrán la piel de gallina.
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/diversion.jpg);" data-toggle="collapse" data-target="#offer-text-6" aria-expanded="false" aria-controls="collapse">
						<p>Diversión</p>
					</button>
					<div class="collapse" id="offer-text-6">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						La diversión es uno de nuestros fuertes y uno de nuestros objetivos para todas nuestras actividades. Diviertete como cuando eras ñiño con las cosas simples.
					</div>
					</div>
				</div>
			</div> <!-- col -->
	</div>

</jsp:body>
</t:template>