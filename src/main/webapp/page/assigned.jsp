<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="title.assigned" />
	<c:param name="page" value="2" />
</c:import>
<div class="span12">
	<div class="hero">
		<div class="page-header">
			<h4>
				<fmt:message key="welcom.assigned" />
			</h4>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty key_issues}">
			<h6>
				<fmt:message key="assigned.not.found.issues" />
			</h6>
		</c:when>
		<c:otherwise>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>Id</th>
						<th>Priority</th>
						<th>Assigned</th>
						<th>Type</th>
						<th>Status</th>
						<th>Summary</th>
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
												value="${oneissue.getId()}" /> </a></td>
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
</div>
</c:otherwise>
</c:choose>

<jsp:include page="footer.jsp"></jsp:include>
