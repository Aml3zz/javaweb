<%@page import="cn.edu.swu.mvcapp.domain.Customer"%>
<%@page import ="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<div style="background-color:#ddd;padding:30;width:30%;">
	<form action="query.do" method="post">
			<table>
				<tr>
					<td>Name:</td>
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
	</div>
	</center>
	<br>
	
	<%
			List<Customer> customers = (List<Customer>)request.getAttribute("customers");
			if(customers != null && customers.size() >0){
	%>
	
		<hr>
		<br><br>
		<center>
	        <div style="background-color:#ddd;padding:30;width:40%;">

				<table border="1" cellpadding="10" cellspacing="0">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Address</th>
						<th>Phone</th>
						<th>UPDATE</th>
						<th>DELETE</th>
					</tr>
					
					<%
						for(Customer customer:customers){
					%>
							
							<tr>
								<td><%= customer.getId() %></td>
								<td><%= customer.getName() %></td>
								<td><%= customer.getAddress() %></td>
								<td><%= customer.getPhone() %></td>
								<td>
	a									<a href="edit.do?id=<%= customer.getId() %>">UPDATE</a>
									</td>
									<td>
										<a href="delete.do?id=<%= customer.getId() %>">DELETE</a>
								</td>
							</tr>
					
					<%
						}
					%>
					
				</table>
		
			
        	</div>
	        </center>

	<% 
			}
	%>
	<br>
	
	
</body>
</html>
