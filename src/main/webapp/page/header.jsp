<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ page import="org.training.dcharnavoki.issuetracker.constant.Constant"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<c:set var="language"
	value="${not empty param.changeLang ? param.changeLang : language}"
	scope="session" />
<fmt:setLocale value="${language}" scope="request" />
<fmt:setBundle basename="i18n.Messages" scope="request" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./page/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="./page/bootstrap/css/bootstrap-responsive.css.css"
	rel="stylesheet">
<title><fmt:message key="${param.title}" />
</title>
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner">
			<a class="brand" href="Main"> Issue Tracker</a>
			<c:if test="${not empty sessionScope.key_user}">
				<ul class="nav">
					<li class="${param.page == 1 ? 'active': ''}"><a href="Main"><fmt:message
								key="view.menu.issues" />
					</a></li>
					<li class="${param.page == 2 ? 'active': ''}"><a href="#"><fmt:message
								key="view.menu.assigned" />
					</a></li>
					<li class="${param.page == 3 ? 'active': ''}"><a href="#"><fmt:message
								key="view.menu.new-issue" />
					</a></li>
				</ul>
			</c:if>
			<c:choose>
				<c:when test="${empty sessionScope.key_user}">
					<form class="navbar-form pull-right" method="post" action="Login">
						<input type="text" name="key_login" value="${key_login}"
							class="input-small"
							placeholder=<fmt:message key="input.email.placeholder"/>>
						<input type="password" name="key_password" class="input-small"
							id="appendedInputButton" size="16"
							placeholder=<fmt:message key="input.password.placeholder"/>>
						<button class="btn" type="submit">
							<fmt:message key="view.submit.login" />
						</button>
					</form>
				</c:when>
				<c:otherwise>
					<div class="navbar-form pull-right btn-group">
						<a class="btn btn-primary" href="#"><i
							class="icon-user icon-white"></i> ${key_user.email}</a> <a
							class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
							href="#"><span class="caret"></span> </a>
						<ul class="dropdown-menu">
							<li><a href="#"><i class="icon-pencil"></i> <fmt:message
										key="view.menu.edit" /> </a>
							</li>
							<li><a href="Logout"><i class="icon-off"></i> <fmt:message
										key="view.menu.logout" /> </a>
							</li>
							<!--<li><a href="#"><i class="icon-ban-circle"></i> Ban</a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="i"></i> Make admin</a></li> -->
						</ul>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<br>
	<c:if test="${not empty sessionScope.key_alert_warning}">
		<c:set var="message"
			value="${ key_alert_warning == 1 ? 'login.empty' : key_alert_warning == 2 ? 'login.bad': 'incorect warning code'}"></c:set>
		<div class="alert ">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<h5>
				<fmt:message key="alert.warning" />
			</h5>
			<fmt:message key="${message}" />
			<c:remove var="key_alert_warning" scope="session" />
		</div>
	</c:if>