<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<jsp:include page="header.jsp"></jsp:include>
<title>title here</title>
</head>
<body>
			<table width="650" align="left"  border="1"  cellpadding="2" cellspacing="0">
				<tr>
					<td>Category</td>
					<td>Ticket price</td>
					<td>Total tickets</td>
					<td>Available</td>
					<c:if test="${user.role eq USER}">
						<td>Paid</td>
						<td>Ordered</td>
						<td>Number</td>
					</c:if>
				</tr>
				<c:forEach var="ticket" items="${tickets}" varStatus="categ">
					<tr>
						<td>${ticket.category}</td>
						<td>${ticket.price}</td>
						<td>${ticket.count}</td>
						<td>${ticketsOrdered[ticket.category].available}</td>
						<c:if test="${user.role eq USER}">
							<td>${ticketsOrdered[ticket.category].userPaid} 
							<c:set var="sumPaid" value="${sumPaid + ticketsOrdered[ticket.category].userPaid * ticket.price}"/>
							</td>
							<td>${ticketsOrdered[ticket.category].userOrdered}
							<c:set var="sumOrdered" value="${sumOrdered + ticketsOrdered[ticket.category].userOrdered * ticket.price}"/>
							</td>
							<td><input type="text" name="${ticket.category}" value="${numbers[ticket.category].numText}" style="color: ${numbers[ticket.category].color}"> </td>
						</c:if>
					</tr>
				</c:forEach>
				<c:if test="${user.role eq USER}">
					<tr>
				<td>Sum</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${sumPaid}</td>
					<td>${sumOrdered}</td>
					<td></td>
					</tr>
				</c:if>
			</table>

</body>
</html>
