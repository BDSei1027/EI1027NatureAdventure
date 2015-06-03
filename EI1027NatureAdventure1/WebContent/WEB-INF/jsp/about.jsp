<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<c:set var="github" scope="page" value="https://github.com/BDSei1027"/>
<c:set var="githubrepo" scope="page" value="https://github.com/BDSei1027/EI1027NatureAdventure"/>
<c:set var="rdp" scope="page" value="https://github.com/RataDP" />
<c:set var="cdd" scope="page" value="https://github.com/CDDL" />
<c:set var="dsh" scope="page" value="https://github.com/serrano94" />


<t:template>
<jsp:body>
    <h4>About us</h4>
    <div style="padding-left: 5%;">
		<p>We are NatureAdventure.</p>
		<p>What is NatureAdventure? NatureAdventure is a business that manage adventure activities with instructors.
		The activities can be done when you want. You only need to make the booking and our staff will assign an
		instructor for your activity, and be ready for the adrenaline</p>
		<p>This information system is a project for an assignament of the subject <em>EI1027, Disseny i implementaci� de
		sistemes de la informaci�</em> and <em>EI 1023, Fonaments de l'enginyeria del programari</em>.</p>
		<p>The source of all this project is in Github. Our team is <a class="high" href="${github}"><strong>@BDSei1027</strong></a> and the
		repository is <a class="high" href="${githubrepo}"><strong>EI1027NatureAdventure</strong></a>.</p>
		<h3>The team is composed by</h3>
		<div class="team">
			<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingOne">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
								David Serrano Hern�ndez
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
						<div class="panel-body">
							<div class="miembro">
								<div class="media">
								<div class="media-left">
								<img src="cuiner.jpg" class="media-object" alt="64x64" style="width:120px; height: 120px;">
								</div>
									<div class="media-body">
										<h5 class="media-heading">David Serrano Hern�ndez</h5>
										<div class="miembro-text">
											<p><strong>Email:</strong> <a href="mailto:">alxxxxxx@uji.es</a></p>
											<p><strong>GitHub:</strong> <a href="${dsh}">@serrano94</a></p>
											<p><Strong>Information:</strong></p>
										</div>
									</div>
								</div>
							</div> <!-- miembro -->
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingTwo">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
								Catal�n Den�s Dam�an
							</a>
						</h4>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
						<div class="panel-body">
							<div class="miembro">
								<div class="media">
								<div class="media-left">
								<img src="cuiner.jpg" class="media-object" alt="64x64" style="width:120px; height: 120px;">
								</div>
									<div class="media-body">
										<h5 class="media-heading">Catal�n Den�s Dam�an</h5>
										<div class="miembro-text">
											<p><strong>E-mail:</strong> <a href="mailto:">alxxxxxx@uji.es</a></p>
											<p><strong>GitHub:</strong> <a href="${cdd}">@cddl</a></p>
											<p><Strong>Information:</strong></p>
										</div>
									</div>
								</div>
							</div> <!-- miembro -->
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading" role="tab" id="headingThree">
						<h4 class="panel-title">
							<a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
								Borja Blasco Garc�a
							</a>
						</h4>
					</div>
					<div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
						<div class="panel-body">
							<div class="miembro">
								<div class="media">
								<div class="media-left">
								<img src="cuiner.jpg" class="media-object" alt="64x64" style="width:120px; height: 120px;">
								</div>
									<div class="media-body">
										<h5 class="media-heading">Borja Blasco Garc�a</h5>
										<div class="miembro-text">
											<p><strong>E-mail:</strong> <a href="mailto:">alxxxxxx@uji.es</a></p>
											<p><strong>GitHub:</strong> <a href="${rdp}">@ratadp</a></p>
											<p><Strong>Information:</strong></p>
										</div>
									</div>
								</div>
							</div> <!-- miembro -->
						</div>
					</div>
				</div>
			</div> <!-- group panels -->
		</div> <!-- team -->
	</div> <!-- padding -->

</jsp:body>
</t:template>