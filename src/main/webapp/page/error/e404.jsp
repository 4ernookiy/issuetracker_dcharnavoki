<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="../header.jsp">
	<c:param name="title" value="title.error.404" />
</c:import>

<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="title.error.404" />
			</h4>
		</div>
	</div>
	<c:if
		test="${not empty requestScope['javax.servlet.error.request_uri']}">
		<c:set var="resorceURI"
			value="${requestScope['javax.servlet.error.request_uri']}" />
		<fmt:message key="error.404.message">
			<fmt:param value="${resorceURI}"></fmt:param>
		</fmt:message>
	</c:if>
	<br>
	<c:if test="${not empty requestScope['javax.servlet.error.message']}">
		<c:out value="message=" />
		<fmt:message key="${requestScope['javax.servlet.error.message']}" />
	</c:if>
</div>
<jsp:include page="../footer.jsp"></jsp:include>