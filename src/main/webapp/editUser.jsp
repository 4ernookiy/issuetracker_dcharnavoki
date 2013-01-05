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
			<h4>
				<fmt:message key="edit-user.welcom">
					<fmt:param value="${key_user.firstName} ${key_user.lastName}" />
				</fmt:message>
			</h4>
		</div>
	</div>
	<form name="editUser" class="form-horizontal" action="SaveUser">

		<div class="control-group">
			<label class="control-label" for="newFirstName"><fmt:message
					key="edit-user.first-name" /> </label>
			<div class="controls">
				<input type="text" name="newFirstName" id="newFirstName"
					value="${empty newFirstName ? key_user.firstName : newFirstName}"
					placeholder="<fmt:message key="edit-user.first-name"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newLastName"><fmt:message
					key="edit-user.last-name" /> </label>
			<div class="controls">
				<input type="text" name="newLastName" id="newLastName"
					value="${empty newLastName ? key_user.lastName : newLastName}"
					placeholder="<fmt:message key="edit-user.last-name"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newEmail"><fmt:message
					key="edit-user.email" /> </label>
			<div class="controls">
				<input type="text" name="newEmail" id="newEmail"
					value="${empty newEmail ? key_user.email : newEmail}"
					placeholder="<fmt:message key="edit-user.email"/>">
			</div>
		</div>

		<c:set var="roleAdmin" value="<%=Role.ADMIN%>" />
		<c:if test="${roleAdmin eq key_user.role}">
			<c:set var="roles" value="<%=Role.values()%>" />
			<c:set var="roleGuest" value="<%=Role.GUEST%>" />
			<div class="control-group">
				<label class="control-label" for="newEmail"><fmt:message
						key="edit-user.role" /> </label>
				<div class="controls">
					<select name="newRole">
						<c:forEach var="role" items="${roles}">
							<c:if test="${role != roleGuest}">
								<c:set var="selectOption"
									value="${role eq key_user.role ? 'selected':''}"></c:set>
								<option value="${role}"${selectOption}>
									<c:out value="${role }" />
								</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</c:if>

		<div class="control-group">
			<div class="controls">
				<a href="pageChangePassword"><fmt:message key="edit-user.password.change" />
				</a>
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">
					<fmt:message key="view.btn.update" />
				</button>
			</div>
		</div>
	</form>
</div>
<jsp:include page="page/footer.jsp"></jsp:include>
