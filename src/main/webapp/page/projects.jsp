<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="projects.title" />
	<c:param name="page" value="8" />
</c:import>


<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="projects.welcom" />
			</h4>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty projects}">
			<h6>
				<fmt:message key="projects.no-data" />
			</h6>
		</c:when>
		<c:otherwise>
			<table class="table table-hover">
				<thead>
					<tr>
						<th><fmt:message key="view.issue.id" /></th>
						<th><fmt:message key="view.project.name" /></th>
						<th><fmt:message key="view.project.manager" /></th>
						<th><fmt:message key="view.project.description" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="oneItem" items="${projects}">
						<tr>
							<c:choose>
								<c:when test="${not empty key_user}">
									<c:url value="EditProject" var="ProjectLink">
										<c:param name="projectId" value="${oneItem.getId()}" />
									</c:url>
									<td><a href="${ProjectLink}"><c:out
												value="${oneItem.getId()}" /></a></td>
								</c:when>
								<c:otherwise>
									<td>${oneItem.id}</td>
								</c:otherwise>
							</c:choose>
							<td>${oneItem.name}</td>
							<td>${oneItem.manager.email}</td>
							<td>${oneItem.description}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="footer.jsp"></jsp:include>
