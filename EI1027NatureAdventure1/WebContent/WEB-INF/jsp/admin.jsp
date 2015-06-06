<?xml version="1.0" encoding="UTF-8" ?>
<%@page contentType="text/html; charset=iso-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:template>
	<jsp:body>
	    <div class="row">
      <div class="col-lg-6">
        <h3>Welcome PEPE</h3>
  			<h5 class="subtitle">This is your main page. <br><small class="subtitle" style="color: #95a5a6;">Here you can manage your tasks.</small></h5>
  		</div>
      <div class="col-lg-6">
        <h6 class="text-right">Today is <strong class="high">02/03/2015</strong></h6>
      </div>

  	</div>
    <hr>
    <div class="row">
      <div class="col-lg-2" style="width: 22%;">
    			<ul class="nav nav-pills nav-stacked">
            <li><h4>Menu</h4></li>
    				<li class="active"><a href="${pageContext.request.contextPath}/admin/">Activty management</a></li>
    				<li class="active"><a href="${pageContext.request.contextPath}/admin/">Instructor management</a>
    				<li class="active"><a href="${pageContext.request.contextPath}/admin/">Booking management</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/admin/">Client management</a></li>
            <li class="active"><a href="${pageContext.request.contextPath}/admin/">User management</a></li>
    			</ul>
      </div> <!-- col -->
      <div class="col-lg-9">
        <h6 class="text-center"><span class="badge">20</span>pending bookings</h6>

        <p> Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis accumsan nisl. Proin dui ipsum, facilisis maximus ex vitae, dapibus venenatis nisl. Ut finibus vitae arcu quis vestibulum. Cras finibus enim id magna dictum, quis ornare odio suscipit. Curabitur porttitor dui id tempor malesuada. Nullam sit amet gravida neque, nec placerat elit. Praesent auctor tempus magna, eu ornare nulla ornare non. Ut molestie volutpat condimentum. Nulla tristique risus sed feugiat scelerisque. Sed varius pulvinar lorem, nec tempor ipsum dapibus sit amet. In eget tincidunt mi, sit amet aliquam tortor. Fusce molestie eget est vel lobortis. Donec dui purus, sagittis hendrerit pulvinar mattis, rhoncus a sapien. In elementum, mi et malesuada pretium, massa arcu consectetur massa, et imperdiet erat ligula nec diam. Curabitur et purus nec magna luctus posuere. </p>
<p> Mauris sollicitudin tempor sem, eu tristique quam ultricies in. Nullam placerat ultrices sem eu aliquam. Quisque turpis enim, pretium et sodales et, consectetur et elit. Sed tincidunt lacus non ligula pharetra fringilla. Ut eget ante quis est rhoncus convallis nec id mauris. Suspendisse accumsan lorem et gravida dignissim. Donec ut nibh leo. Vivamus pulvinar congue ligula. Donec auctor vel ex id vehicula. Nulla pellentesque molestie velit in auctor. Etiam varius erat ante, id ornare tellus fermentum eu. Etiam egestas velit ut lacinia lacinia. </p>
<p> Integer sollicitudin dolor lacus, vel finibus tellus faucibus ut. Donec elementum eleifend tempor. Donec condimentum tempus risus placerat tincidunt. Proin non velit viverra, maximus felis et, porttitor massa. Nunc non sapien sem. Proin ultrices eu ex vitae venenatis. Vestibulum at congue nisi. In elementum est ut ipsum commodo porta. Etiam lacinia, nisl vitae ultricies cursus, ex ligula dignissim dolor, id placerat dolor ipsum vel felis. </p>
<p> Praesent quam dolor, posuere efficitur iaculis vel, varius nec nisi. Phasellus id purus nunc. Donec fringilla augue vel odio facilisis malesuada. Praesent pellentesque pharetra semper. Duis pharetra velit sed ligula imperdiet porta ut sed mauris. Sed sagittis nec est quis ullamcorper. Curabitur sit amet maximus tortor, et blandit nulla. Vestibulum nunc leo, dapibus eget porta eget, luctus sed ex. Aliquam ut dolor sem. Aenean vulputate dapibus tempus. Praesent sollicitudin id ligula eu suscipit. In dignissim tortor arcu, sed imperdiet tortor sagittis vel. </p>
      </div>
    </div> <!-- row -->
	
	</jsp:body>
</t:template>