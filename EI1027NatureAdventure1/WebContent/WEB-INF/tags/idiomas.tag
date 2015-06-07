<%@ tag description="Idiomas" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="en"/>

<div class="col-xs-5 col-sm-4 col-md-3 col-lg-3 pull-right" style="padding-right: 0px">	
	<div id="languages" class="text-right">
		<div class="container-fluid">
			<div class="col-xs-5"><a href="${pageContext.request.contextPath}/en.html" id="langA"><fmt:message key="languages.en" /></a></div>
			<div class="col-xs-2"> | </div>
			<div class="col-xs-5"><a href="${pageContext.request.contextPath}/es.html" id="langA"><fmt:message key="languages.es" /></a></div>
		</div>
	</div>
</div>