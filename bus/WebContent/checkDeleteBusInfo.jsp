<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
String busNum = request.getParameter("busNum") == null ? "" : request.getParameter("busNum");
DeleteBusInfo deleteBusInfo = new DeleteBusInfo();
boolean success = deleteBusInfo.deleteBusInfo(busNum);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证删除车次信息</title>
<script type="text/javascript">
<%
if (success == true) {%>
	alert("删除车次信息成功!");
	window.location.href = "deleteBusInfo.jsp";
<%} else {%>
	alert("删除车次信息失败!");
	window.location.href = "deleteBusInfo.jsp";
<%}
%>
</script>
</head>

<body>

</body>
</html>
