<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:import url="../header.jsp">
	<c:param name="title" value="title.error.401" />
</c:import>
<div class="span12">
	<div class='hero'>
		<div class="page-header">
			<h4>
				<fmt:message key="title.error.401" />
			</h4>
		</div>
	</div>
	<fmt:message key="error.401.message" />
</div>
<jsp:include page="../footer.jsp"></jsp:include>