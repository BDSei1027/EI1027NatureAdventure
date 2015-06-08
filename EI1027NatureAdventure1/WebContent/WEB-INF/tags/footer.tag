<%@ tag description="This tag contains the basic structure of the website, the header with the menu and the footer" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<footer>
    <div class="container-fluid">
	    <div class="row">
	      <div class="col-sm-2 col-xs-4 footer-col-1">
	        <p><a href="${pageContext.request.contextPath}/about.html" class="text-muted">About us</a></p>
	        <p class="text-muted">Contact with us</p>
	      </div>
	      <div class=" col-sm-10 col-xs-8 col-2">
	  			<p style="font-size: 20px;" class="text-muted"><fmt:message key="footer.one" /> <strong>NatureAdventure</strong></p>
	      	<p class="text-muted">EI1027 - Disseny i Implementació de Sistemes d'Informació<br>
	        EI1023 - Fonaments de l'enginyeria del programari.</p>
	        <p style="padding-right: 5%; font-size: 20px;"><a href="http://ujiapps.uji.es/">Universitat Jaume I</a></p>
	      </div>
	    </div>
    </div>
</footer>