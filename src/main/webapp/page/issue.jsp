<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="title.issue.description" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<c:url value="EditIssue" var="EditIssueLink">
					<c:param name="issueId" value="${key_issue.id}" />
				</c:url>
				<a href="${EditIssueLink}"><fmt:message key="view.menu.issue" />&nbsp
					<fmt:message key="view.issue.id" /> &nbsp:&nbsp${key_issue.id}
					- <fmt:message key="view.issue.edit" /></a>
			</h4>
		</div>
	</div>
	<table class="table table-striped table-bordered">
		<tbody>
			<tr>
				<td><fmt:message key="view.issue.project" />
				</td>
				<td>${key_issue.project.name}</td>
				<td><fmt:message key="view.issue.build" />
				</td>
				<td>${key_issue.build.description}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.added" />
				</td>
				<td><fmt:formatDate value="${key_issue.createDate}"
						dateStyle="full" />
				</td>
				<td><fmt:message key="view.issue.create" />
				</td>
				<td>${key_issue.createdBy.email}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.last-update" />
				</td>
				<td><fmt:formatDate value="${key_issue.modifyDate}"
						dateStyle="full" />
				</td>
				<td><fmt:message key="view.issue.changed" />
				</td>
				<td>${key_issue.modifiedBy.email}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.type" />
				</td>
				<td>${key_issue.type.description}</td>
				<td><fmt:message key="view.issue.status" />
				</td>
				<td>${key_issue.status.description}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.priority" />
				</td>
				<td>${key_issue.priority.description}</td>
				<td><fmt:message key="view.issue.resolution" />
				</td>
				<td>${key_issue.resolution.description}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.assigned" />
				</td>
				<td colspan="3">${key_issue.assigned.email}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.summary" />
				</td>
				<td colspan="3">${key_issue.summary}</td>
			</tr>
			<tr>
				<td><fmt:message key="view.issue.description" />
				</td>
				<td colspan="3">${key_issue.description}</td>
			</tr>
		</tbody>
	</table>
	<c:if test="${not empty key_comments}">
	<hr>
		<span class="label label-success">Comments</span>
		<br>
		<c:forEach var="comment" items="${key_comments}">
			<p style='padding: 20px'>
				<i class="icon-comment"></i> ${comment.user.firstName}
				${comment.user.lastName},
				<fmt:formatDate value="${comment.date}" type="both"
					pattern="EEEE, dd MMMM yyyy, HH:mm" />
				<br> ${comment.text}
			</p>
		</c:forEach>
	</c:if>
</div>
<jsp:include page="footer.jsp"></jsp:include>