<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="create.project.title" />
	<c:param name="page" value="8" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="create.project.welcom" />
			</h4>
		</div>
	</div>
	<form name="projectNew" class="form-horizontal" method="post" action="SaveProject">
	
		<div class="control-group">
			<label class="control-label" for="newName"><fmt:message
					key="create.project.name" /> </label>
			<div class="controls">
				<input type="text" name="newName" id="newName"
					value="${newName}"
					placeholder="<fmt:message key="create.project.name"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newDescription"><fmt:message
					key="create.project.description" /> </label>
			<div class="controls">
				<input type="text" name="newDescription" id="newDescription"
					value="${newDescription}"
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
							value="${newAssigned.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}" ${selectItem}>${varItem.firstName}&nbsp${varItem.lastName}:&nbsp${varItem.email}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newBuild"><fmt:message
					key="create.project.build" /> </label>
			<div class="controls">
				<input type="text" name="newBuild" id="newBuild" value="${newBuild}"
					placeholder="<fmt:message key="create.project.build" />">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn" name="action" value="save">
					<fmt:message key="create.project.btn.submit" />
				</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
