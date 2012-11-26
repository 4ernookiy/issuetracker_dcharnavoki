<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page errorPage="error.jsp"%>
<c:import url="page/header2.jsp">
	<c:param name="title" value="title.main" />
	<c:param name="page" value="1" />
</c:import>
<div class="wrap">
	<div class="container">
		<div class="page-header">
			<h1>Sticky footer</h1>
		</div>
		<p class="lead">Pin a fixed-height footer to the bottom of the
			viewport in desktop browsers with this custom HTML and CSS.</p>
	</div>
	<div id="push"></div>
</div>
<jsp:include page="page/footer2.jsp"></jsp:include>