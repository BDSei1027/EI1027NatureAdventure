<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:message key="" />

<t:template>
<jsp:body>
<h4><fmt:message key="admin.activitymanage.title" /></h4>
<h6 class="subtitle"><fmt:message key="admin.activitymanage.addactivity.title" /></h6>
<div class="panel panel-default">
	<div class="panel-heading">
		<div class="panel-title"><fmt:message key="admin.activitymanage.addactivity.form.title" /></div>
	</div>
	<div class="panel-body">
		<div class="container-fluid">
		<form:form method="post" modelAttribute="activity" role="form">
			<div class="row form-group">
				<form:label path="name" for="name" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.name" /> <em>(EN)</em></form:label>
				<div class="col-lg-10">
					<div class="input-group">
					<div class="input-group-addon">EN</div>
					<form:input path="name" type="text" class="form-control" id="name" placeholder="Enter the Name" />
					</div>
					<form:errors path="name" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="nombre" for="nom" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.name" /> <em>(ES)</em></form:label>
				<div class="col-lg-10">
					<div class="input-group">
					<div class="input-group-addon">ES</div>
					<form:input path="nombre" type="text" class="form-control" id="nom" placeholder="Introduce el nombre" />
					</div>
					<form:errors path="nombre" class="text-danger" />
				</div>
			</div>
			
			<div class="row form-group">
				<form:label path="level" for="lvl" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.level" /> </form:label>
				<div class="col-lg-10">
					<form:select path="level" class="form-control select select-primary mrs mbm" data-toggle="select">
						<form:option value="0"><fmt:message key="admin.activitymanage.addactivity.form.level.0" /></form:option>
						<form:option value="1"><fmt:message key="admin.activitymanage.addactivity.form.level.1" /></form:option>
						<form:option value="2"><fmt:message key="admin.activitymanage.addactivity.form.level.2" /></form:option>
						<form:option value="3"><fmt:message key="admin.activitymanage.addactivity.form.level.3" /></form:option>
					</form:select>
				</div>
			</div>
			<div class="row form-group">
				<form:label path="schedule" for="sch" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.schedule" /> </form:label>
				<div class="col-lg-10">
					<form:select path="schedule" class="form-control select select-primary mrs mbm" data-toggle="select">
						<form:option value="morning"><fmt:message key="admin.activitymanage.addactivity.form.schedule.morn" /></form:option>
						<form:option value="evening"><fmt:message key="admin.activitymanage.addactivity.form.schedule.eve" /></form:option>
						<form:option value="night"><fmt:message key="admin.activitymanage.addactivity.form.schedule.night" /></form:option>
						<form:option value="midnight"><fmt:message key="admin.activitymanage.addactivity.form.schedule.mid" /></form:option>
					</form:select>
				</div>
			</div>
			<div class="row form-group">
				<form:label path="price" for="price" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.price" /> </form:label>
				<div class="col-lg-10">
					<div class="input-group">
      					<div class="input-group-addon"><span class="glyphicon glyphicon-euro"></span></div>
							<form:input path="price" type="text" class="form-control" id="price" placeholder="Enter the price" />
						</div>
					<form:errors path="price" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="place" for="place" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.place" /> </form:label>
				<div class="col-lg-10">
					<form:input path="place" type="text" class="form-control" id="place" placeholder="Enter the place" />
					<form:errors path="place" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="minimumGroup" for="min" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.ming" /> </form:label>
				<div class="col-lg-10">
					<form:input path="minimumGroup" type="number" class="form-control" id="min" placeholder="Enter the minimum group"/>
					<form:errors path="minimumGroup" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<form:label path="maximumGroup" for="max" class="col-lg-2 control-label"><fmt:message key="admin.activitymanage.addactivity.form.maxg" /> </form:label>
				<div class="col-lg-10">
					<form:input path="maximumGroup" type="number" class="form-control" id="max" placeholder="Enter the maximum group"/>
					<form:errors path="maximumGroup" class="text-danger" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-lg-12">
					<form:label path="description" for="description" class="control-label"><fmt:message key="admin.activitymanage.addactivity.form.description" /> <em>(EN)</em></form:label>
					<form:textarea path="description" class="form-control" rows="3" />
					<form:errors path="description" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-lg-12">
					<form:label path="descripcion" for="descripcion" class="control-label"><fmt:message key="admin.activitymanage.addactivity.form.description" /> <em>(ES)</em></form:label>
					<form:textarea path="descripcion" class="form-control" rows="3" />
					<form:errors path="descripcion" />
				</div>
			</div>
			<div class="text-center">
				<div class="btn-group">
					<button type="submit" class="btn btn-primary"><fmt:message key="admin.activitymanage.addactivity.form.submit" /></button>
					<button type="reset" class="btn btn-danger"><fmt:message key="admin.activitymanage.addactivity.form.clear" /></button>
				</div>
			</div>
		</form:form>
		</div>
 	</div>
 </div>
</jsp:body>
</t:template>