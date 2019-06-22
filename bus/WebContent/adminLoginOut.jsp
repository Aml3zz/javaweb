<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%
if (session.getAttribute("adminname") != null && !session.getAttribute("adminname").equals("") && session.getAttribute("adminpassword") != null && !session.getAttribute("adminpassword").equals("")) {
	session.removeAttribute("adminname");
	session.removeAttribute("adminpassword");
	response.sendRedirect("adminLogin.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员注销</title>
</head>

<body>
管理员还没有登录该系统！
</body>
</html>
