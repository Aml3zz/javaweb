<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
DeleteBusInfo del = new DeleteBusInfo();
String busNum = request.getParameter("busNum") == null ? "" : request.getParameter("busNum");
String storder = request.getParameter("storder") == null ? "" : request.getParameter("storder");
boolean success = del.deleteBusSt(busNum,storder);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无标题文档</title>
</head>

<body>
<script type="text/javascript">
<%
if (success) {%>
	alert("删除成功！");
	window.location.href = "deleteStInfo.jsp";
<%} else {%>
	alert("删除失败！");
	window.location.href = "deleteStInfo.jsp";
<%}
%>
</script>
</body>
</html>
