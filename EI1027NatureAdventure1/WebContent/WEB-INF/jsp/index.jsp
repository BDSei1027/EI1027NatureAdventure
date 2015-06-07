<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:template>
<jsp:body>
	<div class="portada">
		<h1 id="titulo">NatureAdventure</h1>
		<h5 class="eslongan">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</h5>
	</div>

	<div class="cuerpo-index">
		<h4>The popular offers</h4>
			<div class="col-lg-6">
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/aa.jpg); background-position: -80px -90px;" data-toggle="collapse" data-target="#offer-text-1" aria-expanded="false" aria-controls="collapse">
						<p>Skydiving <small>High</small></p>
					</button>
					<div class="collapse in" id="offer-text-1">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet scelerisque orci, sed venenatis elit. Ut faucibus lacus at dapibus pharetra. Aliquam erat volutpat metus.
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/bb.jpg);" data-toggle="collapse" data-target="#offer-text-2" aria-expanded="false" aria-controls="collapse">
						<p>Canoeing <small>Easy</small></p>
					</button>
					<div class="collapse" id="offer-text-2">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet scelerisque orci, sed venenatis elit. Ut faucibus lacus at dapibus pharetra. Aliquam erat volutpat metus.
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/ff.jpg); background-position: -80px -100px;" data-toggle="collapse" data-target="#offer-text-3" aria-expanded="false" aria-controls="collapse">
						<p>Safari <small>Easy</small></p>
					</button>
					<div class="collapse in" id="offer-text-3">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet scelerisque orci, sed venenatis elit. Ut faucibus lacus at dapibus pharetra. Aliquam erat volutpat metus.
					</div>
					</div>
				</div>
			</div> <!-- col -->
			
			<div class="col-lg-6">
			
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/dd.jpg); background-position: -200px -120px;" data-toggle="collapse" data-target="#offer-text-4" aria-expanded="false" aria-controls="collapse">
						<p>Treking <small>Beginner</small></p>
					</button>
					<div class="collapse" id="offer-text-4">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet scelerisque orci, sed venenatis elit. Ut faucibus lacus at dapibus pharetra. Aliquam erat volutpat metus.
					</div>
					</div>
				</div>
				
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/cc.jpg);" data-toggle="collapse" data-target="#offer-text-3" aria-expanded="false" aria-controls="collapse">
						<p>Bungee-jumping <small>Medium</small></p>
					</button>
					<div class="collapse in" id="offer-text-3">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet scelerisque orci, sed venenatis elit. Ut faucibus lacus at dapibus pharetra. Aliquam erat volutpat metus.
					</div>
					</div>
				</div>
				<div class="offer">
					<button class="offer-img" style="background-image: url(${pageContext.request.contextPath}/img/ee.jpg);" data-toggle="collapse" data-target="#offer-text-3" aria-expanded="false" aria-controls="collapse">
						<p>Surf <small>Medium</small></p>
					</button>
					<div class="collapse" id="offer-text-3">
					<div class="offer-space-top"></div>
					<div class="offer-text">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus aliquet scelerisque orci, sed venenatis elit. Ut faucibus lacus at dapibus pharetra. Aliquam erat volutpat metus.
					</div>
					</div>
				</div>
			</div> <!-- col -->
	</div>

</jsp:body>
</t:template>