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
	<h2>About us</h2>
	<p>We are NatureAdventure.</p>
	<p>What is NatureAdventure? NatureAdventure is a business that manage adventure activities with instructors. 
	The activities can be done when you want. You only need to make the booking and our staff will assign an
	instructor for your activity, and be ready for the adrenaline</p>
	<br>
	<p>This information system is a project for an assignament of the subject <em>EI1027, Disseny i implementació de
	sistemes de la informació</em> and <em>EI 1023, Fonaments de l'enginyeria del programari</em>.</p>
	<br>
	<p>The source of all this project is in Github. Our team is <a href="${github}"><strong>@BDSei1027</strong></a> and the
	repository is <a href="${githubrepo}"><strong>EI1027NatureAdventure</strong></a>.</p>
	<p>The team are composed by:</p>
	<div class="miebro">
		<div class="marco">
			<!-- Foto here -->
			<p><strong>Name:</strong> David Serrano Hernández</p>
			<p><strong>E-mail:</strong> <a href="mailto:">alxxxxxx@uji.es</a></p>
			<p><strong>GitHub:</strong> <a href="${dsh}">@serrano94</a></p>
			
		</div>
	</div> 
	<div class="miebro">
		<div class="marco">
			<!-- Foto here -->
			<p><strong>Name:</strong> Catalín Denís Damían</p>
			<p><strong>E-mail:</strong> <a href="mailto:">alxxxxxx@uji.es</a></p>
			<p><strong>GitHub:</strong> <a href="${cdd}">@cddl</a></p>
			
		</div>
	</div>
	<div class="miebro">
		<div class="marco">
			<!-- Foto here -->
			<p><strong>Name:</strong> Borja Blasco García</p>
			<p><strong>E-mail:</strong> <a href="mailto:">alxxxxxx@uji.es</a></p>
			<p><strong>GitHub:</strong> <a href="${rdp}">@ratadp</a></p>
			
		</div>
	</div>

</jsp:body>
</t:template>