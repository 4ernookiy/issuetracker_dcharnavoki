<%@page import="org.training.dcharnavoki.issuetracker.constant.Constant"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="edit-project.title" />
	<c:param name="titleP1" value="${project.id}" />
</c:import>
<div class="span12">
	<div class="hero">
		<div class="page-header">
			<h4>
				<fmt:message key="edit-project.title">
					<fmt:param value="${project.id}" />
				</fmt:message>
			</h4>
		</div>
	</div>
	<form name="editProject" class="form-horizontal" method="post"
		action="SaveProject">

		<input type="hidden" name="projectId" value="${project.id}">

		<div class="control-group">
			<label class="control-label" for="newName"><fmt:message
					key="create.project.name" /> </label>
			<div class="controls">
				<input type="text" name="newName" id="newName"
					value="${empty newName ? project.name :newName}"
					placeholder="<fmt:message key="create.project.name"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newDescription"><fmt:message
					key="create.project.description" /> </label>
			<div class="controls">
				<input type="text" name="newDescription" id="newDescription"
					value="${empty newDescription ? project.description :newDescription }"
					placeholder="<fmt:message key="create.project.description"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newAssigned"><fmt:message
					key="view.issue.assigned" /> </label>
			<div class="controls">
				<select name="newAssigned">
					<option value="-1" selected>
						<fmt:message key="view.issue.no-assigned" />
					</option>
					<c:forEach var="varItem" items="${users}">
						<c:set var="selectItem"
							value="${varItem.id eq (empty newAssigned.id ? project.manager.id : newAssigned.id)? 'selected' : ''}" />
						<option value="${varItem.id}" ${selectItem}>${varItem.firstName}&nbsp${varItem.lastName}:&nbsp${varItem.email}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newBuild"><fmt:message
					key="edit-project.versions" /> </label>
			<div class="controls">
				<select name="Build">
					<c:forEach var="varItem" items="${project.builds}">
						<option value="${varItem.id}">${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newBuild"><fmt:message
					key="edit-project.build.new" /> </label>
			<div class="controls">
				<input type="text" name="newBuild" id="newBuild"
					value="${newBuild}"
					placeholder="<fmt:message key="edit-project.build.new"/>">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn" name="action" value="update">
					<fmt:message key="view.btn.update" />
				</button>
			</div>
		</div>
	</form>

</div>
<jsp:include page="footer.jsp"></jsp:include>