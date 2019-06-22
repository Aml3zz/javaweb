<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage=""  pageEncoding="UTF-8"%>
<%@ page import = "com.busSystem.util.*" %>
<% 
ModifyBusNum modifyBusNum = new ModifyBusNum();
String busNum = request.getParameter("busNum") == null ? "" : request.getParameter("busNum");
String beginSt = request.getParameter("beginSt") == null ? "" : request.getParameter("beginSt");
String endSt= request.getParameter("endSt") == null ? "" : request.getParameter("endSt");
String selectPrice = request.getParameter("selectPrice") == null ? "" : request.getParameter("selectPrice");
String selectLevel = request.getParameter("selectLevel") == null ? "" : request.getParameter("selectLevel");
String selectNote = request.getParameter("selectNote") == null ? "" : request.getParameter("selectNote");
boolean success = modifyBusNum.addBusNumInfo(busNum,beginSt,endSt,selectPrice,selectLevel,selectNote);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证添加车次信息</title>
</head>
<script language = "javascript">
<%
if (success == true) {%>
	alert("添加车次信息成功!");
	window.location.href="addBusInfo.jsp";
<%} else {%>
	alert("添加车次信息失败!");
	window.location.href="addBusInfo.jsp";
<%}
%>
</script>
<body>

</body>
</html>
