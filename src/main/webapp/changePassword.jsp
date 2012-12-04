<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="./page/header.jsp">
	<c:param name="title" value="edit-user.title" />
	<c:param name="titleP1" value="${key_user.email}" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>${key_user.firstName} ${key_user.lastName} ?</h4>
		</div>
	</div>
	<form name="changePassword" class="form-horizontal" method="post" action="ChangePassword">

		<div class="control-group">
			<label class="control-label" for="oldPassword"><fmt:message
					key="edit-user.password.old" /> </label>
			<div class="controls">
				<input name="oldPassword" type="password" id="oldPassword"
					placeholder="<fmt:message key="edit-user.password.old" />">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newPassword"><fmt:message
					key="edit-user.password.new" /> </label>
			<div class="controls">
				<input name="newPassword" type="password" id="newPassword"
					placeholder="<fmt:message key="edit-user.password.new" />">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="confirmPassword"><fmt:message
					key="edit-user.password.confirm" /> </label>
			<div class="controls">
				<input name="confirmPassword" type="password" id="confirmPassword"
					placeholder="<fmt:message key="edit-user.password.confirm" />">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">
					<fmt:message key="view.btn.change" />
				</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="page/footer.jsp"></jsp:include>
