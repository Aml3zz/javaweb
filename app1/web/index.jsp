<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="query.do" method="post">
			<table>
				<tr>
					<td>CustomerName:</td>
					<td><input type="text" name="name"/></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input type="text" name="address"/></td>
				</tr>
				<tr>
					<td>Phone:</td>
					<td><input type="text" name="phone"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="Query"/></td>
					<td><a href="newcustomer.jsp">add new customer</a></td>
			</tr>
			</table>
	</form>
	
	<br>
	
	<c:if test="${!empty requestScope.customers }">
		<hr>
		<br><br>
		
				<table border="1" cellpadding="10" cellspacing="0">
					<tr>
						<th>ID</th>
						<th>CustomerName</th>
						<th>Address</th>
						<th>Phone</th>
						<th>UPDATE</th>
						<th>DELETE</th>
					</tr>
					

					<c:forEach items="${requestScope.customers }" var="cust">	
							<tr>
								<td>${cust.id }</td>
								<td>${cust.name }</td>
								<td>${cust.address }</td>
								<td>${cust.phone }</td>
								<td>
										<c:url value="/edit.do" var="editurl">
												<c:param name="id" value="${cust.id }"></c:param>
										</c:url>
										<a href="${editurl }">UPDATE</a>
									</td>
									<td>
										<c:url value="/delete.do" var="deleteurl">
												<c:param name="id" value="${cust.id }"></c:param>
										</c:url>
										<a href="${deleteurl }">DELETE</a>
								</td>
							</tr>

					</c:forEach>					
				</table>
		
</c:if>
	<br>
	
	
</body>
</html>
