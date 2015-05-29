<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 

<t:template>
	<jsp:body>
	<h2>Sign up</h2>
	<p>Congratulation, you decided to sign up in our system.</p>
	<p>The privileage of been registered are:</p>
	<ul>
		<li>Faster bookings</li>
		<li>Save your bookings.</li>
		<li>Subscribe to our newsletter.</li>
	</ul>
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">Sign up form</h3>
		</div>
		<div class="panel-body">
			<form:form method="post" modelAttribute="register" action="${pageContext.request.contextPath}/register.html" role="form">
				<div class="row form-group">
					<form:label path="name" for="nam" class="col-lg-2 control-label">Name: </form:label>
					<div class="col-lg-10">				
						<form:input path="name" type="text" class="form-control width-50" id="nam" placeholder="Your name" />
						<form:errors path="name" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="lastName" for="lname" class="col-lg-2 control-label">Last name: </form:label>
					<div class="col-lg-10">
						<form:input path="lastName" type="text" class="form-control width-50" id="lname" placeholder="Your last name"/>
					<form:errors path="lastName" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="email" for="email" class="col-lg-2 control-label">E-mail: </form:label>
					<div class="col-lg-10">
						<form:input path="email" type="email" class="form-control width-50" id="email" placeholder="Your email"/>
						<form:errors path="email" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="id" for="id" class="col-lg-2 control-label">ID number: </form:label>
					<div class="col-lg-10">
						<form:input path="id" type="text" class="form-control width-50" id="id" placeholder="Your identification number"/>
						<form:errors path="id" class="text-danger" />
					</div>
				</div>
				<div class="row form-group">
					<form:label path="password" for="pass" class="col-lg-2 control-label">Password: </form:label>
					<div class="col-lg-10">
						<form:input path="password" type="password" class="form-control width-50" id="pass" placeholder="Your password"/>
						<form:errors path="password" class="text-danger" />
					</div>
				</div>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">Conditions and terms</h3>	
					</div>
					<div class="panel-body" style="max-height: 200px;overflow-y: auto">
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus ut mattis sapien, et efficitur tortor. Sed metus erat, mattis aliquam porttitor consectetur, varius viverra diam. Sed porttitor metus sed lacus tincidunt pulvinar. Etiam molestie neque porta, tincidunt velit ac, dictum nunc. Etiam ut ante eu elit aliquam iaculis. Curabitur placerat pulvinar dui nec lobortis. Fusce volutpat sapien ut nisi dictum porta. Aenean venenatis nisl ut accumsan dapibus. Pellentesque porta nibh eros, et tempor turpis ullamcorper sit amet. Cras elementum pretium est, sit amet commodo ligula congue non. Duis posuere mauris at faucibus condimentum.</p>
						<p>Nulla maximus est a dui consequat pellentesque. Mauris quis porttitor nulla. Proin iaculis lacinia ipsum sed varius. Sed leo velit, pellentesque eget purus eu, ornare pulvinar sapien. Curabitur sit amet massa in tellus pellentesque posuere in vel nunc. Cras maximus posuere tincidunt. Duis non efficitur ex. Proin vitae tellus sed eros pulvinar laoreet sit amet at massa. Donec sed mauris eu magna dictum ultricies. Nunc vitae interdum arcu. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi facilisis lectus et enim rutrum viverra. </p>
						<p>Duis egestas aliquet felis. Mauris consectetur, purus in pharetra elementum, dolor purus varius sapien, sed congue risus nisi sit amet nisi. Ut id risus vel felis pellentesque porta id non neque. Curabitur auctor ac ligula at mollis. Phasellus porttitor pharetra diam, quis suscipit ipsum ornare eget. Sed facilisis nibh sed lacinia rhoncus. Suspendisse eu urna vitae ex ornare venenatis. Duis porta auctor semper. Nam aliquet tortor ac dui faucibus, id hendrerit risus feugiat. Nam ultricies, erat id scelerisque blandit, diam lectus pretium arcu, a porttitor est purus quis metus. Mauris in nisi nec sapien dictum blandit et id dolor. Sed eget vulputate sapien. Vivamus rutrum nisl ut cursus fringilla. Aenean suscipit nibh eget rutrum ornare. </p>
						<p>Quisque a orci enim. Aliquam gravida velit urna, eu congue metus imperdiet sit amet. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nulla ut vestibulum ligula. Sed purus risus, vehicula at nulla in, convallis pretium ante. Suspendisse tempor libero id sapien elementum vehicula eget ut tellus. Sed ornare velit suscipit luctus tristique. Curabitur malesuada at justo ac sodales. Ut auctor feugiat felis, in vulputate turpis tristique et. Nam consectetur ante eros, et porttitor lectus mollis ut. Aenean facilisis arcu ac facilisis consequat. Nulla aliquam vel lorem a venenatis. Proin tristique ut lorem vitae tincidunt. Donec scelerisque, sapien posuere finibus congue, nibh leo eleifend purus, ac dapibus augue nisl vel est. Nunc lacinia tristique elit sed luctus. Fusce quis malesuada ex.</p>
						<p>Suspendisse sit amet ligula id sapien rutrum luctus in eu ex. Donec vel purus a nisi convallis tempor eu ut leo. Sed ut ipsum euismod, bibendum nibh in, tincidunt odio. Aliquam volutpat dictum nisi a lacinia. Cras hendrerit consequat consectetur. Donec eget dui at velit aliquet interdum. Pellentesque porta massa dolor, blandit rhoncus lectus consectetur eu. In at tristique ex, et consectetur eros. Morbi enim est, condimentum aliquet sapien id, euismod imperdiet nisi. Cras sit amet justo gravida orci commodo cursus. Donec nec consequat dui. Aenean mattis mauris ante, vel ullamcorper lorem eleifend quis. Integer nec risus velit. </p>				
					</div>
				</div>
				<div class="text-center">
					<div class="checkbox">
						<form:label path="tocs">
							<form:checkbox path="tocs"/> Do you accept the TOCS?
							<br><form:errors path="tocs" class="text-danger"></form:errors>
						</form:label>
					</div>
				</div>
				<div id="selectlanguage" style="margin-bottom 15px;">
				Select your language: 
				<form:select path="language">
					<form:option value="EN">English</form:option>
					<form:option value="ES">Spanish</form:option>
				</form:select>
				</div>
				
				<button type="submit" class="btn btn-primary">Submit</button>
				<button type="reset" class="btn btn-danger">Clear</button>
			</form:form>
		</div>
	</div>
	</jsp:body>
</t:template>