<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
String id = request.getParameter("id") == null ? "" : request.getParameter("id");
DeleteBusInfo deleteBusInfo = new DeleteBusInfo();
boolean success = deleteBusInfo.deleteMessage(id);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证留言删除是否成功</title>
</head>

<body>
<script type="text/javascript">
	<%
		if (success) {%>
			alert("删除成功！");
			window.location.href = "deleteMessage.jsp";
		<%} else {%>
			alert("删除不成功！");
			window.location.href = "deleteMessage.jsp";
		<%}
	%>
</script>
</body>
</html>
