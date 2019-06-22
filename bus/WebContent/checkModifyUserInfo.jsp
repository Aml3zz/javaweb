<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
String username = request.getParameter("username") == null ? "" : request.getParameter("username");
String userpassword = request.getParameter("userpassword") == null ? "" : request.getParameter("userpassword");
String userage = request.getParameter("userage") == null ? "" : request.getParameter("userage");
String address = request.getParameter("address") == null ? "" : request.getParameter("address");
String email = request.getParameter("email") == null ? "" : request.getParameter("email");
String question = request.getParameter("selectQuestion") == null ? "" : request.getParameter("selectQuestion");
String answer = request.getParameter("answer") == null ? "" : request.getParameter("answer");
String idnum = request.getParameter("idnum") == null ? "" : request.getParameter("idnum");
ModifyBusNum modifyBusNum = new ModifyBusNum();
boolean success = modifyBusNum.modifyUserInfo(username,userpassword,userage,address,email,question,answer,idnum);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证用户修改信息</title>
</head>
<script type="text/javascript">
<%
	if (success) {%>
		alert("用户信息修改成功！");
		window.location.href = "userInfo.jsp";
	<%} else {%>
		alert("用户信息修改失败！");
		window.location.href = "userInfo.jsp";
	<%}
%>
</script>
<body>
</body>
</html>
