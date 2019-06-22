<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import="com.busSystem.util.*"%>
<%
String username = request.getParameter("username") == null ? "" : request.getParameter("username");
DeleteBusInfo deleteUserInfo = new DeleteBusInfo();
boolean success = deleteUserInfo.deleteUserInfo(username);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证用户删除是否成功</title>
</head>

<body>
<script type="text/javascript">
	<%
		if (success) {%>
			alert("删除成功！");
			window.location.href = "deleteUserInfo.jsp";
		<%} else {%>
			alert("删除不成功！");
			window.location.href = "deleteUserInfo.jsp";
		<%}
	%>
</script>
</body>
</html>
