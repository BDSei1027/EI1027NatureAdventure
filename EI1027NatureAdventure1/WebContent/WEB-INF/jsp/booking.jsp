<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<t:template>
<jsp:body>
	<c:if test="${lang eq 'es'}">
		<c:set var="actName" value="${activity.nombre}" scope="page" />
		<c:choose>
			<c:when test="${activity.level eq 0}"><c:set var="lvl" value="Básico" /></c:when>
			<c:when test="${activity.level eq 1}"><c:set var="lvl" value="Fácil" /></c:when>
			<c:when test="${activity.level eq 2}"><c:set var="lvl" value="Medio" /></c:when>
			<c:when test="${activity.level eq 3}"><c:set var="lvl" value="Alto" /></c:when>
		</c:choose>
	</c:if>
	<c:if test="${lang eq 'en'}">
		<c:set var="actName" value="${activity.name}" scope="page" />
		<c:choose>
			<c:when test="${activity.level eq 0}"><c:set var="lvl" value="Begginer" /></c:when>
			<c:when test="${activity.level eq 1}"><c:set var="lvl" value="Easy" /></c:when>
			<c:when test="${activity.level eq 2}"><c:set var="lvl" value="Medium" /></c:when>
			<c:when test="${activity.level eq 3}"><c:set var="lvl" value="High" /></c:when>
		</c:choose>
	</c:if>	
	<h4><fmt:message key="booking.title" /></h4>
	<h6 class="subtitle"><fmt:message key="booking.subtitle" /></h6>
	
	<div class="row">
		<div class="col-lg-3">
			<div class="container-fluid help">
				<div class="help-heading"><fmt:message key="booking.panel.help.title" /></div>
				<div class="help-body">
					<fmt:message key="booking.panel.help" />
				</div>
			</div>
		</div> <!-- col -->
		<div class="col-lg-9">
			<div class="panel panel-warning">
				<div class="panel-heading"><h5 class="panel-title"><fmt:message key="booking.panel.act" /></h5></div>
				<div class="panel-body">
					<p><fmt:message key="booking.panel.act.msg" /></p>
					<div class="modalList" style="padding-left: 20px;">
					<dl>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.act" /></dt><dd><c:out value="${actName}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.lvl" /></dt><dd><c:out value="${lvl}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.sche" /></dt><dd style="text-transform: capitalize;"><c:out value="${activity.schedule}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.max" /></dt><dd><c:out value="${activity.maximumGroup}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.min" /></dt><dd><c:out value="${activity.minimumGroup}" /></dd>
						<dt style="color:inherit;"><fmt:message key="booking.panel.act.price" /></dt><dd><c:out value="${activity.price}" /> &euro;</dd>
					</dl>
					</div>
				</div>
			</div> <!-- panel -->
		<form:form modelAttribute="registerEnvelope" action="${pageContext.request.contextPath}/activities/createBooking/${activity.idAct}.html" method="post" role="form">
		<c:if test="${empty user}">
			<div class="user">
				<p><fmt:message key="booking.signup.msg.1" /></p>
				
				<div class="panel panel-info">
					<div class="panel-heading">
						<h5 class="panel-title"><fmt:message key="booking.signup.title" /></h5>
					</div>
					<div class="panel-body"style="background-color: #FFF">
						<div class="row form-group">
							<form:label path="name" for="nam" class="col-lg-3 control-label"><fmt:message key="reg.name" /> </form:label>
							<div class="col-lg-9">				
								<form:input path="name" type="text" class="form-control" id="nam" placeholder="Enter your name" />
								<form:errors path="name" class="text-danger" />
							</div>
						</div>
						<div class="row form-group">
							<form:label path="lastName" for="lname" class="col-lg-3 control-label"><fmt:message key="reg.lastname" /> </form:label>
							<div class="col-lg-9">
								<form:input path="lastName" type="text" class="form-control" id="lname" placeholder="Enter your last name"/>
							<form:errors path="lastName" class="text-danger" />
							</div>
						</div>
						<div class="row form-group">
							<form:label path="email" for="email" class="col-lg-3 control-label">Email: </form:label>
							<div class="col-lg-9">
								<div class="input-group">
								<form:input path="email" type="email" class="form-control" id="email" placeholder="Enter your email"/>
								<div class="input-group-addon"><span class="fui-mail"></span></div>
								</div>
								<form:errors path="email" class="text-danger" />
							</div>
						</div>
						<div class="row form-group">
							<form:label path="id" for="id" class="col-lg-3 control-label"><fmt:message key="reg.idnumber" /> </form:label>
							<div class="col-lg-9">
								<form:input path="id" type="text" class="form-control" id="id" placeholder="Enter your identification number"/>
								<form:errors path="id" class="text-danger" />
							</div>
						</div>
						<p style="font-style: italic; font-size: 13px;"><fmt:message key="booking.signup.msg.2" /></p>
						<div class="optional">
							<div class="row form-group">
								<form:label path="password" for="pass" class="col-lg-3 control-label"><fmt:message key="reg.password" /> </form:label>
								<div class="col-lg-9">
									<form:input path="password" type="password" class="form-control" id="pass" placeholder="Enter your password"/>
									<form:errors path="password" class="text-danger" />
								</div>
							</div>
							<div id="selectlanguage" class="row" style="margin-bottom: 15px;">
								<div class="col-lg-3 labelReg"><fmt:message key="reg.choose" /></div>
								<div class="col-lg-9">
									<form:select path="language" data-toggle="select" class="form-control select select-primary mrs mbm">
										<optgroup>
											<form:option value="EN"><fmt:message key="languages.en" /></form:option>
											<form:option value="ES"><fmt:message key="languages.es" /></form:option>
										</optgroup>
									</form:select>
								</div>
							</div>
							<div class="panel panel-info">
								<div class="panel-heading">
									<h3 class="panel-title"><fmt:message key="reg.cond" /></h3>	
								</div>
								<div class="panel-body" style="max-height: 200px;overflow-y: auto">
									<p id="condandterms">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ut mattis sapien, et efficitur tortor. Sed metus erat, mattis aliquam porttitor consectetur, varius viverra diam. Sed porttitor metus sed lacus tincidunt pulvinar. Etiam molestie neque porta, tincidunt velit ac, dictum nunc. Etiam ut ante eu elit aliquam iaculis. Curabitur placerat pulvinar dui nec lobortis. Fusce volutpat sapien ut nisi dictum porta. Aenean venenatis nisl ut accumsan dapibus. Pellentesque porta nibh eros, et tempor turpis ullamcorper sit amet. Cras elementum pretium est, sit amet commodo ligula congue non. Duis posuere mauris at faucibus condimentum.</p>
									<p id="condandterms">Nulla maximus est a dui consequat pellentesque. Mauris quis porttitor nulla. Proin iaculis lacinia ipsum sed varius. Sed leo velit, pellentesque eget purus eu, ornare pulvinar sapien. Curabitur sit amet massa in tellus pellentesque posuere in vel nunc. Cras maximus posuere tincidunt. Duis non efficitur ex. Proin vitae tellus sed eros pulvinar laoreet sit amet at massa. Donec sed mauris eu magna dictum ultricies. Nunc vitae interdum arcu. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi facilisis lectus et enim rutrum viverra. </p>
									<p id="condandterms">Duis egestas aliquet felis. Mauris consectetur, purus in pharetra elementum, dolor purus varius sapien, sed congue risus nisi sit amet nisi. Ut id risus vel felis pellentesque porta id non neque. Curabitur auctor ac ligula at mollis. Phasellus porttitor pharetra diam, quis suscipit ipsum ornare eget. Sed facilisis nibh sed lacinia rhoncus. Suspendisse eu urna vitae ex ornare venenatis. Duis porta auctor semper. Nam aliquet tortor ac dui faucibus, id hendrerit risus feugiat. Nam ultricies, erat id scelerisque blandit, diam lectus pretium arcu, a porttitor est purus quis metus. Mauris in nisi nec sapien dictum blandit et id dolor. Sed eget vulputate sapien. Vivamus rutrum nisl ut cursus fringilla. Aenean suscipit nibh eget rutrum ornare. </p>
									<p id="condandterms">Quisque a orci enim. Aliquam gravida velit urna, eu congue metus imperdiet sit amet. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla ut vestibulum ligula. Sed purus risus, vehicula at nulla in, convallis pretium ante. Suspendisse tempor libero id sapien elementum vehicula eget ut tellus. Sed ornare velit suscipit luctus tristique. Curabitur malesuada at justo ac sodales. Ut auctor feugiat felis, in vulputate turpis tristique et. Nam consectetur ante eros, et porttitor lectus mollis ut. Aenean facilisis arcu ac facilisis consequat. Nulla aliquam vel lorem a venenatis. Proin tristique ut lorem vitae tincidunt. Donec scelerisque, sapien posuere finibus congue, nibh leo eleifend purus, ac dapibus augue nisl vel est. Nunc lacinia tristique elit sed luctus. Fusce quis malesuada ex.</p>
									<p id="condandterms">Suspendisse sit amet ligula id sapien rutrum luctus in eu ex. Donec vel purus a nisi convallis tempor eu ut leo. Sed ut ipsum euismod, bibendum nibh in, tincidunt odio. Aliquam volutpat dictum nisi a lacinia. Cras hendrerit consequat consectetur. Donec eget dui at velit aliquet interdum. Pellentesque porta massa dolor, blandit rhoncus lectus consectetur eu. In at tristique ex, et consectetur eros. Morbi enim est, condimentum aliquet sapien id, euismod imperdiet nisi. Cras sit amet justo gravida orci commodo cursus. Donec nec consequat dui. Aenean mattis mauris ante, vel ullamcorper lorem eleifend quis. Integer nec risus velit. </p>				
								</div>
							</div>
							<div style="padding-left:40%;">
									<form:label path="tocs" class="checkbox" for="toc">
										<form:checkbox id="toc" path="tocs" data-toggle="checkbox"/> <fmt:message key="reg.tocs" />
										<br><form:errors path="tocs" class="text-danger"></form:errors>
									</form:label>
							</div>
						</div> <!-- opcional -->
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${not empty user}">
				<form:form modelAttribute="client" method="post" role="form">
					<form:hidden path="name" />
					<form:hidden path="lastName" />
					<form:hidden path="email" />
					<form:hidden path="id" />
					<form:hidden path="password" />
					<form:hidden path="language" />
					<form:hidden path="tocs" />
				</form:form>
			</c:if>

			<div class="panel panel-info">
				<div class="panel-heading"><h5 class="panel-title"><fmt:message key="booking.book" /></h5></div>
				<div class="panel-body">
						<h5 class="text-center"><fmt:message key="booking.book.act" /> <c:out value="${actName}" /></h5>
						<div class="form-group row">
							<form:label path="dateActivity"  class="col-lg-3 control-label"><fmt:message key="booking.book.date" /></form:label>
							<div class="col-lg-9">
								<div class="input-group">
									<form:input path="dateActivity" id="date" class="form-control datepicker" type="text" readonly="true" cssStyle="background-color: #FFF; opacity: 1; border-color: #BDC3C7; color: inherit;" />
									<div class="input-group-addon"><span class="fui-calendar"></span></div>
								</div>
								<form:errors path="dateActivity" class="text-danger" />
							</div>
						</div>
						<div class="form-group row">
							<form:label path="groupSize" class="col-lg-3 control-label"><fmt:message key="booking.book.size" /></form:label>
							<div class="col-lg-9">
							<form:input path="groupSize" type="number" class="form-control" id="grSz" oninput="javascript:updatePrice();"/>
							<form:errors path="groupSize" class="text-danger" />
							</div>
						</div>
			
						<div class="form-group row">
							<div class="col-lg-12">
								<form:label path="information" class="control-label"><fmt:message key="booking.book.information" /></form:label>
								<form:textarea path="information" class="form-control" />
								<form:errors path="information" class="text-danger" />
							</div>
						</div>
						<form:hidden path="dateCreation" />
						<form:hidden path="price" />
						<form:hidden path="innerIdBooking" />
						<form:hidden path="idBooking" />
						<form:hidden path="idAct" />
						<form:hidden path="clientId" />
					
					<div class="form-group row">
						<div class="control-label col-lg-2"><fmt:message key="booking.book.price" />: </div>
						<div class="col-lg-10">
							<div class="input-group">
								<div class="form-control" id="prc"></div>
								<div class="input-group-addon">&euro;</div>
							</div>
						</div>
					</div>
					
					<div class="text-center" style="margin-top: 20px;">
						<div class="btn-group">
							<button type="submit" class="btn btn-primary"><fmt:message key="booking.btn.submit" /></button>
							<button type="reset" class="btn btn-danger" onClick="javascript:clearField();"><fmt:message key="booking.btn.clear" /></button>
						</div>
					</div>
				</div>
			</div>
		</form:form>
		</div> <!-- col -->
	</div> <!-- row -->


	

<script type="text/javascript">
var price = ${activity.price}
function updatePrice()
{
    var groupSize = document.getElementById("grSz").value;
    precioTotal = price * groupSize
    document.getElementById("prc").innerHTML = precioTotal.toFixed(2);
}
function clearField()
{
	document.getElementById("prc").innerHTML = '';
}

</script>
<c:if test="${lang eq 'es'}">
	<script>
	    $('.datepicker').datepicker({
	    	format: 'dd/mm/yyyy',
	    	weekStart: 1,
	    	language: 'es'
	    });
function updateDate()
{
	var date = $('.datepicker').getDate();
	document.getElementById("date").innerHTML = date;
}
	</script>
</c:if>
<c:if test="${lang eq 'en'}">
	<script>
	    $('.datepicker').datepicker({
	    	format: 'mm/dd/yyyy',
	    	weekStart: 1,
	    	language: 'en'
	    })
	</script>
</c:if>

</jsp:body>
</t:template>
