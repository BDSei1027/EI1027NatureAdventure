<%@ tag description="Estructura de la pagina de administracion de los instructores"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Estan los campos de Form:form preparados no se porque falla -->
<h2>Instructor management</h2>
<div id="actionsHelp" class="row">
	<div id="action" class="col-lg-2">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="#" style="padding-top: 6px; padding-bottom: 6px;">All</a></li>
			<li><a href="#OnlyActive" style="padding-top: 6px; padding-bottom: 6px;">Only active</a>
			<li><a href="#OnlyInactive" style="padding-top: 6px; padding-bottom: 6px;">Only inactive</a></li>
			<li>
				<a id="popover" href="#SearchInstructor" role="button" style="padding-top: 6px; padding-bottom: 6px;">Search</a>
				<div id="popover-body" style="max-width:none; width: 250px;" class="hide">
					<form role="form" method="get" modelAttribute="searchinstructor" action="${pageContext.request.contextPath}/admin/searchInstructor.html">
						<div class="form-group">
							<label path="value" for="sea" class="control-label">Search </label>
							<input path="value" id="search" type="text" class="form-control" />
						</div>
						<div class="form-group">
							What field?
							<select path="field">
								<option value="ssnumber">SSNumber</option>
								<option value="idnumber">IDNumber</option>
								<option value="name">Name</option>
								<option value="lastname">Last name</option>
								<option value="email">Email</option>
							</select>
						</div>
					</form>
				</div>
			</li>
			<li><a href="${pageContext.request.contextPath}/admin/instructorManagement/add.html" style="padding-top: 6px; padding-bottom: 6px;">New instructor</a></li>
		</ul>
	</div>
	<div id="help" class="col-lg-10">
		<div class="panel panel-info">
			<div class="panel-heading">
				<h3 class="panel-title">Help!</h3>
			</div>
			<div class="panel-body">
				<p>The following table lists all the instructor, which are in the data base.</p>
				<p>To see the activities of the instructor, click on <span class="label label-info">Edit</span></p>
				<p>In menu of the left, you can choose how do you want the data and add more instructors</p>
			</div>
		</div>
	</div>
</div>

<!-- poner un div despues de esta tag -->