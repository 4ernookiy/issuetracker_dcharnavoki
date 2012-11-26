<%@page import="org.training.dcharnavoki.issuetracker.constant.Constant"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="edit-issue.title" />
	<c:param name="titleP1" value="${key_issue.id}" />
	<c:param name="page" value="1" />
</c:import>
<div class="span12">
	<div class="hero">
		<div class="page-header">
			<h4>
				<fmt:message key="edit-issue.title">
					<fmt:param value="${key_issue.id}" />
				</fmt:message>
			</h4>
		</div>
	</div>
	<form name="createIssue" method="post" action="SaveIssue">

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.create" />
			</div>
			<div class="span8">
				<table class="table table-hover table-condensed">
					<tbody>
						<tr>
							<td>${key_issue.createdBy.firstName}
								${key_issue.createdBy.lastName} : ${key_issue.createdBy.email}</td>
							<td><fmt:formatDate value="${key_issue.createDate}"
									dateStyle="full" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.changed" />
			</div>
			<div class="span8">
				<table class="table table-hover table-condensed">
					<tbody>
						<tr>
							<td>${key_issue.modifiedBy.firstName}
								${key_issue.modifiedBy.lastName} : ${key_issue.modifiedBy.email}</td>
							<td><fmt:formatDate value="${key_issue.modifyDate}"
									dateStyle="full" /></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.summary" />
			</div>
			<div class="span8">
				<input class="input-block-level" type="text" name="newSummary"
					value="${key_issue.summary}"
					placeholder="<fmt:message key="view.issue.summary"/>">
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.description" />
			</div>
			<div class="span8">
				<textarea class="input-block-level" type="text" rows="5"
					name="newDescription"
					placeholder="<fmt:message key="view.issue.description"/>">${key_issue.description}</textarea>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.status" />
			</div>
			<div class="span8">
				<select name="newStatus">
					<c:forEach var="varItem" items="${statuses}">
						<c:set var="selectItem"
							value="${varItem.id eq key_issue.status.id ? 'selected' : ''}" />
						<option value="${varItem.id}"${selectItem} >${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.priority" />
			</div>
			<div class="span8">
				<select name="newPriority">
					<c:forEach var="varItem" items="${priorities}">
						<c:set var="selectItem"
							value="${key_issue.priority.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}"${selectItem}>${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.type" />
			</div>
			<div class="span8">
				<select name="newType">
					<c:forEach var="varItem" items="${types}">
						<c:set var="selectItem"
							value="${key_issue.type.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}"${selectItem} >${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.project" />
				&nbsp&&nbsp
				<fmt:message key="view.issue.build" />
			</div>
			<c:set var="delimeter" value="<%=Constant.DELIMETER %>" />
			<div class="span8">
				<select name="newProject">
					<c:forEach var="project" items="${projects}">
						<c:forEach var="build" items="${project.builds}">
							<c:set var="issuePbId"
								value="${key_issue.project.id}${delimeter}${key_issue.build.id}" />
							<c:set var="pbId" value="${project.id}${delimeter}${build.id}" />
							<c:set var="selectItem"
								value="${issuePbId eq pbId ? 'selected' : ''}" />
							<option value="${pbId}"${selectItem} >${project.name}&nbsp:&nbsp${build.description}</option>
						</c:forEach>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.assigned" />
			</div>
			<div class="span8">
				<select name="newAssigned">
					<option value="-1" selected>
						<fmt:message key="view.issue.no-assigned" />
					</option>
					<c:forEach var="varItem" items="${users}">
						<c:set var="selectItem"
							value="${key_issue.assigned.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}"${selectItem} >${varItem.firstName}&nbsp${varItem.lastName}:&nbsp${varItem.email}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="textarea.comment.add" />
			</div>
			<div class="span8">
				<textarea name="newComment" class="input-block-level" rows="2"
					placeholder="<fmt:message key="textarea.comment.add" />"></textarea>
			</div>
		</div>

		<div class="form">
			<button type="submit" class="btn btn-primary">
				<fmt:message key="btn.issue.update" />
			</button>
		</div>
	</form>

</div>
<jsp:include page="footer.jsp"></jsp:include>