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
				<input type="text" class="input-small" placeholder="Email">
				<input class="span2" id="appendedInputButton" size="16"
					type="password" placeholder="Password">
				<button class="btn" type="button">Sign in!</button>
			</form>
			</c:when>
			<c:otherwise>
			ldfkvmdflmdkfl
			</c:otherwise>
			</c:choose>

		</div>
	</div>
</body>
</html>