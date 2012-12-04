<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="edit.priority.title" />
	<c:param name="page" value="5" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="edit.priority.welcom" />
			</h4>
		</div>
	</div>
	<div><h5><fmt:message key="edit.resolution.help-description" /></h5></div>
	
	<form name="priorityEdit" class="form-horizontal" method="post"
		action="SavePriority">

		<div class="control-group">
			<label class="control-label" for="editPriority"><fmt:message
					key="edit.priority.select-priority" /> </label>
			<div class="controls">
				<select name="editPriority">
					<option value="0">
						<fmt:message key="view.selected" />
					</option>
					<c:forEach var="varItem" items="${priorities}">
						<c:set var="selectItem"
							value="${editPriority.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}" ${selectItem}>${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newPriority"><fmt:message
					key="edit.resolution.new-description" /> </label>
			<div class="controls">
				<input type="text" name="newPriority" id="newPriority"
					value="${newPriority}"
					placeholder="<fmt:message key="edit.resolution.new-description"/>">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button class="btn btn-primary" type="submit" name="action"
					value="save">
					<fmt:message key="view.btn.add" />
				</button>
				<button class="btn" type="submit" name="action" value="update">
					<fmt:message key="view.btn.update" />
				</button>
			</div>
		</div>

	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
