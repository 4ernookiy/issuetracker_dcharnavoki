<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<jsp:include page="header.jsp"></jsp:include>
<title>Issue Tracker</title>
</head>
<body>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Priority</th>
				<th>Assigned</th>
				<th>Type</th>
				<th>Status</th>
				<th>Summary</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="oneissue" items="${key_issues}">
				<c:if test="${oneissue.priority.id == 1}">
					<c:set var="priorityColor" value="error"/>
				</c:if>
				<c:if test="${oneissue.priority.id == 2}">
					<c:set var="priorityColor" value="warning"/>
				</c:if>
				<c:if test="${oneissue.priority.id == 3}">
					<c:set var="priorityColor" value="info"/>
				</c:if>
				<c:if test="${oneissue.priority.id == 4}">
					<c:set var="priorityColor" value="success"/>
				</c:if>
				<tr class="${priorityColor}">
					<td>${oneissue.id}</td>
					<td>${oneissue.priority.description}</td>
					<td>${oneissue.assigned.email}</td>
					<td>${oneissue.type.description}</td>
					<td>${oneissue.status.description}</td>
					<td>${oneissue.summary}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
