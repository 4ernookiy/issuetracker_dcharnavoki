<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="title.error" />
</c:import>
<div></div>
<div class="span12">
<div class="hero">
	<div class="page-header">
		<h4>
			<fmt:message key="alert.error" />
		</h4>
	</div>
</div>
<c:if
	test="${not empty requestScope['javax.servlet.error.request_uri']}">
	<c:set var="resorceURI"
		value="${requestScope['javax.servlet.error.request_uri']}" />
		<c:out value="URL=${resorceURI}"></c:out>
</c:if>
<c:if test="${not empty key_alert_error}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<c:out value="${key_alert_error}"></c:out>
	</div>
	<hr>
</c:if>
<c:if
	test="${not empty requestScope['javax.servlet.error.status_code']}">
	<c:out
		value="errorCode=${requestScope['javax.servlet.error.status_code']}"></c:out>
	<hr>
</c:if>
<c:if test="${not empty requestScope['javax.servlet.error.exception']}">
	<c:out
		value="exception=${requestScope['javax.servlet.error.exception']}" />
	<hr>
</c:if>
<c:if test="${not empty requestScope['javax.servlet.error.message']}">
	<c:out value="message=${requestScope['javax.servlet.error.message']}" />
	<hr>
</c:if>
<c:if
	test="${not empty requestScope['javax.servlet.error.servlet_name']}">
	<c:out
		value="servletname=${requestScope['javax.servlet.error.servlet_name']}"></c:out>
	<hr>
</c:if>
</div>
<jsp:include page="footer.jsp"></jsp:include>
