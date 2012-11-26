<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ page
	import="org.training.dcharnavoki.issuetracker.constant.Constant"%>
<%@ page import="org.training.dcharnavoki.issuetracker.beans.Role"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page errorPage="error.jsp"%>
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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="bootstrap/css/addon.css" rel="stylesheet">
<title><fmt:message key="${param.title}">
		<fmt:param value="${param.titleP1}" />
	</fmt:message>
</title>
</head>
<body>
