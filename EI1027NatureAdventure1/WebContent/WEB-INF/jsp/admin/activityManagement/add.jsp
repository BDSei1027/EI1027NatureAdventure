<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<t:template>
<jsp:body>
<h4>Activity management</h4>
<h6 class="subtitle">Add activity</h6>
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title">Form for add an activity</div>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
		<form:form method="post" modelAttribute="activity" role="form">
			<div class="row form-group">
				<form:label path="name" for="name" class="col-lg-2 control-label">Name </form:label>
				<div class="col-lg-10">
					<form:input path="name" type="text" class="form-control" id="name" placeholder="Enter name of the activity" />
					<form:errors path="name" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="level" for="lvl" class="col-lg-2 control-label">Level </form:label>
				<div class="col-lg-10">
					<form:select path="level" class="form-control select select-primary mrs mbm" data-toggle="select">
						<form:option value="0">Beginner</form:option>
						<form:option value="1">Basic</form:option>
						<form:option value="2">Medium</form:option>
						<form:option value="3">High</form:option>
					</form:select>
				</div>
			</div>
			<div class="row form-group">
				<form:label path="price" for="price" class="col-lg-2 control-label">Price </form:label>
				<div class="col-lg-10">
					<div class="input-group">
      					<div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
							<form:input path="price" type="number" class="form-control" id="price" placeholder="Enter the price" />
						</div>
					<form:errors path="price" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="place" for="place" class="col-lg-2 control-label">Place </form:label>
				<div class="col-lg-10">
					<form:input path="place" type="text" class="form-control" id="place" placeholder="Enter the place" />
					<form:errors path="place" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="minimumGroup" for="min" class="col-lg-2 control-label">Minimum group </form:label>
				<div class="col-lg-10">
					<form:input path="minimumGroup" type="text" class="form-control" id="min" placeholder="Enter the minimum group"/>
					<form:errors path="minimumGroup" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="maximumGroup" for="max" class="col-lg-2 control-label"> Maximum group </form:label>
				<div class="col-lg-10">
					<form:input path="maximumGroup" type="number" class="form-control" id="max" placeholder="Enter the maximum group"/>
					<form:errors path="maximumGroup" class="text-danger" />
				</div>
			</div>
			<div class="text-center">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary">Submit</button>
					<button type="reset" class="btn btn-danger">Clear</button>
				</div>
			</div>
		</form:form>
		</div>
 	</div>
 </div>
</jsp:body>
</t:template>