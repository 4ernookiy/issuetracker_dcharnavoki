<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page
	import="org.training.dcharnavoki.issuetracker.constant.Constant"%>
<%@ page
	import="org.training.dcharnavoki.issuetracker.beans.Message4Jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="alert" value="<%=Constant.MESSAGE%>" scope="request" />
<c:set var="info" value="<%=Message4Jsp.INFO%>" scope="request" />
<c:set var="success" value="<%=Message4Jsp.SUCCESS%>" scope="request" />
<c:set var="warning" value="<%=Message4Jsp.WARNING%>" scope="request" />
<c:set var="error" value="<%=Message4Jsp.ERROR%>" scope="request" />
<div class="span12">
	<c:if test="${not empty message4jsp}">
		<c:choose>
			<c:when test="${message4jsp.type eq info}">
				<c:set var="alertclass" value="alert alert-info" scope="page" />
				<c:set var="title" value="alert.info" scope="page" />
			</c:when>
			<c:when test="${message4jsp.type eq success}">
				<c:set var="alertclass" value="alert alert-success" scope="page" />
				<c:set var="title" value="alert.success" scope="page" />
			</c:when>
			<c:when test="${message4jsp.type eq error}">
				<c:set var="alertclass" value="alert alert-error" scope="page" />
				<c:set var="title" value="alert.error" scope="page" />
			</c:when>
			<c:otherwise>
				<c:set var="alertclass" value="alert" scope="page" />
				<c:set var="title" value="alert.warning" scope="page" />
			</c:otherwise>
		</c:choose>
		<div class="${alertclass}">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<h4>
				<fmt:message key="${title}" />
			</h4>
			<fmt:message key="${message4jsp.text}">
				<c:if test="${not empty message4jsp.params }">
					<c:forEach var="paramValue" items="${message4jsp.params}">
						<fmt:param value="${paramValue}" />
					</c:forEach>
				</c:if>
				<c:remove var="message4jsp" scope="session" />
			</fmt:message>
		</div>
	</c:if>
</div>
