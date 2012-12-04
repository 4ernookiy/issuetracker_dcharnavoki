<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="header.jsp">
	<c:param name="title" value="edit.type.title" />
	<c:param name="page" value="6" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="edit.type.welcom" />
			</h4>
		</div>
	</div>
	<div><h5><fmt:message key="edit.type.help-description" /></h5></div>
	
	<form name="typeEdit" class="form-horizontal" method="post"
		action="SaveType">

		<div class="control-group">
			<label class="control-label" for="editType"><fmt:message
					key="edit.type.select-type" /> </label>
			<div class="controls">
				<select name="editType">
					<option value="0">
						<fmt:message key="view.selected" />
					</option>
					<c:forEach var="varItem" items="${types}">
						<c:set var="selectItem"
							value="${editType.id eq varItem.id ? 'selected' : ''}" />
						<option value="${varItem.id}" ${selectItem}>${varItem.description}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newType"><fmt:message
					key="edit.resolution.new-description" /> </label>
			<div class="controls">
				<input type="text" name="newType" id="newType"
					value="${newType}"
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
