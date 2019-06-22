<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" pageEncoding="UTF-8" %>
<%@ page import = "com.busSystem.util.*" %>
<%
String username = (String) session.getAttribute("userName");
String email = request.getParameter("email") == null ? "" : request.getParameter("email");
String topic = request.getParameter("topic") == null ? "" : request.getParameter("topic");
String messagetext = request.getParameter("messagetext") == null ? "" : request.getParameter("messagetext");
ModifyBusNum modifyBusNum = new ModifyBusNum();
boolean success = modifyBusNum.addMessage(username,topic,email,messagetext);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证添加留言信息</title>
</head>

<body>
<script type="text/javascript">
<%
	if (success) {%>
		alert("添加留言成功！");
		window.location.href = "addMessage.jsp";
	<%} else {%>
		alert("添加留言失败！");
		window.location.href = "addMessage.jsp";
	<%}
%>
</script>
</body>
</html>
