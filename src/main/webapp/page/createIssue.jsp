<%@page import="org.training.dcharnavoki.issuetracker.constant.Constant"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="view.menu.new-issue" />
	<c:param name="page" value="3" />
</c:import>
<div class="span12">
	<div class="hero">
		<div class="page-header">
			<h4>
				<fmt:message key="create-issue.welcom" />
			</h4>
		</div>
	</div>
	<form name="createIssue" method="post" action="SaveIssue">

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.summary" />
			</div>
			<div class="span8">
				<input class="input-block-level" type="text" name="newSummary"
					value="${newSummary}"
					placeholder="<fmt:message key="view.issue.summary"/>">
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.description" />
			</div>
			<div class="span8">
				<textarea class="input-block-level" type="text"
					name="newDescription" value="${newDescription}"
					placeholder="<fmt:message key="view.issue.description"/>">${newDescription}</textarea>
			</div>
		</div>

		<div class="row">
			<div class="span2">
				<fmt:message key="view.issue.status" />
			</div>
			<div class="span8">
				<select name="newStatus">
					<c:forEach var="varItem" items="${statuses}">
						<c:if test="${(varItem.id eq 1 ) or (varItem.id eq 2)}">
							<c:set var="selectItem"
								value="${newStatus eq varItem ? 'selected' : ''}" />
							<option value="${varItem.id}"${selectItem}>${varItem.description}</option>
						</c:if>
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
							value="${newPriority eq varItem ? 'selected' : ''}" />
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
							value="${newType eq varItem ? 'selected' : ''}" />
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
							<c:set var="pbId" value="${project.id}${delimeter}${build.id}" />
							<c:set var="selectItem"
								value="${newProject eq pbId ? 'selected' : ''}" />
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
							value="${newAssigned eq varItem ? 'selected' : ''}" />
						<option value="${varItem.id}"${selectItem} >${varItem.firstName}&nbsp${varItem.lastName}:&nbsp${varItem.email}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form">
			<button type="submit" class="btn btn-primary">
				<fmt:message key="view.menu.new-issue" />
			</button>
		</div>

	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>