<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="edit.status.title" />
	<c:param name="page" value="7" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="edit.status.welcom" />
			</h4>
		</div>
	</div>
	<div><h5><fmt:message key="edit.resolution.help-description" /></h5></div>
	
	<form name="statusEdit" class="form-horizontal" method="post"
		action="SaveStatus">

		<div class="control-group">
			<label class="control-label" for="editStatus"><fmt:message
					key="edit.status.select-status" /> </label>
			<div class="controls">
				<select name="editStatus">
					<option value="0">
						<fmt:message key="view.selected" />
					</option>
					<c:forEach var="varItem" items="${statuses}">
						<c:set var="selectItem"
							value="${editStatus.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}" ${selectItem}>${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newStatus"><fmt:message
					key="edit.resolution.new-description" /> </label>
			<div class="controls">
				<input type="text" name="newStatus" id="newStatus"
					value="${newStatus}"
					placeholder="<fmt:message key="edit.resolution.new-description"/>">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button class="btn" type="submit" name="action" value="update">
					<fmt:message key="view.btn.update" />
				</button>
			</div>
		</div>

	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
