<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<script src="./bootstrap/js/jquery.js"></script>
<script src="./bootstrap/js/bootstrap.js"></script>
<link href="./bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner">
			<a class="brand" href="Main">Issue Tracker</a>
			<ul class="nav">
				<li class="active"><a href="#">Last Issue</a></li>
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
			</ul>
			<c:choose>
				<c:when test="${empty sessionScope.key_user}">
					<form class="navbar-form pull-right" method="post" action="Login">
						<input type="text" name="key_login" value="${key_login}"
							class="input-small" placeholder="Email"> <input
							type="password" name="key_password" class="input-small"
							id="appendedInputButton" size="16" placeholder="Password">
						<button class="btn" type="submit">Sign in!</button>
					</form>
				</c:when>
				<c:otherwise>
						<div class="navbar-form pull-right btn-group">
							<a class="btn btn-primary" href="#"><i
								class="icon-user icon-white"></i> ${key_user.email}</a>
							<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
								href="#"><span class="caret"></span></a>
								
							<ul class="dropdown-menu">
								<li><a href="#"><i class="icon-pencil"></i> Edit</a></li>
								<li><a href="Logout"><i class="icon-off"></i> Sign out</a></li>
								<!--<li><a href="#"><i class="icon-ban-circle"></i> Ban</a></li>
								<li class="divider"></li>
								<li><a href="#"><i class="i"></i> Make admin</a></li> -->
							</ul>
						</div>
					<!--<form class="navbar-form pull-right" method="post" action="Logout">
						 <span class="navbar-text label label-success"><c:out
								value="${key_user.email}" /></span> 
						<button class="btn" type="submit">Sign Out</button>
					</form>-->
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<c:if test="${not empty sessionScope.key_errorMessage}">
		<div class="alert alert-error">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<h4>Error!</h4>
			<c:out value="${sessionScope.key_errorMessage}"></c:out>
			<c:remove var="key_errorMessage" scope="session" />
		</div>
	</c:if>
</body>
</html>