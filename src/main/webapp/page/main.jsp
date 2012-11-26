<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="title.main" />
	<c:param name="page" value="1" />
</c:import>

<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="welcom" />
			</h4>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty key_issues}">
			<h6>
				<fmt:message key="main.not.found.issues" />
			</h6>
		</c:when>
		<c:otherwise>
			<table class="table table-hover">
				<thead>
					<tr>
						<th><fmt:message key="view.issue.id" /></th>
						<th><fmt:message key="view.issue.priority" /></th>
						<th><fmt:message key="view.issue.assigned" /></th>
						<th><fmt:message key="view.issue.type" /></th>
						<th><fmt:message key="view.issue.status" /></th>
						<th><fmt:message key="view.issue.summary" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oneissue" items="${key_issues}">
						<c:if test="${oneissue.priority.id == 1}">
							<c:set var="priorityColor" value="error" />
						</c:if>
						<c:if test="${oneissue.priority.id == 2}">
							<c:set var="priorityColor" value="warning" />
						</c:if>
						<c:if test="${oneissue.priority.id == 3}">
							<c:set var="priorityColor" value="info" />
						</c:if>
						<c:if test="${oneissue.priority.id == 4}">
							<c:set var="priorityColor" value="success" />
						</c:if>
						<tr class="${priorityColor}">
							<c:choose>
								<c:when test="${not empty key_user}">
									<c:url value="Issue" var="IssueLink">
										<c:param name="issueId" value="${oneissue.getId()}" />
									</c:url>
									<td><a href="${IssueLink}"><c:out
												value="${oneissue.getId()}" /></a></td>
								</c:when>
								<c:otherwise>
									<td>${oneissue.id}</td>
								</c:otherwise>
							</c:choose>
							<td>${oneissue.priority.description}</td>
							<td>${oneissue.assigned.email}</td>
							<td>${oneissue.type.description}</td>
							<td>${oneissue.status.description}</td>
							<td>${oneissue.summary}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
	<br>
</div>
<jsp:include page="footer.jsp"></jsp:include>
