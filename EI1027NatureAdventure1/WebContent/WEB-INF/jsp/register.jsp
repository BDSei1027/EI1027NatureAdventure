<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<t:template>
	<jsp:body>
	<h4><fmt:message key="reg.signup" /></h4>
	<p><fmt:message key="reg.info1" /></p>
	<p><fmt:message key="reg.info2" /></p>
	<ul>
		<li><fmt:message key="reg.info3" /></li>
		<li><fmt:message key="reg.info4" /></li>
		<li><fmt:message key="reg.info5" /></li>
	</ul>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title"><fmt:message key="reg.form" /></h3>
		</div>
		<div class="panel-body">
			<form:form method="post" modelAttribute="register" action="${pageContext.request.contextPath}/register.html" role="form">
				<div class="row form-group">
					<form:label path="name" for="nam" class="col-lg-2 control-label"><fmt:message key="reg.name" /> </form:label>
					<div class="col-lg-10">				
						<form:input path="name" type="text" class="form-control" id="nam" placeholder="Enter your name" />
						<form:errors path="name" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="lastName" for="lname" class="col-lg-2 control-label"><fmt:message key="reg.lastname" /> </form:label>
					<div class="col-lg-10">
						<form:input path="lastName" type="text" class="form-control" id="lname" placeholder="Enter your last name"/>
					<form:errors path="lastName" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="email" for="email" class="col-lg-2 control-label">Email </form:label>
					<div class="col-lg-10">
						<div class="input-group">
						<form:input path="email" type="email" class="form-control" id="email" placeholder="Enter your email"/>
						<div class="input-group-addon"><span class="fui-mail"></span></div>
						</div>
						<form:errors path="email" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="id" for="id" class="col-lg-2 control-label"><fmt:message key="reg.idnumber" /> </form:label>
					<div class="col-lg-10">
						<form:input path="id" type="text" class="form-control" id="id" placeholder="Enter your identification number"/>
						<form:errors path="id" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="password" for="pass" class="col-lg-2 control-label"><fmt:message key="reg.password" /> </form:label>
					<div class="col-lg-10">
						<form:input path="password" type="password" class="form-control" id="pass" placeholder="Enter your password"/>
						<form:errors path="password" class="text-danger" />
					</div>
				</div>
				<div id="selectlanguage" class="row" style="margin-bottom: 15px;">
					<div class="col-lg-2 labelReg"><fmt:message key="reg.choose" /></div>
					<div class="col-lg-10">
						<form:select path="language" data-toggle="select" class="form-control select select-primary mrs mbm">
								<form:option value="EN"><fmt:message key="languages.en" /></form:option>
								<form:option value="ES"><fmt:message key="languages.es" /></form:option>
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
				
				
				<div class="text-center" style="margin-top: 20px;">
					<div class="btn-group">
						<button type="submit" class="btn btn-primary"><fmt:message key="reg.submit" /></button>
						<button type="reset" class="btn btn-danger"><fmt:message key="reg.reset" /></button>
					</div>
				</div>
			</form:form>
		</div>
	</div>
	</jsp:body>
</t:template>