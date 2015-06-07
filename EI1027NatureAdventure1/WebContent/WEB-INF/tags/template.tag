<%@ tag description="This tag contains the basic structure of the website, the header with the menu and the footer" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>

<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="WEB-INF/language/messages" />

<html lang="${lang}">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NatureAdventure</title>

<!-- Bootstrap -->
<link href="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-table.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/flat-ui.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-datepicker3.min.css" rel="stylesheet" />



<!-- <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.0/bootstrap-table.min.js"></script>
<!-- <script src="${pageContext.request.contextPath}/js/flat-ui.min.js"></script> -->

<script src="${pageContext.request.contextPath}/js/application.js"></script>
<script src="${pageContext.request.contextPath}/js/prettify.min.js"></script>
<script src="${pageContext.request.contextPath}/js/radiocheck.js"></script>



</head>

<body class="container">
	<!-- Idiomas -->
	<t:idiomas />
	<!-- Fin idioma -->
	
	<!-- Cabecera -->
	<!-- Titulo -->
	<div class="page-header">
		<div class="media">
			<div class="media-left">
				<a href="#"><img src="" alt="" width="" height=""></a>
			</div>
		</div>
		<h2>Nature Adventure</h2>
	</div>
	<!-- Fin titulo -->
    
    <!-- NavBar -->
    <c:choose>
    	<c:when test="${user.type eq 0}">
    		<t:navbaradmin />
    	</c:when>
    	<c:when test="${user.type eq 1}">
    		<t:navbarmonitor />
    	</c:when>
    	<c:when test="${user.type eq 2}">
    		<t:navbaruser />
    	</c:when>
    	<c:otherwise>
    		<t:navbarbasic />
    	</c:otherwise>
    </c:choose>
    <!-- Cuerpo -->
    <div class="container-fluid">
        <jsp:doBody />
    </div>
    <!-- Pie de pagina -->
	<t:footer />
	
	<script type="text/javascript">
	$(document).ready(function(){
		$('select[name="inverse-dropdown"], select[name="inverse-dropdown-optgroup"], select[name="inverse-dropdown-disabled"]').select2({dropdownCssClass: 'select-inverse-dropdown'});

        $('select[name="searchfield"]').select2({dropdownCssClass: 'show-select-search'});
        $('select[name="inverse-dropdown-searchfield"]').select2({dropdownCssClass: 'select-inverse-dropdown show-select-search'});
        $(':checkbox').radiocheck();
      });
    </script>
</body>
</html>
