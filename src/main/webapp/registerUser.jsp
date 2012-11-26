<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="./page/header.jsp">
	<c:param name="title" value="title.register" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="register.new-user" />
			</h4>
		</div>
	</div>
	<form name="register" class="form-horizontal" action="AddUser">
		<div class="control-group">
			<label class="control-label" for="newFirstName"><fmt:message
					key="register.label.first-name" /> </label>
			<div class="controls">
				<input type="text" name="newFirstName" id="newFirstName"
					value="${newFirstName}"
					placeholder="<fmt:message key="register.label.first-name"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newLastName"><fmt:message
					key="register.label.last-name" /> </label>
			<div class="controls">
				<input type="text" name="newLastName" id="newLastName"
					value="${newLastName}"
					placeholder="<fmt:message key="register.label.last-name"/>">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="newEmail"><fmt:message
					key="register.label.email" /> </label>
			<div class="controls">
				<input type="text" name="newEmail" id="newEmail" value="${newEmail}"
					placeholder="<fmt:message key="register.label.email" />">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="inputIcon"> <fmt:message
					key="register.label.role" /> </label>
			<div class="controls">
				<div class="input-prepend">
					<c:set var="roles" value="<%=Role.values()%>" />
					<c:set var="roleGuest" value="<%=Role.GUEST%>" />
					<c:set var="roleUser" value="<%=Role.USER%>" />
					<select name="role">
						<c:forEach var="role" items="${roles}">
							<c:if test="${role != roleGuest}">
								<c:choose>
									<c:when test="${role == roleUser}">
										<option selected value="${role}">
											<c:out value="${role }" />
										</option>
									</c:when>
									<c:otherwise>
										<option value="${role}">
											<c:out value="${role }" />
										</option>
									</c:otherwise>
								</c:choose>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="inputPassword"><fmt:message
					key="register.label.password" /> </label>
			<div class="controls">
				<input name="password" type="password" id="inputPassword"
					placeholder="<fmt:message key="register.label.password" />">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="inputPassword"><fmt:message
					key="register.label.password.confirm" /> </label>
			<div class="controls">
				<input name="confirm" type="password" id="inputPassword"
					placeholder="<fmt:message key="register.label.password.confirm" />">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<!-- <label class="checkbox"> <input type="checkbox">
				Remember me </label> -->
				<button type="submit" class="btn">
					<fmt:message key="register.btn.submit" />
				</button>
			</div>
		</div>
	</form>
	</div>
<jsp:include page="page/footer.jsp"></jsp:include>
