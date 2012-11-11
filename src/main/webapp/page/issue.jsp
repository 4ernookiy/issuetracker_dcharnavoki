<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="title.issue.description" />
</c:import>
<!-- 	<c:if test="${not empty sessionScope.key_user}">
	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand" href="#">Title</a>
			<ul class="nav">
				<li class="active"><a href="#">Home</a>
				</li>
				<li><a href="#">Link</a>
				</li>
				<li><a href="#">Link</a>
				</li>
			</ul>
		</div>
	</div>
	</c:if> -->
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span2">
			<button class="btn" type="button">Edit</button>
		</div>
		<div class="span10">
			<!--Body content-->
			<fmt:message key="view.menu.issue" />
			<fmt:message key="view.issue.id" />
			:${key_issue.id}
			<table class="table table-striped table-bordered">
				<tbody>
					<tr>
						<td><fmt:message key="view.issue.project" /></td>
						<td>${key_issue.project.name}</td>
						<td><fmt:message key="view.issue.build" /></td>
						<td>${key_issue.build.description}</td>
					</tr>
					<tr>
						<td><fmt:message key="view.issue.added" /></td>
						<td>${key_issue.createDate}</td>
						<td><fmt:message key="view.issue.create" /></td>
						<td>${key_issue.createdBy.email}</td>
					</tr>
					<tr>
						<td><fmt:message key="view.issue.last-update" /></td>
						<td>${key_issue.modifyDate}</td>
						<td><fmt:message key="view.issue.assigned" /></td>
						<td>${key_issue.assigned.email}</td>
					</tr>
					<tr>
						<td><fmt:message key="view.issue.type" /></td>
						<td>${key_issue.type.description}</td>
						<td><fmt:message key="view.issue.status" /></td>
						<td>${key_issue.status.description}</td>
					</tr>
					<tr>
						<td><fmt:message key="view.issue.priority" /></td>
						<td>${key_issue.priority.description}</td>
						<td><fmt:message key="view.issue.resolution" /></td>
						<td>${key_issue.resolution.description}</td>
					</tr>
					<tr>
						<td><fmt:message key="view.issue.description" /></td>
						<td colspan="3">${key_issue.description}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>


<jsp:include page="footer.jsp"></jsp:include>