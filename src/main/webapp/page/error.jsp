<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="title.error" />
</c:import>
<div></div>
<h5>
	<fmt:message key="alert.error" />
</h5>
<c:if test="${not empty key_alert_error}">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<c:out value="${key_alert_error}"></c:out>
	</div>
</c:if>
<jsp:include page="footer.jsp"></jsp:include>
